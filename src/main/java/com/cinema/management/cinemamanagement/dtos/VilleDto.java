package com.cinema.management.cinemamanagement.dtos;

import com.cinema.management.cinemamanagement.entities.Cinema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class VilleDto implements Serializable {
    private Long id;
    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;
    private Collection<Cinema> cinemas;
}
