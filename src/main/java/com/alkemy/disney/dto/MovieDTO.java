package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Integer qualification;
    private List<CharacterDTO> characters = new ArrayList<>();
    private GenderDTO gender;
    private Long genderId;

}
