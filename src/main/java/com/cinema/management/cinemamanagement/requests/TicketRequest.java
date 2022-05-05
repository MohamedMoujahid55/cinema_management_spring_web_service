package com.cinema.management.cinemamanagement.requests;

import com.cinema.management.cinemamanagement.entities.Place;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TicketRequest {
    private String nomClient;
    private double prix;
    private int codePayement;
    private boolean reservee;
    private ProjectionFilm projectionFilm;
    private Place place;
}
