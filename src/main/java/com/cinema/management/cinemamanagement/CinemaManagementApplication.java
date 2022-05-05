package com.cinema.management.cinemamanagement;

import com.cinema.management.cinemamanagement.entities.Categorie;
import com.cinema.management.cinemamanagement.entities.Cinema;
import com.cinema.management.cinemamanagement.entities.Film;
import com.cinema.management.cinemamanagement.entities.Ville;
import com.cinema.management.cinemamanagement.repositories.CategorieRepository;
import com.cinema.management.cinemamanagement.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import java.util.Date;

@SpringBootApplication
public class CinemaManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ICinemaInitService cinemaInitService, IVilleService villeService){
        return args -> {
           cinemaInitService.initVille();
           cinemaInitService.initCinemas();
           cinemaInitService.initSalles();
           cinemaInitService.initPlaces();
           cinemaInitService.initSeance();
           cinemaInitService.initCategories();
           cinemaInitService.initFilms();
           cinemaInitService.initProjections();
           cinemaInitService.initTickets();
        };
     }

}
