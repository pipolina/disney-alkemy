package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.MovieService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    public MovieDTO save(MovieDTO dto){
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity savedMovie = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedMovie,true);
        return result;
    }

    public List<MovieDTO> getAllMovies(){
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities);
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
        return movieMapper.movieEntity2DTO(entitySaved,true);
    }

    public void delete(@NonNull Long id){
        movieRepository.deleteById(id);
    }

}
