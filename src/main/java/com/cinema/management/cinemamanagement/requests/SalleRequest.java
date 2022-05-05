package com.cinema.management.cinemamanagement.requests;

import com.cinema.management.cinemamanagement.entities.Cinema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SalleRequest {
    private Long id;
    private String name;
    private int nombrePlaces;
    private Cinema cinema;
}
