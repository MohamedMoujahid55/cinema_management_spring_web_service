package com.cinema.management.cinemamanagement.requests;

import com.cinema.management.cinemamanagement.entities.Categorie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
public class FilmRequest {

    private String titre;
    private double duree;
    private String realisateur;
    private String description;
    private String photo;
    private Date dateSortie;

    private Categorie categorie;
    //private Collection<ProjectionFilm> projectionFilms;
}
