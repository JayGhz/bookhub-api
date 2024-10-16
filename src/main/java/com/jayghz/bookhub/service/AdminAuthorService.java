package com.jayghz.bookhub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jayghz.bookhub.dto.AuthorDTO;

public interface AdminAuthorService {
    List<AuthorDTO> getAll();
    Page<AuthorDTO> paginate(Pageable pageable);
    AuthorDTO findById(Integer id);
    AuthorDTO create(AuthorDTO AuthorDTO);
    AuthorDTO update(Integer id, AuthorDTO updateAuthorDTO);
    void delete(Integer id);
}
