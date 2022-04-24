package com.alkemy.disney.repository;

import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findAll(Specification<MovieEntity> spec);

}
