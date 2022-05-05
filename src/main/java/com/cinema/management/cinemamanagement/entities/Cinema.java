package com.cinema.management.cinemamanagement.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Cinema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longtitude;
    private double latitude;
    private double altitude;
    private int nombreSalles;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ville ville;
    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<Salle> salles;
}
