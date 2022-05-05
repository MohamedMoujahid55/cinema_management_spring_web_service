package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import com.cinema.management.cinemamanagement.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Salle findByName(String name);
}
