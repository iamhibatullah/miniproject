package com.itsupport.ticketing.dto.ticket;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class InsertTicketDTO {

    private int complainerId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate troubledSince;
    private String subject;
    private String description;

    public InsertTicketDTO() {
    }

    public InsertTicketDTO(int complainerId, LocalDate troubledSince, String subject, String description) {
        this.complainerId = complainerId;
        this.troubledSince = troubledSince;
        this.subject = subject;
        this.description = description;
    }

    public int getComplainerId() {
        return complainerId;
    }

    public void setComplainerId(int complainerId) {
        this.complainerId = complainerId;
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

}
