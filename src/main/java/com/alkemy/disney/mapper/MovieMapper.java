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
public class MovieMapper {

    @Autowired
    CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity (MovieDTO dto){
        MovieEntity entity = new MovieEntity();

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setQualification(dto.getQualification());
        entity.setCharacters(dto.getCharacters());
        entity.setGender(dto.getGender());

        return entity;
    }

    public MovieDTO movieEntity2DTO (MovieEntity entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate());
        dto.setQualification(entity.getQualification());
        if(loadCharacters){
            List<CharacterDTO> characterDTOS = characterMapper.characterEntityList2DTOList(entity.getCharacters());
            dto.setCharacters(characterDTOS);
        }
        dto.setGender(entity.getGender());

        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities){
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity:entities) {
            dtos.add(movieEntity2DTO(entity,false));
        }
        return dtos;
    }

    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setQualification(dto.getQualification());
        entity.setGender(dto.getGender());
    }

    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> dtos){
        List<MovieEntity> entities = new ArrayList<>();
        for (MovieDTO dto : dtos){
            entities.add(movieDTO2Entity(dto));
        }
        return entities;
    }

}
