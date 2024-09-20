package com.jayghz.bookhub.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> paginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Category create(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category update(Integer id, Category updateCategory) {
        Category category = findById(id);
        category.setName(updateCategory.getName());
        category.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
