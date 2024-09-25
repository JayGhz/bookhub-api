package com.jayghz.bookhub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jayghz.bookhub.dto.AuthorDTO;
import com.jayghz.bookhub.model.entity.Author;

@Component
public class AuthorMapper {
    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convertir de entidad a DTO
    public AuthorDTO toDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }

    // Convertir de DTO a entidad
    public Author toEntity(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }
}
