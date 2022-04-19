package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterDTO;
import lombok.NonNull;

import java.util.List;

public interface CharacterService {

    CharacterDTO create(CharacterDTO characterDTO);
    CharacterDTO update(Long id, CharacterDTO characterDTO);
    void delete(@NonNull Long id);
    List<CharacterDTO> getAll();
    CharacterDTO findById(@NonNull Long id);
    List<CharacterDTO> getByFilters(String name, Integer age, List<Long> movie);

}
