package com.cinema.management.cinemamanagement.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class VilleRequest {

    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;
}
