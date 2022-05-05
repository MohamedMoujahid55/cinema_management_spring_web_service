package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.FilmDto;
import com.cinema.management.cinemamanagement.entities.Categorie;
import com.cinema.management.cinemamanagement.entities.Film;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface IFilmService {
    FilmDto addNewFilm(FilmDto filmDto);
    FilmDto getFilmbyName(FilmDto filmDto);
    FilmDto getFilmById(Long id);
    List<FilmDto> getFilms(int page, int size);
    FilmDto updateNewFilm(Long id, FilmDto filmDto);
    void deteleFilm(Long id);
}
