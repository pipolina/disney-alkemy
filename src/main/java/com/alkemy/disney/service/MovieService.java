package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;
import lombok.NonNull;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();
    MovieDTO findById(@NonNull Long id);
    MovieDTO update(Long id, MovieDTO movieDTO);
    void delete(@NonNull Long id);
    List<MovieDTO> getByFilters(String name, Long genderId, String order);
    MovieDTO addCharacters(Long idMovie, Long idCharacter);
    void deleteCharacter(Long idMovie, Long idCharacter);

}
