package com.jayghz.bookhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jayghz.bookhub.model.entity.Author;
import com.jayghz.bookhub.repository.AuthorRepository;
import com.jayghz.bookhub.service.AdminAuthorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminAuthorServiceImpl implements AdminAuthorService{
    
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Author> paginate(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));        
    }

    @Transactional
    @Override
    public Author create(Author author) {
        author.setCreatedAt(LocalDateTime.now());
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author update(Integer id, Author updateAuthor) {
        Author author = findById(id);
        author.setFirstName(updateAuthor.getFirstName());
        author.setLastName(updateAuthor.getLastName());
        author.setBio(updateAuthor.getBio());
        author.setUpdatedAt(LocalDateTime.now());
        return authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) {
        Author auhtor = findById(id);
        authorRepository.delete(auhtor);
    }

}
