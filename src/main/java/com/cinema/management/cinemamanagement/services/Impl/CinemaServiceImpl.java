package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.CinemaDto;
import com.cinema.management.cinemamanagement.entities.Cinema;
import com.cinema.management.cinemamanagement.repositories.CinemaRepository;
import com.cinema.management.cinemamanagement.repositories.VilleRepository;
import com.cinema.management.cinemamanagement.services.ICinemaService;
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
public class CinemaServiceImpl implements ICinemaService {
    private CinemaRepository cinemaRepository;
    @Override
    public CinemaDto addNewCinema(CinemaDto cinemaDto) {
        ModelMapper modelMapper = new ModelMapper();
        Cinema cinema = modelMapper.map(cinemaDto, Cinema.class);
        Cinema newCinema = cinemaRepository.save(cinema);
        CinemaDto newCinemaDto = modelMapper.map(newCinema, CinemaDto.class);
        return newCinemaDto;
    }

    @Override
    public CinemaDto getCinemabyName(String name) {

        CinemaDto cinemaDto = new ModelMapper().map(cinemaRepository.findByName(name), CinemaDto.class);
        return cinemaDto;
    }

    @Override
    public CinemaDto getCinemaById(Long id) {
        return new ModelMapper().map(cinemaRepository.findById(id).orElse(null), CinemaDto.class);
    }

    @Override
    public List<CinemaDto> getCinemas(int page, int size) {
        Page<Cinema> cinemasPage = cinemaRepository.findAll(PageRequest.of(page,size));
        ModelMapper modelMapper = new ModelMapper();
        List<Cinema> cinemaList = cinemasPage.getContent();
        List<CinemaDto> cinemaDtos = new ArrayList<>();
        cinemaList.forEach(cinema -> {
            cinemaDtos.add(modelMapper.map(cinema, CinemaDto.class));
        });
        return cinemaDtos;
    }

    @Override
    public CinemaDto updateNewCinema(Long id, CinemaDto cinemaDto) {

        Cinema cinema = cinemaRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();

        cinema.setName(cinemaDto.getName());
        cinema.setLatitude(cinemaDto.getLatitude());
        cinema.setAltitude(cinemaDto.getAltitude());
        cinema.setLongtitude(cinemaDto.getLongtitude());
        cinema.setNombreSalles(cinemaDto.getNombreSalles());

        Cinema newCinema = cinemaRepository.save(cinema);
        CinemaDto newCinemaDto = modelMapper.map(newCinema, CinemaDto.class);
        return newCinemaDto;
    }

    @Override
    public void deteleCinema(Long id) {
        cinemaRepository.deleteById(id);
    }
}
