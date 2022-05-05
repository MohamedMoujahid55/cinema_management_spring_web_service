package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Place;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ProjectionFilmRepository extends JpaRepository<ProjectionFilm, Long> {
    ProjectionFilm findByDateProjection(Date date);
}
