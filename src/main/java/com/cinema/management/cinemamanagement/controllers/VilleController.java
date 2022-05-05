package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.VilleDto;
import com.cinema.management.cinemamanagement.entities.Ville;
import com.cinema.management.cinemamanagement.requests.VilleRequest;
import com.cinema.management.cinemamanagement.respenses.VilleRespense;
import com.cinema.management.cinemamanagement.services.IVilleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
@AllArgsConstructor
public class VilleController {
    private IVilleService villeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<VilleRespense> getVille(@PathVariable Long id){

        VilleDto villeDto = villeService.getVilleById(id);
        ModelMapper modelMapper = new ModelMapper();
        VilleRespense villeRespense = modelMapper.map(villeDto, VilleRespense.class);
       return new ResponseEntity<VilleRespense>(villeRespense,HttpStatus.OK);
    }
    @GetMapping
    public List<VilleRespense> getVilles(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "3") int size){
        List<VilleRespense> villeRespenses = new ArrayList<>();
        List<VilleDto> villeDtos = villeService.getVilles(page, size);
        villeDtos.forEach(villeDto -> {
            villeRespenses.add(new ModelMapper().map(villeDto, VilleRespense.class));
        });
        return villeRespenses;
    }

    @PostMapping
    public ResponseEntity<VilleRespense> addVille(@RequestBody VilleRequest villeRequest){

        ModelMapper modelMapper = new ModelMapper();
        VilleDto villeDto = modelMapper.map(villeRequest, VilleDto.class);
        VilleDto newVilleDto = villeService.addNewVille(villeDto);
        VilleRespense villeRespense = modelMapper.map(newVilleDto, VilleRespense.class);
        return new ResponseEntity<VilleRespense>(villeRespense, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VilleRespense> updateVille(@PathVariable Long id, @RequestBody VilleRequest villeRequest){

        ModelMapper modelMapper = new ModelMapper();
        VilleDto villeDto = modelMapper.map(villeRequest, VilleDto.class);
        villeDto.setId(villeService.getVilleById(id).getId());
        VilleDto newVilleDto = villeService.updateNewVille(villeDto);
        VilleRespense villeRespense = modelMapper.map(newVilleDto, VilleRespense.class);
        return new ResponseEntity<VilleRespense>(villeRespense, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}") // errore in deleting the record id = 1
    public ResponseEntity deleteVille(@PathVariable Long id){
        villeService.deteleVille(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
