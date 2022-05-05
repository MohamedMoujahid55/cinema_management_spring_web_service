package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.FilmDto;
import com.cinema.management.cinemamanagement.entities.Categorie;
import com.cinema.management.cinemamanagement.entities.Film;
import com.cinema.management.cinemamanagement.repositories.CategorieRepository;
import com.cinema.management.cinemamanagement.repositories.FilmRepository;
import com.cinema.management.cinemamanagement.services.IFilmService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FilmServiceImpl implements IFilmService {
    private FilmRepository filmRepository;

    @Override
    public FilmDto addNewFilm(FilmDto filmDto) {
        ModelMapper modelMapper = new ModelMapper();
        Film film = modelMapper.map(filmDto, Film.class);
        Film newFilm = filmRepository.save(film);
        FilmDto filmDtoNew = modelMapper.map(newFilm, FilmDto.class);
        return filmDtoNew;
    }

    @Override
    public FilmDto getFilmbyName(FilmDto filmDto) {
       Film film = filmRepository.findByTitre(filmDto.getTitre());

       return new ModelMapper().map(film, FilmDto.class);
    }

    @Override
    public FilmDto getFilmById(Long id) {
        Film film = filmRepository.findById(id).orElse(null);

        return new ModelMapper().map(film, FilmDto.class);
    }

    @Override
    public List<FilmDto> getFilms(int page, int size) {
        ModelMapper modelMapper = new ModelMapper();
        Page<Film> filmPage = filmRepository.findAll(PageRequest.of(page, size));
        List<Film> filmList = filmPage.getContent();
        List<FilmDto> filmDtos = new ArrayList<>();

        filmList.forEach(film -> {
            filmDtos.add(modelMapper.map(film, FilmDto.class));
        });

        return filmDtos;
    }

    @Override
    public FilmDto updateNewFilm(Long id, FilmDto filmDto) {

       Film film = filmRepository.findById(id).orElse(null);

       film.setCategorie(filmDto.getCategorie());
       film.setDateSortie(filmDto.getDateSortie());
       film.setDescription(filmDto.getDescription());
       film.setDuree(filmDto.getDuree());
       film.setPhoto(filmDto.getPhoto());
       film.setTitre(filmDto.getTitre());
       film.setRealisateur(filmDto.getRealisateur());

       Film newFilm = filmRepository.save(film);

       return new ModelMapper().map(newFilm, FilmDto.class);
    }

    @Override
    public void deteleFilm(Long id) {
        filmRepository.deleteById(id);
    }
}
