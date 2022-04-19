package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterFilterDTO {

    private String name;
    private Integer age;
    private List<Long> movie;
}
