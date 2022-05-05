package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.CategorieDto;
import com.cinema.management.cinemamanagement.requests.CategorieRequest;
import com.cinema.management.cinemamanagement.respenses.CategorieResponse;
import com.cinema.management.cinemamanagement.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories") @AllArgsConstructor
public class CategorieController {
    private ICategorieService categorieService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategorieResponse> getCategorie(@PathVariable Long id){

        CategorieDto categorieDto = categorieService.getCategorieById(id);
        CategorieResponse categorieResponse = new ModelMapper().map(categorieDto, CategorieResponse.class);

        return new ResponseEntity<>(categorieResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<CategorieResponse> getCategories(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size){

        List<CategorieDto> categorieDtos = categorieService.getCategories(page, size);
        ModelMapper modelMapper = new ModelMapper();
        List<CategorieResponse> categorieResponses = new ArrayList<>();

        categorieDtos.forEach(categorieDto -> {
            categorieResponses.add(modelMapper.map(categorieDto, CategorieResponse.class));
        });

        return categorieResponses;
    }

    @PostMapping
    public ResponseEntity<CategorieResponse> addNewCategorie(@RequestBody CategorieRequest categorieRequest){

        ModelMapper modelMapper = new ModelMapper();
        CategorieDto categorieDto = modelMapper.map(categorieRequest, CategorieDto.class);
        CategorieDto newCategoriDto = categorieService.addNewCategory(categorieDto);
        CategorieResponse categorieResponse = modelMapper.map(newCategoriDto, CategorieResponse.class);

        return new ResponseEntity<>(categorieResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategorieResponse> updateCategorie(@PathVariable Long id,
                                                             @RequestBody CategorieRequest categorieRequest){
        ModelMapper modelMapper = new ModelMapper();
        CategorieDto categorieDto = modelMapper.map(categorieRequest, CategorieDto.class);
        CategorieDto categorieDtoNew = categorieService.updateNewCategory(id, categorieDto);
        CategorieResponse categorieResponse = modelMapper.map(categorieDtoNew, CategorieResponse.class);

        return new ResponseEntity<>(categorieResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CategorieResponse> deleteCategorie(@PathVariable Long id){
        categorieService.deteleCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
