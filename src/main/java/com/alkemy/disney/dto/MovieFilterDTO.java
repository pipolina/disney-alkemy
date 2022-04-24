package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieFilterDTO {

    private String name;
    private Long genderId;
    private String order;

    public boolean isASC(){
        return order.compareToIgnoreCase("ASC") == 0;
    }

}
