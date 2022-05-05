package com.cinema.management.cinemamanagement.requests;

import com.cinema.management.cinemamanagement.entities.Ville;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CinemaRequest {
    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;
    private int nombreSalles;
    private Ville ville;
}
