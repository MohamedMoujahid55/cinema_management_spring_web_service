package com.cinema.management.cinemamanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class SeanceDto implements Serializable {

    private Long id;
    private Date heureDebut;

}
