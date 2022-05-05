package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.SalleDto;
import com.cinema.management.cinemamanagement.requests.SalleRequest;
import com.cinema.management.cinemamanagement.respenses.SalleResponse;
import com.cinema.management.cinemamanagement.services.ISalleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/salles") @AllArgsConstructor
public class SalleController {
    private ISalleService salleService;

    @GetMapping("/{id}")
    public ResponseEntity<SalleResponse> getSalle(@PathVariable Long id){
        SalleDto salleDto = salleService.getSalleById(id);

        ModelMapper modelMapper = new ModelMapper();
        SalleResponse salleResponse = modelMapper.map(salleDto, SalleResponse.class);

        return new ResponseEntity<>(salleResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<SalleResponse> getSalles(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "3") int size){

        List<SalleDto> salleDtoList = salleService.getSalle(page,size);

        ModelMapper modelMapper = new ModelMapper();
        List<SalleResponse> salleResponseList = new ArrayList<>();

        salleDtoList.forEach(salleDto -> {
            salleResponseList.add(modelMapper.map(salleDto, SalleResponse.class));
        });

        return salleResponseList;
    }

    @PostMapping
    public ResponseEntity<SalleResponse> addNewSalle(@RequestBody SalleRequest salleRequest){
        ModelMapper modelMapper = new ModelMapper();

        SalleDto salleDto = modelMapper.map(salleRequest, SalleDto.class);
        SalleDto newSalleDto = salleService.addNewSalle(salleDto);

        SalleResponse salleResponse = modelMapper.map(newSalleDto, SalleResponse.class);

        return new ResponseEntity<SalleResponse>(salleResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SalleResponse> updateSalle(@PathVariable Long id,
                                                     @RequestBody SalleRequest salleRequest){
        ModelMapper modelMapper = new ModelMapper();
        SalleDto salleDto = modelMapper.map(salleRequest, SalleDto.class);
        SalleDto salleDtoNew = salleService.updateNewSalle(id, salleDto);

        SalleResponse salleResponse = modelMapper.map(salleDtoNew, SalleResponse.class);

        return new ResponseEntity<>(salleResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SalleResponse> deleteSalle(@PathVariable Long id){
        salleService.deteleSalle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
