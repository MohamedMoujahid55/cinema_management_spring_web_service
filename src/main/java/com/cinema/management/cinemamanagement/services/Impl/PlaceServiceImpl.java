package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.PlaceDto;
import com.cinema.management.cinemamanagement.entities.Place;
import com.cinema.management.cinemamanagement.repositories.PlaceRepository;
import com.cinema.management.cinemamanagement.services.IPlaceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PlaceServiceImpl implements IPlaceService {
    private PlaceRepository placeRepository;
    @Override
    public PlaceDto addNewPlace(PlaceDto placeDto) {
        ModelMapper modelMapper = new ModelMapper();
        Place place = modelMapper.map(placeDto, Place.class);
        Place newPlace = placeRepository.save(place);
        PlaceDto NewplaceDto = modelMapper.map(newPlace, PlaceDto.class);
        return NewplaceDto;
    }

    @Override
    public PlaceDto getPlacebyName(int number) {
        Place place = placeRepository.findByNumero(number);
        return new ModelMapper().map(place, PlaceDto.class);
    }

    @Override
    public PlaceDto getPlaceById(Long id) {
        Place place = placeRepository.findById(id).orElse(null);
        return new ModelMapper().map(place, PlaceDto.class);
    }

    @Override
    public List<PlaceDto> getPlaces(int page, int size) {

        Page<Place> placePage = placeRepository.findAll(PageRequest.of(page, size));
        List<Place> placeList = placePage.getContent();
        ModelMapper modelMapper = new ModelMapper();
        List<PlaceDto> placeDtos = new ArrayList<>();
        placeList.forEach(place -> {
            placeDtos.add(modelMapper.map(place, PlaceDto.class));
        });
        return placeDtos;
    }

    @Override
    public PlaceDto updateNewPlace(Long id, PlaceDto placeDto) {
        Place place = placeRepository.findById(id).orElse(null);

        place.setLongtitude(placeDto.getLongtitude());
        place.setLatitude(placeDto.getLatitude());
        place.setAltitude(placeDto.getAltitude());
        place.setNumero(placeDto.getNumero());

        if(placeDto.getSalle().getId() != null){
            place.setSalle(placeDto.getSalle());
        }

        Place newPlace = placeRepository.save(place);
        ModelMapper modelMapper = new ModelMapper();
        PlaceDto placeDtoNew = modelMapper.map(newPlace, PlaceDto.class);

        return placeDtoNew;
    }

    @Override
    public void detelePlace(Long id) {
        placeRepository.deleteById(id);
    }
}
