package com.targa.labs.myboutique.productservice.service;

import com.targa.labs.myboutique.commons.dto.CategoryDto;
import com.targa.labs.myboutique.productservice.domain.Category;
import com.targa.labs.myboutique.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return this.categoryRepository.findById(id).map(CategoryService::mapToDto)
                .orElseThrow(IllegalStateException::new);
    }

    public CategoryDto create(CategoryDto categoryDto) {
        return mapToDto(this.categoryRepository.save(
                new Category(
                        categoryDto.getName(),
                        categoryDto.getDescription()
                )
        ));
    }

    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public static CategoryDto mapToDto(Category category) {
        if (category != null) {
            return new CategoryDto(
                    category.getId(),
                    category.getName(),
                    category.getDescription()
            );
        }
        return null;
    }
}
