package com.cinema.management.cinemamanagement.dtos;

import com.cinema.management.cinemamanagement.entities.Salle;
import com.cinema.management.cinemamanagement.entities.Ville;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
@Data @AllArgsConstructor @NoArgsConstructor
public class CinemaDto implements Serializable {
    private Long id;
    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;
    private int nombreSalles;
    private Ville ville;
    private Collection<Salle> salles;
}
