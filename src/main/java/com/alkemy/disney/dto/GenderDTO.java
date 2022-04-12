package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenderDTO {

    private Long id;
    private String name;
    private String image;
    private List<MovieEntity> movies = new ArrayList<>();

}
