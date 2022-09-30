package com.itsupport.ticketing.service;

import com.itsupport.ticketing.dao.ComplainerRepository;
import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import com.itsupport.ticketing.dto.complainer.UpsertComplainerDTO;
import com.itsupport.ticketing.entity.Complainer;
import com.itsupport.ticketing.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ComplainerServiceImpl implements ComplainerService{

    @Autowired
    private ComplainerRepository complainerRepository;

    //find all existing complainer in the database
    //with parameters name default value as empty string ("")
    @Override
    public List<ComplainerGridDTO> findAllComplainerGrid(String name) {

        List<ComplainerGridDTO> grid = new LinkedList<>();
        complainerRepository.findAllComplainerGrid(name).stream()
                .forEach(each -> grid.add(each.convertToDto()));

        return grid;
    }

    //find complainer by complainer id
    @Override
    public Complainer findComplainerById(int complainerId) {

        return complainerRepository.findById(complainerId)
                .orElseThrow(() -> new GlobalException("Complainer with Id "+complainerId+" Not Found"));
    }

    //saving new personel to database
    @Override
    public Complainer save(UpsertComplainerDTO dto) {
        Complainer entity = new Complainer(dto.getName(), dto.getEmail(), dto.getAddress());
        complainerRepository.save(entity);

        return entity;
    }

    //this method is used when creating new ticket
    //adds the ticket to the complainer's ticket list, then overwrite save it to the database
    @Override
    public void save(Complainer complainer) {
        complainerRepository.save(complainer);
    }

    //updating complainer from database, with parameters complainer id
    @Override
    public Complainer update(UpsertComplainerDTO dto, int complainerId) {
        Complainer entity = findComplainerById(complainerId);

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());

        complainerRepository.save(entity);

        return entity;
    }

    //delete a complainer from the database with parameters complainer id
    @Override
    public void deleteById(int complainerId) {
        complainerRepository.deleteById(complainerId);
    }


}
