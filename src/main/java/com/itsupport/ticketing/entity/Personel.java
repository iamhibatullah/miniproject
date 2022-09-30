package com.itsupport.ticketing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import lombok.Builder;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Personel")
@Builder
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Subject")
    private String subject;
    @OneToMany(mappedBy = "personel")
    @JsonIgnore //used to avoid loop errors
    private List<Ticket> tickets = new LinkedList<>();

    public Personel() {
    }

    public Personel(String firstName, String lastName, String email, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subject = subject;
    }

    public Personel(int id, String firstName, String lastName, String email, String subject, List<Ticket> tickets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subject = subject;
        this.tickets = tickets;
    }

    public PersonelGridDTO convertToDto(){
        return PersonelGridDTO.builder()
                .id(this.id)
                .name(this.firstName.concat(" ").concat(this.lastName))
                .email(this.email)
                .subject(this.subject)
                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
