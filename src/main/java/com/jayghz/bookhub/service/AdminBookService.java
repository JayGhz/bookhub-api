package com.jayghz.bookhub.service;

import java.util.List;

import org.springframework.data.domain.*;

import com.jayghz.bookhub.dto.BookCreateUpdateDTO;
import com.jayghz.bookhub.dto.BookDetailsDTO;

public interface AdminBookService {
    List<BookDetailsDTO> findAll();
    Page<BookDetailsDTO> paginate(Pageable pageable);
    BookDetailsDTO findById(Integer id);
    BookDetailsDTO create(BookCreateUpdateDTO bookCreateUpdateDTO);
    BookDetailsDTO update(Integer id, BookCreateUpdateDTO updateBookDTO);
    void delete(Integer id);

    List<BookDetailsDTO> findTop6BooksByCreatedAt();
}
