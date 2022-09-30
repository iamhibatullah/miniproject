package com.itsupport.ticketing.dto.personel;

import lombok.Builder;

@Builder
public class PersonelGridDTO {

    private int id;
    private String name;
    private String email;
    private String subject;

    public PersonelGridDTO() {
    }

    public PersonelGridDTO(int id, String name, String email, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
    }

    public PersonelGridDTO(String name, String email, String subject) {
        this.name = name;
        this.email = email;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
