package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private Integer age;

    private Double weight; //en kilos

    private String history;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.PERSIST)
    private List<MovieEntity> movie = new ArrayList<>();

}
