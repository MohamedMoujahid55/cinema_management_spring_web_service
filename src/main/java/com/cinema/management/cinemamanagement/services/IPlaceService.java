package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.PlaceDto;


import java.util.List;

public interface IPlaceService {
    PlaceDto addNewPlace(PlaceDto placeDto);
    PlaceDto getPlacebyName(int number);
    PlaceDto getPlaceById(Long id);
    List<PlaceDto> getPlaces(int page, int size);
    PlaceDto updateNewPlace(Long id, PlaceDto placeDto);
    void detelePlace(Long id);
}
