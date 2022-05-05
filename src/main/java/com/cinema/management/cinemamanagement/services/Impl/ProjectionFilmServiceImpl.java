package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.ProjectionDto;
import com.cinema.management.cinemamanagement.entities.ProjectionFilm;
import com.cinema.management.cinemamanagement.repositories.ProjectionFilmRepository;
import com.cinema.management.cinemamanagement.services.IProjectionFilmService;
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
public class ProjectionFilmServiceImpl implements IProjectionFilmService {
    private ProjectionFilmRepository projectionFilmRepository;
    @Override
    public ProjectionDto addNewProjectionFilm(ProjectionDto projectionDto) {
        ModelMapper modelMapper = new ModelMapper();
        ProjectionFilm projectionFilm = modelMapper.map(projectionDto, ProjectionFilm.class);
        ProjectionFilm newProjection = projectionFilmRepository.save(projectionFilm);
        return modelMapper.map(newProjection, ProjectionDto.class);
    }

    @Override
    public ProjectionDto getProjectionFilmByDate(ProjectionDto projectionDto) {
        ProjectionFilm film = projectionFilmRepository.findByDateProjection(projectionDto.getDateProjection());
        return new ModelMapper().map(film, ProjectionDto.class);
    }

    @Override
    public ProjectionDto getProjectionById(Long id) {
        ProjectionFilm projectionFilm = projectionFilmRepository.findById(id).orElse(null);
        return new ModelMapper().map(projectionFilm, ProjectionDto.class);
    }

    @Override
    public List<ProjectionDto> getProjectionFilms(int page, int size) {

        Page<ProjectionFilm> projectionFilmPage = projectionFilmRepository.findAll(PageRequest.of(page, size));
        List<ProjectionFilm> projectionFilmList = projectionFilmPage.getContent();
        List<ProjectionDto> projectionDtos = new ArrayList<>();
        ModelMapper modelMapper  = new ModelMapper();

        projectionFilmList.forEach(projectionFilm -> {
            projectionDtos.add(modelMapper.map(projectionFilm, ProjectionDto.class));
        });

        return projectionDtos;
    }

    @Override
    public ProjectionDto updateProjectionFilm(Long id, ProjectionDto projectionDto) {
        ProjectionFilm projectionFilm = projectionFilmRepository.findById(id).orElse(null);

        projectionFilm.setDateProjection(projectionDto.getDateProjection());
        projectionFilm.setPrix(projectionDto.getPrix());

        if(projectionDto.getFilm().getId() != null){
            projectionFilm.setFilm(projectionDto.getFilm());
        }
        if(projectionDto.getSalle().getId() != null){
            projectionFilm.setSalle(projectionDto.getSalle());

        }
        if (projectionDto.getSeance().getId() != null) {
            projectionFilm.setSeance(projectionDto.getSeance());
        }

        ProjectionFilm newProjection = projectionFilmRepository.save(projectionFilm);

        return new ModelMapper().map(newProjection, ProjectionDto.class);
    }

    @Override
    public void deteleProjectionFilm(Long ProjectionFilm_id) {
        projectionFilmRepository.deleteById(ProjectionFilm_id);
    }
}
