package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.VilleDto;
import com.cinema.management.cinemamanagement.entities.Ville;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVilleService {
    VilleDto addNewVille(VilleDto villeDto);
    VilleDto getVilleById(Long id);
    Ville getVillebyName(String name);
    List<VilleDto> getVilles(int page, int size);
    VilleDto updateNewVille(VilleDto villeDto);
    void deteleVille(Long id);
}
