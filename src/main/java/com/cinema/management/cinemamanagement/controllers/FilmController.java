package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.FilmDto;
import com.cinema.management.cinemamanagement.requests.FilmRequest;
import com.cinema.management.cinemamanagement.respenses.FilmResponse;
import com.cinema.management.cinemamanagement.services.IFilmService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films") @AllArgsConstructor
public class FilmController {
    private IFilmService filmService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmResponse> getFilm(@PathVariable Long id){
        FilmDto filmDto = filmService.getFilmById(id);
        ModelMapper modelMapper = new ModelMapper();
        FilmResponse filmResponse = modelMapper.map(filmDto, FilmResponse.class);
        return new ResponseEntity<>(filmResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<FilmResponse> getFilms(@RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "3") int size){

        List<FilmDto> filmDtos = filmService.getFilms(page, size);
        ModelMapper modelMapper = new ModelMapper();
        List<FilmResponse> filmResponses = new ArrayList<>();

        filmDtos.forEach(filmDto -> {
            filmResponses.add(modelMapper.map(filmDto, FilmResponse.class));
        });

        return filmResponses;
    }

    @PostMapping
    public ResponseEntity<FilmResponse> addNewFilm(@RequestBody FilmRequest filmRequest){
        ModelMapper modelMapper = new ModelMapper();
        FilmDto filmDto = modelMapper.map(filmRequest, FilmDto.class);

        FilmDto newFilm = filmService.addNewFilm(filmDto);

        FilmResponse filmResponse = modelMapper.map(newFilm, FilmResponse.class);

        return new ResponseEntity<>(filmResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<FilmResponse> updateFilm(@PathVariable Long id,
                                                   @RequestBody FilmRequest filmRequest){

        ModelMapper modelMapper = new ModelMapper();
        FilmDto filmDto = modelMapper.map(filmRequest, FilmDto.class);
        FilmDto newFilm = filmService.updateNewFilm(id, filmDto);

        FilmResponse filmResponse = modelMapper.map(newFilm, FilmResponse.class);

        return new ResponseEntity<>(filmResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<FilmResponse> deleteFilm(@PathVariable Long id){
        filmService.deteleFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
