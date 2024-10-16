package com.jayghz.bookhub.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayghz.bookhub.dto.CategoryDTO;
import com.jayghz.bookhub.service.AdminCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    // Metodo para listar todas las categorias
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = adminCategoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Metodo para listar todas las categorias paginadas
    @GetMapping("/paginate")
    public ResponseEntity<Page<CategoryDTO>> paginateCategories(
            @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<CategoryDTO> categories = adminCategoryService.paginate(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    //Metodo para obtener una categoria por su id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Integer id) {
        CategoryDTO category = adminCategoryService.findById(id);
        return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
    }

    // Metoddo para crear una categoria
    @PostMapping
    // El @requestBody indica que el objeto se recibe en el cuerpo de la peticion
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategoryDTO = adminCategoryService.create(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }
    
    // Metodo para actualizar una categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = adminCategoryService.update(id, categoryDTO);
        return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
    }

    // Metodo para eliminar una categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
        adminCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
