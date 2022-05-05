package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.SeanceDto;
import com.cinema.management.cinemamanagement.entities.Seance;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface ISeanceService {
    SeanceDto addNewSeance(SeanceDto seanceDto);
    SeanceDto getSeanceById(Long id);
    SeanceDto getSeanceByDate(SeanceDto seanceDto);
    List<SeanceDto> getSeances(int page, int size);
    SeanceDto updateNewSeance(Long id, SeanceDto seanceDto);
    void deteleSeance(Long seance_id);
}
