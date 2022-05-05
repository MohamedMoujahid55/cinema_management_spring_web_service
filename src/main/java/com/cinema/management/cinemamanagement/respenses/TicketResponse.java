package com.cinema.management.cinemamanagement.respenses;

import com.cinema.management.cinemamanagement.entities.Place;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TicketResponse {
    private String nomClient;
    private double prix;
    private int codePayement;
    private boolean reservee;
    private ProjectionFilm projectionFilm;
    private Place place;
}
