package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Film;
import com.cinema.management.cinemamanagement.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByNumero(int num);
}
