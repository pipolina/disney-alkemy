package com.alkemy.disney.dto;

import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.GenderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CharacterEntity> characters = new HashSet<>();
    private GenderEntity gender;
    private Long genderId;

}
