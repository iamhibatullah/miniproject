package com.itsupport.ticketing.dao;

import com.itsupport.ticketing.dto.ticket.TicketGridDTO;
import com.itsupport.ticketing.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = """
            SELECT *
            FROM Ticket as tic
            LEFT JOIN Complainer as com ON tic.ComplainerId = com.Id
            LEFT JOIN Personel as per ON tic.PersonelId = per.Id
            WHERE tic.subject LIKE %:subject%
            AND tic.status LIKE %:status%
            """, nativeQuery = true)
    List<Ticket> findAllTicketGrid(@Param("subject") String subject,
                                          @Param("status") String status);

    @Query(value = """
            SELECT *
            FROM Ticket as tic
            LEFT JOIN Complainer as com ON tic.ComplainerId = com.Id
            LEFT JOIN Personel as per ON tic.PersonelId = per.Id
            WHERE per.id = :personelId
            """, nativeQuery = true)
    List<Ticket> findAllTicketByPersonelId(@Param("personelId") Integer personelId);

    @Query(value = """
            SELECT *
            FROM Ticket as tic
            LEFT JOIN Complainer as com ON tic.ComplainerId = com.Id
            LEFT JOIN Personel as per ON tic.PersonelId = per.Id
            WHERE com.id = :complainerId
            """, nativeQuery = true)
    List<Ticket> findAllTicketByComplainerId(@Param("complainerId") Integer complainerId);
}
