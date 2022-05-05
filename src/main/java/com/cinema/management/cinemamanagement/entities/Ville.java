package com.cinema.management.cinemamanagement.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;

    @OneToMany(mappedBy = "ville", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<Cinema> cinemas;
}
