package com.jayghz.bookhub.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.dto.BookDetailsDTO;
import com.jayghz.bookhub.service.AdminBookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class HomeBookController {

    private final AdminBookService adminBookService;

    // Endpoint para obtener los 6 libros más recientes
    @GetMapping("/recent")
    public ResponseEntity<List<BookDetailsDTO>> getRecentBooks() {
        List<BookDetailsDTO> recentBooks = adminBookService.findTop6BooksByCreatedAt();
        return new ResponseEntity<>(recentBooks, HttpStatus.OK);
    }
}