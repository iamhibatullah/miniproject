package com.itsupport.ticketing.restcontroller;

import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import com.itsupport.ticketing.dto.complainer.UpsertComplainerDTO;
import com.itsupport.ticketing.dto.personel.PersonelGridDTO;
import com.itsupport.ticketing.dto.personel.UpsertPersonelDTO;
import com.itsupport.ticketing.entity.Complainer;
import com.itsupport.ticketing.entity.Personel;
import com.itsupport.ticketing.service.PersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personel")
public class PersonelController {

    @Autowired
    private PersonelService personelService;

    @GetMapping
    public ResponseEntity<Object> getAllPersonel(@RequestParam(defaultValue = "") String name,
                                                                @RequestParam(defaultValue = "") String subject){

        List<PersonelGridDTO> grid = personelService.findAllPersonelGrid(name, subject);

        return new ResponseEntity<>(grid, HttpStatus.OK);
    }

    @GetMapping("/{personelId}")
    public ResponseEntity<Object> getSinglePersonel(@PathVariable int personelId){

        Personel entity = personelService.findPersonelById(personelId);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insertPersonel(@RequestBody UpsertPersonelDTO dto){
        Personel entity = personelService.save(dto);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{personelId}")
    public ResponseEntity<Object> updatePersonel(@RequestBody UpsertPersonelDTO dto,
                                                   @PathVariable int personelId){
        Personel entity = personelService.update(dto, personelId);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{personelId}")
    public String deletePersonel(@PathVariable int personelId){
        personelService.deleteById(personelId);

        return "Delete Success!";
    }
}
