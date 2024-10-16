package com.jayghz.bookhub.service.impl;

public class AdminCategoryServiceImpl {

<<<<<<< HEAD
}
=======
import com.jayghz.bookhub.dto.CategoryDTO;
import com.jayghz.bookhub.exception.*;
import com.jayghz.bookhub.mapper.CategoryMapper;
import com.jayghz.bookhub.model.entity.Category;
import com.jayghz.bookhub.repository.CategoryRepository;
import com.jayghz.bookhub.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@RequiredArgsConstructor // Notacion de Lombok para inyectar dependencias
@Service // Notacion para indicar que es un servicio de Spring para ser inyectado
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CategoryDTO> paginate(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con ID " + id + " no fue encontrada"));
        return categoryMapper.toDTO(category);
    }

    @Transactional
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        // Verificar si ya existe una categoría con el mismo nombre
        categoryRepository.findByName(categoryDTO.getName())
                .ifPresent(existingCategory -> {
                    throw new BadRequestException("La categoría ya existe con el mismo nombre");
                });

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreatedAt(LocalDateTime.now());
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }


    @Transactional
    @Override
    public CategoryDTO update(Integer id, CategoryDTO updateCategoryDTO) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con ID " + id + " no fue encontrada"));

        // Verificar si ya existe otra categoría con el mismo nombre
        categoryRepository.findByName(updateCategoryDTO.getName())
                .filter(existingCategory -> !existingCategory.getId().equals(id))
                .ifPresent(existingCategory -> {
                    throw new BadRequestException("Ya existe otra categoría con el mismo nombre");
                });

            categoryFromDb.setName(updateCategoryDTO.getName());
        categoryFromDb.setDescription(updateCategoryDTO.getDescription());
        categoryFromDb.setUpdatedAt(LocalDateTime.now());

        categoryFromDb = categoryRepository.save(categoryFromDb);
        return categoryMapper.toDTO(categoryFromDb);
    }


    @Transactional
    @Override
    public void delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con ID " + id + " no fue encontrada"));
        categoryRepository.delete(category);
    }
}
>>>>>>> develop
