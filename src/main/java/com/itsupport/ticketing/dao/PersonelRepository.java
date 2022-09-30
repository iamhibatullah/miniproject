package com.itsupport.ticketing.dao;

import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import com.itsupport.ticketing.entity.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonelRepository extends JpaRepository<Personel, Integer> {

    @Query(value = """
            SELECT *
            FROM Personel as per
            WHERE CONCAT(per.firstName, ' ',per.lastName) LIKE %:name%
            AND per.subject LIKE %:subject%
            """, nativeQuery = true)
    List<Personel> findAllPersonelGrid(@Param("name") String name,
                                              @Param("subject") String subject);
}
