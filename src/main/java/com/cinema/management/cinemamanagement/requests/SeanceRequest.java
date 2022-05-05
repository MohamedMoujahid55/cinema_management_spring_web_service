package com.cinema.management.cinemamanagement.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class SeanceRequest {
    private Date heureDebut;
}
