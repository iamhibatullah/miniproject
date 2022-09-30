package com.itsupport.ticketing.dao;

import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import com.itsupport.ticketing.entity.Complainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplainerRepository extends JpaRepository<Complainer, Integer> {

    @Query(value = """
            SELECT *
            FROM Complainer as com
            WHERE com.name LIKE %:name%
            """, nativeQuery = true)
    List<Complainer> findAllComplainerGrid(@Param("name") String name);
}
