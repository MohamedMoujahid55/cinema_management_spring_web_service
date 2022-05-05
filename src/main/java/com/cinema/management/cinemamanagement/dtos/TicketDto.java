package com.cinema.management.cinemamanagement.dtos;

import com.cinema.management.cinemamanagement.entities.Place;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class TicketDto implements Serializable {

    private Long id;
    private String nomClient;
    private double prix;
    private int codePayement;
    private boolean reservee;
    private ProjectionFilm projectionFilm;
    private Place place;

}
