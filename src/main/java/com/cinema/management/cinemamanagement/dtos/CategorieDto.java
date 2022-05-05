package com.cinema.management.cinemamanagement.dtos;

import com.cinema.management.cinemamanagement.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategorieDto implements Serializable {
    private Long id;
    private String name;
    private Collection<Film> films;
}
