package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gender")
public class GenderController {

    @Autowired
    GenderService genderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenderDTO> create (@RequestBody GenderDTO genderDTO){
        GenderDTO genderSaved = genderService.create(genderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderSaved);
    }

}
