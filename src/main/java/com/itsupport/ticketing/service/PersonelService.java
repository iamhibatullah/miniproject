package com.itsupport.ticketing.service;

import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import com.itsupport.ticketing.dto.personel.UpsertPersonelDTO;
import com.itsupport.ticketing.entity.Personel;

import java.util.List;

public interface PersonelService {
    List<PersonelGridDTO> findAllPersonelGrid(String name, String subject);

    Personel findPersonelById(int personelId);

    Personel save(UpsertPersonelDTO dto);

    Personel update(UpsertPersonelDTO dto, int personelId);

    void deleteById(int personelId);

    void save(Personel personel);
}
