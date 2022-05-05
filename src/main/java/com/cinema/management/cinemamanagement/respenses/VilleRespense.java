package com.cinema.management.cinemamanagement.respenses;

import com.cinema.management.cinemamanagement.entities.Cinema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class VilleRespense {

    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;
    private Collection<Cinema> cinemas;
}
