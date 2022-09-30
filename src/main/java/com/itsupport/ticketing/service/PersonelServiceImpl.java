package com.itsupport.ticketing.service;

import com.itsupport.ticketing.dao.PersonelRepository;
import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import com.itsupport.ticketing.dto.personel.UpsertPersonelDTO;
import com.itsupport.ticketing.entity.Personel;
import com.itsupport.ticketing.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class PersonelServiceImpl implements PersonelService{

    @Autowired
    private PersonelRepository personelRepository;

    //find all existing personel in the database
    //with parameters name and subject default value as empty string ("")
    @Override
    public List<PersonelGridDTO> findAllPersonelGrid(String name, String subject) {

        List<PersonelGridDTO> grid = new LinkedList<>();
        personelRepository.findAllPersonelGrid(name, subject).stream()
                .forEach(each -> grid.add(each.convertToDto()));

        return grid;
    }

    //find a personel by personel id
    @Override
    public Personel findPersonelById(int personelId) {

        return personelRepository.findById(personelId)
                .orElseThrow(() -> new GlobalException("Personel with Id "+personelId+" Not Found"));
    }

    //saving new personel to database
    @Override
    public Personel save(UpsertPersonelDTO dto) {
        Personel entity = new Personel(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getSubject());
        personelRepository.save(entity);

        return entity;
    }

    //updating personel from database, with parameters personel id
    @Override
    public Personel update(UpsertPersonelDTO dto, int personelId) {
        Personel entity = findPersonelById(personelId);

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setSubject(dto.getSubject());

        return entity;
    }

    //delete a personel from the database with parameters personel id
    @Override
    public void deleteById(int personelId) {
        personelRepository.deleteById(personelId);
    }

    //this method is used when assigning personel to a ticket
    //adds the ticket to the personel's ticket list, then overwrite save it to the database
    @Override
    public void save(Personel personel) {
        personelRepository.save(personel);
    }
}
