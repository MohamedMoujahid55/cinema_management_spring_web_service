package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.SalleDto;
import com.cinema.management.cinemamanagement.entities.Categorie;
import com.cinema.management.cinemamanagement.entities.Salle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISalleService {
    SalleDto addNewSalle(SalleDto salleDto);
    SalleDto getSallebyName(String name);
    SalleDto getSalleById(Long id);
    List<SalleDto> getSalle(int page, int size);
    SalleDto updateNewSalle(Long id, SalleDto salleDto);
    void deteleSalle(Long id);
}
