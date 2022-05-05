package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.CinemaDto;
import java.util.List;

public interface ICinemaService {
    CinemaDto addNewCinema(CinemaDto cinemaDto);
    CinemaDto getCinemabyName(String name);
    CinemaDto getCinemaById(Long id);
    List<CinemaDto> getCinemas(int page, int size);
    CinemaDto updateNewCinema(Long id, CinemaDto cinema);
    void deteleCinema(Long id);
}
