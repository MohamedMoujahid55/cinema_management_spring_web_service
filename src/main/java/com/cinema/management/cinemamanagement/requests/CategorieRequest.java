package com.cinema.management.cinemamanagement.requests;

import com.cinema.management.cinemamanagement.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor @Data @NoArgsConstructor
public class CategorieRequest {
    private String name;
    //private Collection<Film> films;
}
