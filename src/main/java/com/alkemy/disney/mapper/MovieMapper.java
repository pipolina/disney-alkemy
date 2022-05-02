package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    CharacterMapper characterMapper;

    @Autowired
    GenderMapper genderMapper;

    public MovieEntity movieDTO2Entity (MovieDTO dto, boolean loadCharacters){
        MovieEntity entity = new MovieEntity();

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        if(dto.getQualification()>0 && dto.getQualification()<=5){
            entity.setQualification(dto.getQualification());
        }else {
            throw new ParamNotFound("The qualification number is out of range");
        }
        if(loadCharacters){
            entity.setCharacters(characterMapper.characterDTOList2EntityList(dto.getCharacters(),false));
        }
        entity.setGender(genderMapper.genderDTO2Entity(dto.getGender()));

        return entity;
    }

    public MovieDTO movieEntity2DTO (MovieEntity entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate());
        dto.setQualification(entity.getQualification());
        if(loadCharacters){
            List<CharacterDTO> characterDTOS = characterMapper.characterEntityList2DTOList(entity.getCharacters(),false);
            dto.setCharacters(characterDTOS);
        }
        dto.setGender(genderMapper.genderEntity2DTO(entity.getGender()));

        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity:entities) {
            dtos.add(movieEntity2DTO(entity,loadCharacters));
        }
        return dtos;
    }

    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setQualification(dto.getQualification());
        entity.setGender(genderMapper.genderDTO2Entity(dto.getGender()));
    }

    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> dtos, boolean loadCharacters){
        List<MovieEntity> entities = new ArrayList<>();
        for (MovieDTO dto : dtos){
            entities.add(movieDTO2Entity(dto,loadCharacters));
        }
        return entities;
    }

}
