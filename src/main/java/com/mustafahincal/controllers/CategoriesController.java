package com.mustafahincal.controllers;

import com.mustafahincal.business.abstracts.CategoryService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
@CrossOrigin
public class CategoriesController {
    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<Category>> getAll() {
        return this.categoryService.getAll();

    }

}
