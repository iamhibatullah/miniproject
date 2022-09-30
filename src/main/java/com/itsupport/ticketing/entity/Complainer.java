package com.itsupport.ticketing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import lombok.Builder;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Complainer")
@Builder
public class Complainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "complainer")
    @JsonIgnore
    private List<Ticket> tickets = new LinkedList<>();

    public Complainer() {
    }

    public Complainer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Complainer(int id, String name, String email, String address, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.tickets = tickets;
    }

    public ComplainerGridDTO convertToDto(){
        return ComplainerGridDTO.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .address(this.address)
                .build();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
