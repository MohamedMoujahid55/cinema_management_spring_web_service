package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.VilleDto;
import com.cinema.management.cinemamanagement.entities.Ville;
import com.cinema.management.cinemamanagement.repositories.VilleRepository;
import com.cinema.management.cinemamanagement.services.IVilleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class VilleServiceImpl implements IVilleService {
    private VilleRepository villeRepository;
    @Override
    public VilleDto addNewVille(VilleDto villeDto) {

        ModelMapper modelMapper = new ModelMapper();
        Ville ville = modelMapper.map(villeDto, Ville.class);
        Ville newVilleEntity = villeRepository.save(ville);
        VilleDto newVilleDto = modelMapper.map(newVilleEntity, VilleDto.class);
        return newVilleDto;
    }

    @Override
    public VilleDto getVilleById(Long id) {
        Ville ville = villeRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        VilleDto villeDto = modelMapper.map(ville, VilleDto.class);
        return villeDto;
    }

    @Override
    public Ville getVillebyName(String name) {
        Ville ville = villeRepository.findByName(name);

        return ville;
    }

    @Override
    public List<VilleDto> getVilles(int page, int size) {

        Page<Ville> villePage = villeRepository.findAll(PageRequest.of(page,size));
        List<Ville> villeList = villePage.getContent();
        ModelMapper modelMapper = new ModelMapper();
        List<VilleDto> villeDtos = new ArrayList<>();
        villeList.forEach(ville -> {
           villeDtos.add(modelMapper.map(ville, VilleDto.class)) ;
        });
        return villeDtos;
    }

    @Override
    public VilleDto updateNewVille(VilleDto villeDto) {
        ModelMapper modelMapper = new ModelMapper();
        Ville ville = modelMapper.map(villeDto, Ville.class);

        Ville villeUpdated = villeRepository.save(ville);

        VilleDto villeDtoUpdated = modelMapper.map(villeUpdated, VilleDto.class);
        return villeDtoUpdated;
    }

    @Override
    public void deteleVille(Long id) {
        villeRepository.deleteById(id);
    }
}
