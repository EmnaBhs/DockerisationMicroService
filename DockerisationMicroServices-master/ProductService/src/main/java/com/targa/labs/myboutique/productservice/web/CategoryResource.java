package com.targa.labs.myboutique.productservice.web;

import com.targa.labs.myboutique.commons.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.targa.labs.myboutique.productservice.service.CategoryService;

import static com.targa.labs.myboutique.commons.utils.Web.API;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public CategoryDto create(CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.categoryService.delete(id);
    }
}
