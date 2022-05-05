package com.cinema.management.cinemamanagement.services;

public interface ICinemaInitService {
    /*
        This Interface is to initialize all database with Fake data;
     */

    void initVille();
    void initCinemas();
    void initSalles();
    void initPlaces();
    void initSeance();
    void initCategories();
    void initFilms();
    void initProjections();
    void initTickets();
}
