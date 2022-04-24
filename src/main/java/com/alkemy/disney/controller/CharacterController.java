package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CharacterDTO> create(@RequestBody CharacterDTO characterDTO){
        CharacterDTO characterSaved = characterService.create(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDTO update(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        return characterService.update(id, characterDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        characterService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDTO findById(@PathVariable Long id) {
        return  characterService.findById(id);
    }

    //todo:como hago para q devuelva siempre todos los personajes
    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> movie
    ){
        List<CharacterDTO> characters;
        if(name==null & age==null & movie==null){
            characters = characterService.getAll();
        }else{
            characters = characterService.getByFilters(name, age, movie);
        }
        return ResponseEntity.ok(characters);
    }

    /*
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDTO> getAll(){
        return characterService.getAll();
    }
     */

}
