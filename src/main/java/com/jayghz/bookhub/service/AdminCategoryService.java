package com.jayghz.bookhub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.jayghz.bookhub.model.entity.Category;

public interface AdminCategoryService {
    List<Category> getAll();
    Page<Category> paginate(Pageable pageable);  
    Category create(Category category);
    Category findById(Integer id);
    Category update(Integer id, Category updateCategory);
    void delete(Integer id);
}
