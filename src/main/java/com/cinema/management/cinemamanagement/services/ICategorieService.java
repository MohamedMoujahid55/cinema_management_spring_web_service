package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.CategorieDto;


import java.util.List;

public interface ICategorieService {
    CategorieDto addNewCategory(CategorieDto categorieDto);
    CategorieDto getCategoriebyName(CategorieDto categorieDto);
    CategorieDto getCategorieById(Long id);
    List<CategorieDto> getCategories(int page, int size);
    CategorieDto updateNewCategory(Long id, CategorieDto categorieDto);
    void deteleCategorie(Long id);
}
