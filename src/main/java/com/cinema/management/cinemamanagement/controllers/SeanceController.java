package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.SeanceDto;
import com.cinema.management.cinemamanagement.requests.SeanceRequest;
import com.cinema.management.cinemamanagement.respenses.SeanceResponse;
import com.cinema.management.cinemamanagement.services.ISeanceService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seances") @AllArgsConstructor
public class SeanceController {
    private ISeanceService seanceService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<SeanceResponse> getSeance(@PathVariable Long id){
        SeanceDto seanceDto = seanceService.getSeanceById(id);
        ModelMapper modelMapper = new ModelMapper();
        SeanceResponse seanceResponse = modelMapper.map(seanceDto, SeanceResponse.class);
        return new ResponseEntity<>(seanceResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<SeanceResponse> getSeances(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "3") int size){

        List<SeanceDto> seanceDtoList = seanceService.getSeances(page, size);
        ModelMapper modelMapper = new ModelMapper();
        List<SeanceResponse> seanceResponseList = new ArrayList<>();

        seanceDtoList.forEach(seanceDto -> {
            seanceResponseList.add(modelMapper.map(seanceDto, SeanceResponse.class));
        });

        return seanceResponseList;
    }

    @PostMapping
    public ResponseEntity<SeanceResponse> addNewSeance(@RequestBody SeanceRequest seanceRequest){
        ModelMapper modelMapper = new ModelMapper();
        SeanceDto seanceDto = modelMapper.map(seanceRequest, SeanceDto.class);

        SeanceDto newSeance = seanceService.addNewSeance(seanceDto);

        SeanceResponse seanceResponse = modelMapper.map(newSeance, SeanceResponse.class);

        return new ResponseEntity<>(seanceResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SeanceResponse> updateSeance(@PathVariable Long id,
                                                       @RequestBody SeanceRequest seanceRequest){
        ModelMapper modelMapper = new ModelMapper();
        SeanceDto seanceDto = modelMapper.map(seanceRequest, SeanceDto.class);
        SeanceDto newSeance = seanceService.updateNewSeance(id, seanceDto);

        SeanceResponse seanceResponse = modelMapper.map(newSeance, SeanceResponse.class);

        return  new ResponseEntity<>(seanceResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SeanceResponse> deleteSeance(@PathVariable Long id){
        seanceService.deteleSeance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
