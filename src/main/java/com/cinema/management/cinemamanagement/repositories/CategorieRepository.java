package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByName(String name);
}
