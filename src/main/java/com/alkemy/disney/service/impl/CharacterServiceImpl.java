package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

@Autowired
    private CharacterMapper characterMapper;

@Autowired
    private CharacterRepository characterRepository;

public CharacterDTO create(CharacterDTO characterDTO){
    CharacterEntity entity = characterMapper.characterDTO2Entity(characterDTO);
    CharacterEntity entitySaved = characterRepository.save(entity);
    CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved);

    return result;
}




}
