package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entity.GenderEntity;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {

    public GenderEntity genderDTO2Entity (GenderDTO dto){
        GenderEntity entity = new GenderEntity();

        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setMovies(dto.getMovies());

        return entity;
    }

    public GenderDTO genderEntity2DTO (GenderEntity entity){
        GenderDTO dto = new GenderDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setMovies(entity.getMovies());

        return dto;
    }

}
