package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.CategorieDto;
import com.cinema.management.cinemamanagement.entities.Categorie;
import com.cinema.management.cinemamanagement.repositories.CategorieRepository;
import com.cinema.management.cinemamanagement.services.ICategorieService;
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
public class CategorieServiceImpl implements ICategorieService {
    private CategorieRepository categorieRepository;

    @Override
    public CategorieDto addNewCategory(CategorieDto categorieDto) {
        ModelMapper modelMapper = new ModelMapper();
        Categorie categorie = modelMapper.map(categorieDto, Categorie.class);
        Categorie newCategorie = categorieRepository.save(categorie);
        CategorieDto categorieDtoNew = modelMapper.map(newCategorie, CategorieDto.class);
        return categorieDtoNew;
    }

    @Override
    public CategorieDto getCategoriebyName(CategorieDto categorieDto) {
        Categorie categorie = categorieRepository.findByName(categorieDto.getName());

        return new ModelMapper().map(categorie, CategorieDto.class);
    }

    @Override
    public CategorieDto getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        return new ModelMapper().map(categorie, CategorieDto.class);
    }

    @Override
    public List<CategorieDto> getCategories(int page, int size) {

        ModelMapper modelMapper = new ModelMapper();
        Page<Categorie> categoriePage = categorieRepository.findAll(PageRequest.of(page, size));
        List<Categorie> categorieList = categoriePage.getContent();
        List<CategorieDto> categorieDtos = new ArrayList<>();

        categorieList.forEach(categorie -> {
            categorieDtos.add(modelMapper.map(categorie, CategorieDto.class));
        });
        return categorieDtos;
    }

    @Override
    public CategorieDto updateNewCategory(Long id, CategorieDto categorieDto) {

        Categorie categorie = categorieRepository.findById(id).orElse(null);
        categorie.setName(categorieDto.getName());
        Categorie newCategorie = categorieRepository.save(categorie);

        return new ModelMapper().map(newCategorie, CategorieDto.class);
    }

    @Override
    public void deteleCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
