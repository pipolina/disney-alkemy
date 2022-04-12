package com.alkemy.disney.service.impl;

import com.alkemy.disney.controller.GenderController;
import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    GenderMapper genderMapper;

    public GenderDTO create(GenderDTO genderDTO){

        GenderEntity entity = genderMapper.genderDTO2Entity(genderDTO);
        GenderEntity entitySaved = genderRepository.save(entity);
        GenderDTO result = genderMapper.genderEntity2DTO(entitySaved);

        return result;
    }

}
