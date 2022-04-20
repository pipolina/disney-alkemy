package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CharacterMapper {

    @Autowired
    MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto){
        CharacterEntity entity = new CharacterEntity();

        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        entity.setMovie(dto.getMovie());

        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies){
        CharacterDTO dto = new CharacterDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if(loadMovies){
            List<MovieDTO> movieDTOS = movieMapper.movieEntityList2DTOList(entity.getMovie());
            dto.setMovie(movieDTOS); //me pide un entity, pero no entiendo porque
        }
        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2DTO(entity,false));
        }
        return dtos;
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos){
        List<CharacterEntity> entities = new ArrayList<>();
        for (CharacterDTO dto : dtos){
            entities.add(characterDTO2Entity(dto));
        }
        return entities;
    }

    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {

        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());

    }

}
