package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<MovieDTO> movie = new ArrayList<>();

}
