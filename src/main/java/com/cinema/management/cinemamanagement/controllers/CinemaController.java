package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.CinemaDto;
import com.cinema.management.cinemamanagement.requests.CinemaRequest;
import com.cinema.management.cinemamanagement.respenses.CinemaRespense;
import com.cinema.management.cinemamanagement.services.ICinemaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
@AllArgsConstructor
public class CinemaController {
    private ICinemaService cinemaService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CinemaRespense> getCinema(@PathVariable Long id){

        CinemaDto cinemaDto = cinemaService.getCinemaById(id);
        ModelMapper modelMapper = new ModelMapper();
        CinemaRespense cinemaRespense = modelMapper.map(cinemaDto, CinemaRespense.class);
        return new ResponseEntity<CinemaRespense>(cinemaRespense, HttpStatus.OK);
    }

    @GetMapping
    public List<CinemaRespense> getCinemas(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "3") int size){

        List<CinemaDto> cinemaDtos = cinemaService.getCinemas(page, size);
        ModelMapper modelMapper = new ModelMapper();
        List<CinemaRespense> cinemaRespenses = new ArrayList<>();
        cinemaDtos.forEach(cinemaDto -> {
            cinemaRespenses.add(modelMapper.map(cinemaDto, CinemaRespense.class));
        });
        return cinemaRespenses;
    }

    @PostMapping
    public ResponseEntity<CinemaRespense> addNewCinema(@RequestBody CinemaRequest cinemaRequest){

        ModelMapper modelMapper = new ModelMapper();
        CinemaDto cinemaDto = modelMapper.map(cinemaRequest, CinemaDto.class);
        CinemaDto newCinema = cinemaService.addNewCinema(cinemaDto);

        CinemaRespense cinemaRespense = modelMapper.map(newCinema, CinemaRespense.class);

        return new ResponseEntity<CinemaRespense>(cinemaRespense, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CinemaRespense> updateCinema(@PathVariable Long id,
                                                       @RequestBody CinemaRequest cinemaRequest){
        ModelMapper modelMapper = new ModelMapper();
        CinemaDto cinemaDto = modelMapper.map(cinemaRequest, CinemaDto.class);
        CinemaDto newCinemaDto = cinemaService.updateNewCinema(id, cinemaDto);
        CinemaRespense cinemaRespense = modelMapper.map(newCinemaDto, CinemaRespense.class);

        return  new ResponseEntity<CinemaRespense>(cinemaRespense, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CinemaRespense> deleteCinema(@PathVariable Long id){
        cinemaService.deteleCinema(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
