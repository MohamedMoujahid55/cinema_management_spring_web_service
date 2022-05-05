package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Cinema;
import com.cinema.management.cinemamanagement.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByTitre(String titre);
}
