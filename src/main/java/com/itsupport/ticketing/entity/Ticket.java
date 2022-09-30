package com.itsupport.ticketing.entity;

import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import com.itsupport.ticketing.dto.ticket.TicketGridDTO;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "ComplainerId")
    private Complainer complainer;
    @ManyToOne
    @JoinColumn(name = "PersonelId")
    private Personel personel;
    @Column(name = "TroubledSince")
    private LocalDate troubledSince;
    @Column(name = "Subject")
    private String subject;
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private String status;
    @Column(name = "ResolvedAt")
    private LocalDate resolvedAt;

    public Ticket() {
    }

    public Ticket(Complainer complainer, Personel personel, LocalDate troubledSince, String subject, String description, String status) {
        this.complainer = complainer;
        this.personel = personel;
        this.troubledSince = troubledSince;
        this.subject = subject;
        this.description = description;
        this.status = status;
    }

    public Ticket(int id, Complainer complainer, Personel personel,
                  LocalDate troubledSince, String subject, String description,
                  String status, LocalDate resolvedAt) {
        this.id = id;
        this.complainer = complainer;
        this.personel = personel;
        this.troubledSince = troubledSince;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.resolvedAt = resolvedAt;
    }

    public TicketGridDTO convertPendingTicketToDto(){
        return TicketGridDTO.builder()
                .id(this.id)
                .complainerName(this.complainer.getName())
                .troubledSince(this.troubledSince)
                .subject(this.subject)
                .description(this.description)
                .status(this.status)
                .build();
    }
    public TicketGridDTO convertResolvingTicketToDto(){
        return TicketGridDTO.builder()
                .id(this.id)
                .personelName(this.personel.getFirstName().concat(" ").concat(this.personel.getLastName()))
                .complainerName(this.complainer.getName())
                .troubledSince(this.troubledSince)
                .subject(this.subject)
                .description(this.description)
                .status(this.status)
                .build();
    }

    public TicketGridDTO convertCompletedTicketToDto(){
        return TicketGridDTO.builder()
                .id(this.id)
                .personelName(this.personel.getFirstName().concat(" ").concat(this.personel.getLastName()))
                .complainerName(this.complainer.getName())
                .troubledSince(this.troubledSince)
                .subject(this.subject)
                .description(this.description)
                .status(this.status)
                .resolvedAt(this.resolvedAt)
                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Complainer getComplainer() {
        return complainer;
    }

    public void setComplainer(Complainer complainer) {
        this.complainer = complainer;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public LocalDate getTroubledSince() {
        return troubledSince;
    }

    public void setTroubledSince(LocalDate troubledSince) {
        this.troubledSince = troubledSince;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDate resolvedAt) {
        this.resolvedAt = resolvedAt;
    }
}
