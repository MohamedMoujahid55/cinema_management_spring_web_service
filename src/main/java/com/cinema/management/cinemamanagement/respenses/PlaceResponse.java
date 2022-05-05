package com.cinema.management.cinemamanagement.respenses;

import com.cinema.management.cinemamanagement.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class PlaceResponse {
    private Long id;
    private int numero;
    private double longtitude;
    private double latitude;
    private double altitude;

    //private Salle salle;
    private Collection<Ticket> tickets;
}
