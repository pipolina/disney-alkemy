package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO){
        MovieDTO savedMovie = movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    /*
    @GetMapping("/list")
    public ResponseEntity<List<MovieDTO>> getAll(){
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }
     */

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO foundMovie = movieService.findById(id);
        return ResponseEntity.ok().body(foundMovie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        MovieDTO result = movieService.update(id, movieDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genderId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> movies;
        if(name==null & genderId==null){
            movies = movieService.getAllMovies();
        }else{
            movies = movieService.getByFilters(name, genderId, order);
        }
        return ResponseEntity.ok(movies);
    }

}
