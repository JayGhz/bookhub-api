package com.jayghz.bookhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import com.jayghz.bookhub.dto.AuthorDTO;
import com.jayghz.bookhub.mapper.AuthorMapper;
import com.jayghz.bookhub.model.entity.Author;
import com.jayghz.bookhub.exception.*;

import com.jayghz.bookhub.repository.AuthorRepository;
import com.jayghz.bookhub.service.AdminAuthorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminAuthorServiceImpl implements AdminAuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDTO> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AuthorDTO> paginate(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(authorMapper::toDTO);
    }


    @Transactional(readOnly = true)
    @Override
    public AuthorDTO findById(Integer id) {
       Author author = authorRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Author with ID " + id + " not found"));
       return authorMapper.toDTO(author);
    }

    @Transactional
    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {
        authorRepository.findByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName())
                        .ifPresent(existingAuthor ->{
                            throw new BadRequestException("An author with the same first name and last name already exists");
                        });

        Author author = authorMapper.toEntity(authorDTO);
        author.setCreatedAt(LocalDateTime.now());
        author = authorRepository.save(author);
        return authorMapper.toDTO(author);
    }

    @Transactional
    @Override
    public AuthorDTO update(Integer id, AuthorDTO updateAuthorDTO) {
        Author authorFromDb = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with ID " + id + " not found"));


        authorRepository.findByFirstNameAndLastName(updateAuthorDTO.getFirstName(), updateAuthorDTO.getLastName())
                .filter(existingAuthor -> !existingAuthor.getId().equals(id))
                .ifPresent(existingAuthor -> {
                    throw new BadRequestException("Author with the same first name and last name already exists");
                });

        // Actualizar los campos
        authorFromDb.setFirstName(updateAuthorDTO.getFirstName());
        authorFromDb.setLastName(updateAuthorDTO.getLastName());
        authorFromDb.setBio(updateAuthorDTO.getBio());
        authorFromDb.setUpdatedAt(LocalDateTime.now());

        authorFromDb = authorRepository.save(authorFromDb);
        return authorMapper.toDTO(authorFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with ID " + id + " not found"));
        authorRepository.delete(author);
    }
}
