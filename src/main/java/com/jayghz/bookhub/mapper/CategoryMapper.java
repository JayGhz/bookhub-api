package com.jayghz.bookhub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jayghz.bookhub.dto.CategoryDTO;
import com.jayghz.bookhub.model.entity.Category;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convertir de entidad a DTO
    public CategoryDTO toDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    // Convertir de DTO a entidad
    public Category toEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}
