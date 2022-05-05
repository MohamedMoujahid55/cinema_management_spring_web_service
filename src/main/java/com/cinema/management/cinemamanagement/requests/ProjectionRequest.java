package com.cinema.management.cinemamanagement.requests;

import com.cinema.management.cinemamanagement.entities.Film;
import com.cinema.management.cinemamanagement.entities.Salle;
import com.cinema.management.cinemamanagement.entities.Seance;
import com.cinema.management.cinemamanagement.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProjectionRequest {
    private Date dateProjection;
    private double prix;
    private Seance seance;
    //private Collection<Ticket> tickets;
    private Salle salle;
    private Film film;
}
