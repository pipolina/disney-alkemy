package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.service.CharacterService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    public CharacterDTO create(CharacterDTO characterDTO){
        CharacterEntity entity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity entitySaved = characterRepository.save(entity);

    return characterMapper.characterEntity2DTO(entitySaved);
    }

    public CharacterDTO update(Long id, CharacterDTO characterDTO){
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid character id");
        }
        characterMapper.characterEntityRefreshValues(entity.get(),characterDTO);
        CharacterEntity entitySaved = characterRepository.save(entity.get());
        return characterMapper.characterEntity2DTO(entitySaved);
    }

    public void delete(@NonNull Long id){
        characterRepository.deleteById(id);
    }

    public CharacterDTO findById(@NonNull Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid character id");
        }
        return characterMapper.characterEntity2DTO(entity.get());
    }

    public List<CharacterDTO> getAll(){
        List<CharacterEntity> entities = characterRepository.findAll();
        return characterMapper.characterEntityList2DTOList(entities);
    }

}
