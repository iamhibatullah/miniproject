package com.itsupport.ticketing.service;

import com.itsupport.ticketing.dto.ticket.InsertTicketDTO;
import com.itsupport.ticketing.dto.ticket.TicketGridDTO;
import com.itsupport.ticketing.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<TicketGridDTO> findAllTicketGrid(String subject, String status);

    List<TicketGridDTO> findAllTicketByPersonelId(int personelId);

    List<TicketGridDTO> findAllTicketByComplainerId(int complainerId);

    Ticket findTicketById(int ticketId);

    Ticket newTicket(InsertTicketDTO dto);

    Ticket assignPersonel(int ticketId, int personelId);

    Ticket completeTicket(int ticketId);
}
