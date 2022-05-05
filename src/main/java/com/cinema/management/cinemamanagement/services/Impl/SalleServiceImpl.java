package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.SalleDto;
import com.cinema.management.cinemamanagement.entities.Categorie;
import com.cinema.management.cinemamanagement.entities.Salle;
import com.cinema.management.cinemamanagement.repositories.SalleRepository;
import com.cinema.management.cinemamanagement.services.ISalleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class SalleServiceImpl implements ISalleService {
    private SalleRepository salleRepository;
    @Override
    public SalleDto addNewSalle(SalleDto salleDto) {

        ModelMapper modelMapper = new ModelMapper();
        Salle salle = modelMapper.map(salleDto, Salle.class);
        Salle newSalle = salleRepository.save(salle);
        SalleDto salleDtoNew = modelMapper.map(newSalle, SalleDto.class);

        return salleDtoNew;
    }

    @Override
    public SalleDto getSallebyName(String name) {
        Salle salle = salleRepository.findByName(name);

        return new ModelMapper().map(salle, SalleDto.class) ;
    }

    @Override
    public SalleDto getSalleById(Long id) {
        Salle salle = salleRepository.findById(id).orElse(null);
        return new ModelMapper().map(salle, SalleDto.class);
    }

    @Override
    public List<SalleDto> getSalle(int page, int size) {

        Page<Salle> sallePage = salleRepository.findAll(PageRequest.of(page, size));
        List<Salle> sallesList = sallePage.getContent();

        ModelMapper modelMapper = new ModelMapper();
        List<SalleDto> salleDtos = new ArrayList<>();

        sallesList.forEach(salle -> {

            salleDtos.add(modelMapper.map(salle, SalleDto.class));
        });

        return salleDtos;
    }

    @Override
    public SalleDto updateNewSalle(Long id, SalleDto salleDto) {

        Salle salle = salleRepository.findById(id).orElse(null);

        salle.setName(salleDto.getName());
        salle.setNombrePlaces(salleDto.getNombrePlaces());
        if(salleDto.getCinema().getId() != null){
            salle.setCinema(salleDto.getCinema());
        }
        Salle newSalle = salleRepository.save(salle);

        ModelMapper modelMapper = new ModelMapper();
        SalleDto salleDtoUpdated = modelMapper.map(newSalle, SalleDto.class);

        return salleDtoUpdated;
    }

    @Override
    public void deteleSalle(Long id) {
        salleRepository.deleteById(id);
    }
}
