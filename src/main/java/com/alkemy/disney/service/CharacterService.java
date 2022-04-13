package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterDTO;
import lombok.NonNull;

public interface CharacterService {

    CharacterDTO create(CharacterDTO characterDTO);
    CharacterDTO update(Long id, CharacterDTO characterDTO);
    void delete(@NonNull Long id);

}
