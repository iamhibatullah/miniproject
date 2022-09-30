package com.itsupport.ticketing.restcontroller;

import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import com.itsupport.ticketing.dto.personel.UpsertPersonelDTO;
import com.itsupport.ticketing.dto.ticket.InsertTicketDTO;
import com.itsupport.ticketing.dto.ticket.TicketGridDTO;
import com.itsupport.ticketing.entity.Personel;
import com.itsupport.ticketing.entity.Ticket;
import com.itsupport.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<Object> getAllTicket(@RequestParam(defaultValue = "") String subject,
                                                            @RequestParam(defaultValue = "") String status){

        List<TicketGridDTO> grid = ticketService.findAllTicketGrid(subject, status);

        return new ResponseEntity<>(grid, HttpStatus.OK);
    }

    @GetMapping("/personel/{personelId}")
    public ResponseEntity<Object> getAllTicketByPersonelId(@PathVariable int personelId){

        List<TicketGridDTO> grid = ticketService.findAllTicketByPersonelId(personelId);

        return new ResponseEntity<>(grid, HttpStatus.OK);
    }

    @GetMapping("/complainer/{complainerId}")
    public ResponseEntity<Object> getAllTicketByComplainerId(@PathVariable int complainerId){

        List<TicketGridDTO> grid = ticketService.findAllTicketByComplainerId(complainerId);

        return new ResponseEntity<>(grid, HttpStatus.OK);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Object> getSingleTicket(@PathVariable int ticketId){

        Ticket entity = ticketService.findTicketById(ticketId);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insertTicket(@RequestBody InsertTicketDTO dto){
        Ticket entity = ticketService.newTicket(dto);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @PostMapping("/assign/{ticketId}/{personelId}")
    public ResponseEntity<Object> assignPersonel(@PathVariable int ticketId,
                                                 @PathVariable int personelId){
        Ticket entity = ticketService.assignPersonel(ticketId, personelId);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @PostMapping("/complete/{ticketId}")
    public ResponseEntity<Object> completeTicket(@PathVariable int ticketId){
        Ticket entity = ticketService.completeTicket(ticketId);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }
}
