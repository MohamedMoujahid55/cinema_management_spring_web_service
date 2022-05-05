package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.ProjectionDto;
import com.cinema.management.cinemamanagement.requests.ProjectionRequest;
import com.cinema.management.cinemamanagement.respenses.ProjectionResponse;
import com.cinema.management.cinemamanagement.services.IProjectionFilmService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projections") @AllArgsConstructor
public class ProjectionController {
    private IProjectionFilmService projectionFilmService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectionResponse> getProjection(@PathVariable Long id){

        ProjectionDto projectionDto = projectionFilmService.getProjectionById(id);
        ModelMapper modelMapper = new ModelMapper();
        ProjectionResponse projectionResponse = modelMapper.map(projectionDto, ProjectionResponse.class);

        return  new ResponseEntity<ProjectionResponse>(projectionResponse, HttpStatus.OK);
    }

    @GetMapping
    public  List<ProjectionResponse> getProjections(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "3") int size){
        List<ProjectionDto> projectionDtos = projectionFilmService.getProjectionFilms(page, size);
        List<ProjectionResponse> projectionResponses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        projectionDtos.forEach(projectionDto -> {
            projectionResponses.add(modelMapper.map(projectionDto, ProjectionResponse.class));
        });
        return projectionResponses;
    }

    @PostMapping
    public ResponseEntity<ProjectionResponse> addNewProjection(@RequestBody ProjectionRequest projectionRequest){

        ModelMapper modelMapper = new ModelMapper();
        ProjectionDto projectionDto = modelMapper.map(projectionRequest, ProjectionDto.class);
        ProjectionDto newProjectionDto = projectionFilmService.addNewProjectionFilm(projectionDto);
        ProjectionResponse projectionResponse = modelMapper.map(newProjectionDto, ProjectionResponse.class);

        return new ResponseEntity<>(projectionResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectionResponse> updateProjection(@PathVariable Long id,
                                                               @RequestBody ProjectionRequest projectionRequest){

        ModelMapper modelMapper = new ModelMapper();
        ProjectionDto projectionDto = modelMapper.map(projectionRequest, ProjectionDto.class);
        ProjectionDto newProjectionDto = projectionFilmService.updateProjectionFilm(id, projectionDto);
        ProjectionResponse projectionResponse = modelMapper.map(newProjectionDto, ProjectionResponse.class);

        return new ResponseEntity<>(projectionResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjectionResponse> deleteProjection(@PathVariable Long id){
        projectionFilmService.deteleProjectionFilm(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
