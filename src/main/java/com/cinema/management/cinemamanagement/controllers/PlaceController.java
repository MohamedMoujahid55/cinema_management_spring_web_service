package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.PlaceDto;
import com.cinema.management.cinemamanagement.repositories.PlaceRepository;
import com.cinema.management.cinemamanagement.requests.PlaceRequest;
import com.cinema.management.cinemamanagement.respenses.PlaceResponse;
import com.cinema.management.cinemamanagement.services.IPlaceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/places") @AllArgsConstructor
public class PlaceController {
    private IPlaceService placeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlaceResponse> getPlace(@PathVariable Long id){
        PlaceDto placeDto = placeService.getPlaceById(id);
        ModelMapper modelMapper = new ModelMapper();
        PlaceResponse placeResponse = modelMapper.map(placeDto, PlaceResponse.class);
        return new ResponseEntity<PlaceResponse>(placeResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<PlaceResponse> getPlaces(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "5") int size){

        List<PlaceDto> placeDtoList = placeService.getPlaces(page, size);
        ModelMapper modelMapper = new ModelMapper();
        List<PlaceResponse> placeResponseList = new ArrayList<>();

        placeDtoList.forEach(placeDto -> {
            placeResponseList.add(modelMapper.map(placeDto, PlaceResponse.class));
        });

        return placeResponseList;
    }

    @PostMapping
    public ResponseEntity<PlaceResponse> addNewPlace(@RequestBody PlaceRequest placeRequest){
        ModelMapper modelMapper = new ModelMapper();
        PlaceDto placeDto = modelMapper.map(placeRequest, PlaceDto.class);
        PlaceDto newPlaceDto = placeService.addNewPlace(placeDto);

        PlaceResponse placeResponse = modelMapper.map(newPlaceDto, PlaceResponse.class);

        return new ResponseEntity<PlaceResponse>(placeResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public  ResponseEntity<PlaceResponse> updatePlace(@PathVariable Long id,
                                                      @RequestBody PlaceRequest placeRequest){
        ModelMapper modelMapper = new ModelMapper();
        PlaceDto placeDto = modelMapper.map(placeRequest, PlaceDto.class);

        PlaceDto placeDtoUpdated = placeService.updateNewPlace(id, placeDto);

        PlaceResponse placeResponse = modelMapper.map(placeDtoUpdated, PlaceResponse.class);

        return  new ResponseEntity<PlaceResponse>(placeResponse, HttpStatus.ACCEPTED);

    }

    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<PlaceResponse> deletePlace(@PathVariable Long id){
        placeService.detelePlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
