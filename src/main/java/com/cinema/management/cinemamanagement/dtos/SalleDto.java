package com.cinema.management.cinemamanagement.dtos;

import com.cinema.management.cinemamanagement.entities.Cinema;
import com.cinema.management.cinemamanagement.entities.Place;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class SalleDto implements Serializable {

    private Long id;
    private String name;
    private int nombrePlaces;
    private Cinema cinema;
    private Collection<Place> places;
    private Collection<ProjectionFilm> projectionFilms;
}
