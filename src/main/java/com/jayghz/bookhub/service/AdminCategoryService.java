package com.jayghz.bookhub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jayghz.bookhub.dto.CategoryDTO;

public interface AdminCategoryService {
    List<CategoryDTO> getAll();
    Page<CategoryDTO> paginate(Pageable pageable);  
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO findById(Integer id);
    CategoryDTO update(Integer id, CategoryDTO updateCategoryDTO);
    void delete(Integer id);
}
