package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Salle;
import com.cinema.management.cinemamanagement.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
    Seance findByHeureDebut(Date date);
}
