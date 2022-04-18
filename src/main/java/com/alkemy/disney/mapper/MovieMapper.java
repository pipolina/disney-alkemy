package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

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

    public MovieDTO movieEntity2DTO (MovieEntity entity){
        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate());
        dto.setQualification(entity.getQualification());
        dto.setCharacters(entity.getCharacters()); //aca no entiendo el tema del "loadCharacters"
        dto.setGender(entity.getGender());

        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities){
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity:entities) {
            dtos.add(movieEntity2DTO(entity));
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

}
