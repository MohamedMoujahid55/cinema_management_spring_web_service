package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.ProjectionDto;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface IProjectionFilmService {
    ProjectionDto addNewProjectionFilm(ProjectionDto projectionDto);
    ProjectionDto getProjectionFilmByDate(ProjectionDto projectionDto);
    ProjectionDto getProjectionById(Long id);
    List<ProjectionDto> getProjectionFilms(int page, int size);
    ProjectionDto updateProjectionFilm(Long id, ProjectionDto projectionDto);
    void deteleProjectionFilm(Long id);
}
