package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.*;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specification.MovieSpecification;
import com.alkemy.disney.service.CharacterService;
import com.alkemy.disney.service.MovieService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieSpecification movieSpecification;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterService characterService;

    public MovieDTO save(MovieDTO dto){
        MovieEntity entity = movieMapper.movieDTO2Entity(dto,false);
        MovieEntity savedMovie = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedMovie,false);
        return result;
    }

    public List<MovieDTO> getAllMovies(){
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities,true);
        return result;
    }

    public MovieDTO findById(@NonNull Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid movie id");
        }
        return movieMapper.movieEntity2DTO(entity.get(),true);
    }

    public MovieDTO update(Long id, MovieDTO movieDTO){
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid movie id");
        }
        movieMapper.movieEntityRefreshValues(entity.get(),movieDTO);
        MovieEntity entitySaved = movieRepository.save(entity.get());
        return movieMapper.movieEntity2DTO(entitySaved,false);
    }

    public void delete(@NonNull Long id){
        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getByFilters(String name, Long genderId, String order){
        MovieFilterDTO filterDTO = new MovieFilterDTO(name,genderId,order);
        List<MovieEntity> entites = movieRepository.findAll(movieSpecification.getByFilters(filterDTO));
        return movieMapper.movieEntityList2DTOList(entites,true);
    }

    public MovieDTO addCharacters(Long idMovie, Long idCharacter){
        MovieEntity movie = movieMapper.movieDTO2Entity(findById(idMovie),false);
        CharacterEntity character = characterMapper.characterDTO2Entity(characterService.findById(idCharacter),false);
        List<CharacterEntity> characters = new ArrayList<>();
        characters.add(character);
        movie.setCharacters(characters);
        return movieMapper.movieEntity2DTO(movie,true);
    }

    public void deleteCharacter(Long idMovie, Long idCharacter){
        MovieEntity movie = movieMapper.movieDTO2Entity(findById(idMovie),false);
        CharacterEntity character = characterMapper.characterDTO2Entity(characterService.findById(idCharacter),false);
        movie.getCharacters().remove(character);
    }

}
