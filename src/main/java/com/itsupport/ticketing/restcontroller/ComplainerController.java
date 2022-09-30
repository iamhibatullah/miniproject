package com.itsupport.ticketing.restcontroller;

import com.itsupport.ticketing.dto.complainer.ComplainerGridDTO;
import com.itsupport.ticketing.dto.complainer.UpsertComplainerDTO;
import com.itsupport.ticketing.entity.Complainer;
import com.itsupport.ticketing.service.ComplainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complainer")
public class ComplainerController {

    @Autowired
    private ComplainerService complainerService;

    @GetMapping
    public ResponseEntity<Object> getAllComplainer(@RequestParam(defaultValue = "") String name){

        List<ComplainerGridDTO> grid = complainerService.findAllComplainerGrid(name);

        return new ResponseEntity<>(grid, HttpStatus.OK);
    }

    @GetMapping("/{complainerId}")
    public ResponseEntity<Object> getSingleComplainer(@PathVariable int complainerId){

        Complainer entity = complainerService.findComplainerById(complainerId);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insertComplainer(@RequestBody UpsertComplainerDTO dto){
        Complainer entity = complainerService.save(dto);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{complainerId}")
    public ResponseEntity<Object> updateComplainer(@RequestBody UpsertComplainerDTO dto,
                                                   @PathVariable int complainerId){
        Complainer entity = complainerService.update(dto, complainerId);

        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{complainerId}")
    public String deleteComplainer(@PathVariable int complainerId){
        //checks if the given complainer id exists in the database
        complainerService.deleteById(complainerId);

        return "Delete Success!";
    }
}
