package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Ticket;
import com.cinema.management.cinemamanagement.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Long> {
    Ville findByName(String name);
}
