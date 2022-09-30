package com.itsupport.ticketing.service;

import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import com.itsupport.ticketing.dto.complainer.UpsertComplainerDTO;
import com.itsupport.ticketing.entity.Complainer;

import java.util.List;

public interface ComplainerService {
    List<ComplainerGridDTO> findAllComplainerGrid(String name);

    Complainer findComplainerById(int complainerId);

    Complainer save(UpsertComplainerDTO dto);

    void save(Complainer complainer);

    Complainer update(UpsertComplainerDTO dto, int complainerId);

    void deleteById(int complainerId);
}
