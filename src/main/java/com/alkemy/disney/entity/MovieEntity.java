package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyy-mm-dd")
    private LocalDate creationDate;

    private Integer qualification;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<CharacterEntity> characters = new HashSet<>(); //xq define un set y no un arraylist??

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)//Eager significa que la inicializacion va a ser de tipo temprana
    @JoinColumn(name = "gender_id",insertable = false,updatable = false)
    private GenderEntity gender;

    @Column(name = "gender_id",nullable = false)
    private Long genderId; //le agrego el genero para solo enviar el genero al momento de crearlo y no toda la entidad entera. para guardar y actualizar

}
