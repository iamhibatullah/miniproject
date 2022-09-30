package com.itsupport.ticketing.dto.ticket;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class TicketGridDTO {

    private int id;
    private String personelName;
    private String complainerName;
    private LocalDate troubledSince;
    private String subject;
    private String description;
    private String status;
    private LocalDate resolvedAt;

    public TicketGridDTO() {
    }

    public TicketGridDTO(int id, String personelName,
                         String complainerName, LocalDate troubledSince,
                         String subject, String description, String status, LocalDate resolvedAt) {
        this.id = id;
        this.personelName = personelName;
        this.complainerName = complainerName;
        this.troubledSince = troubledSince;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.resolvedAt = resolvedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonelName() {
        return personelName;
    }

    public void setPersonelName(String personelName) {
        this.personelName = personelName;
    }

    public String getComplainerName() {
        return complainerName;
    }

    public void setComplainerName(String complainerName) {
        this.complainerName = complainerName;
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
