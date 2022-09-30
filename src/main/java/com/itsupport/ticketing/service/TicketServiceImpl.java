package com.itsupport.ticketing.service;

import com.itsupport.ticketing.dao.TicketRepository;
import com.itsupport.ticketing.dto.ticket.InsertTicketDTO;
import com.itsupport.ticketing.dto.ticket.TicketGridDTO;
import com.itsupport.ticketing.entity.Complainer;
import com.itsupport.ticketing.entity.Personel;
import com.itsupport.ticketing.entity.Ticket;
import com.itsupport.ticketing.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ComplainerService complainerService;
    @Autowired
    private PersonelService personelService;

    //find all existing ticket in the database
    //with parameters subject and status default value as empty string ("")
    @Override
    public List<TicketGridDTO> findAllTicketGrid(String subject, String status) {

        List<TicketGridDTO> grid = new LinkedList<>();
        List<Ticket> ticketList = ticketRepository.findAllTicketGrid(subject, status);

        for (Ticket each : ticketList) {
            if(each.getStatus().equals("Pending")){
                grid.add(each.convertPendingTicketToDto());
            } else if(each.getStatus().equals("Resolving")){
                grid.add(each.convertResolvingTicketToDto());
            } else if(each.getStatus().equals("Completed")){
                grid.add(each.convertCompletedTicketToDto());
            }
        }

        return grid;
    }

    //find all ticket with parameters personel id
    //returns all the ticket with personel that match the personel id given
    @Override
    public List<TicketGridDTO> findAllTicketByPersonelId(int personelId) {

        Personel personel = personelService.findPersonelById(personelId);
        if(personel.getTickets().isEmpty()){
            throw new GlobalException("This Personel is currently not resolving any Ticket");
        }

        List<TicketGridDTO> grid = new LinkedList<>();

        List<Ticket> ticketList = ticketRepository.findAllTicketByPersonelId(personelId);

        for (Ticket each : ticketList) {
            if(each.getStatus().equals("Resolving")){
                grid.add(each.convertResolvingTicketToDto());
            } else if(each.getStatus().equals("Completed")){
                grid.add(each.convertCompletedTicketToDto());
            }
        }

        return grid;
    }

    //find all ticket with parameters complainer id
    //returns all the ticket with complainer that match the complainer id given
    @Override
    public List<TicketGridDTO> findAllTicketByComplainerId(int complainerId) {

        Complainer complainer = complainerService.findComplainerById(complainerId);
        if(complainer.getTickets().isEmpty()){
            throw new GlobalException("This Complainer has not made any Ticket");
        }

        List<TicketGridDTO> grid = new LinkedList<>();
        List<Ticket> ticketList = ticketRepository.findAllTicketByComplainerId(complainerId);

        for (Ticket each : ticketList) {
            if(each.getStatus().equals("Pending")){
                grid.add(each.convertPendingTicketToDto());
            } else if(each.getStatus().equals("Resolving")){
                grid.add(each.convertResolvingTicketToDto());
            } else if(each.getStatus().equals("Completed")){
                grid.add(each.convertCompletedTicketToDto());
            }
        }

        return grid;
    }

    //find ticket by the ticket id
    @Override
    public Ticket findTicketById(int ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new GlobalException("Ticket with Id "+ticketId+" Not Found"));
    }

    //create new ticket, every new ticket has the status "Pending" and have no personel
    //assign a personel with different method
    @Override
    public Ticket newTicket(InsertTicketDTO dto) {

        Complainer ticketComplainer = complainerService.findComplainerById(dto.getComplainerId());

        Ticket entity = new Ticket();
        entity.setComplainer(ticketComplainer);
        entity.setSubject(dto.getSubject());
        entity.setDescription(dto.getDescription());
        entity.setTroubledSince(dto.getTroubledSince());
        entity.setStatus("Pending");
        ticketRepository.save(entity);

        //add the ticket to the complainer's ticket list
        ticketComplainer.addTicket(entity);
        complainerService.save(ticketComplainer);

        return entity;
    }

    //assign a personel to resolve the ticket, also change the ticket status to "Resolving"
    @Override
    public Ticket assignPersonel(int ticketId, int personelId) {

        Personel ticketPersonel = personelService.findPersonelById(personelId);

        Ticket entity = findTicketById(ticketId);

        //cannot assign personel with different subject to the ticket
        if(entity.getSubject().equals(ticketPersonel.getSubject())){
            entity.setPersonel(ticketPersonel);
            entity.setStatus("Resolving");
            ticketRepository.save(entity);


            //add the ticket to the personel's ticket list
            ticketPersonel.addTicket(entity);
            personelService.save(ticketPersonel);
        } else {
            throw new GlobalException("Personel subject does not match with the ticket subject!");
        }

        return entity;
    }

    //when the ticket problem is resolved, use this method to complete the ticket
    //change the ticket status to "Completed" and add Resolved Date
    @Override
    public Ticket completeTicket(int ticketId) {
        Ticket entity = findTicketById(ticketId);

        //if there is no personel on the ticket, the ticket cannot be completed
        if(entity.getPersonel() == null){
            throw new GlobalException("This ticket cannot be completed without a Personel resolving it!");
        }else {
            entity.setStatus("Completed");
            entity.setResolvedAt(LocalDate.now());
            ticketRepository.save(entity);

        }

        return entity;
    }
}
