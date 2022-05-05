package com.cinema.management.cinemamanagement.respenses;

import com.cinema.management.cinemamanagement.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data @NoArgsConstructor @AllArgsConstructor
public class CategorieResponse {
    private String name;
    //private Collection<Film> films;
}
