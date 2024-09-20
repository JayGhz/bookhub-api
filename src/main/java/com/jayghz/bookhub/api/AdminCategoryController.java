package com.jayghz.bookhub.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayghz.bookhub.model.entity.Category;
import com.jayghz.bookhub.service.AdminCategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    // Inyeccion de la interfaz AdminCategoryService para que el controlador no
    // dependa directamente de la implementacion
    private final AdminCategoryService adminCategoryService;

    // Metodo para listar todas las categorias
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = adminCategoryService.getAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    // Metodo para listar todas las categorias paginadas
    @GetMapping("/paginate")
    public ResponseEntity<Page<Category>> paginateCategories(
            @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<Category> categories = adminCategoryService.paginate(pageable);
        return new ResponseEntity<Page<Category>>(categories, HttpStatus.OK);

    }

    //Metodo para obtener una categoria por su id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id) {
        Category category = adminCategoryService.findById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    // Metoddo para crear una categoria
    @PostMapping
    // El @requestBody indica que el objeto se recibe en el cuerpo de la peticion
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCategory = adminCategoryService.create(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }
    
    // Metodo para actualizar una categoria
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        Category updatedCategory = adminCategoryService.update(id, category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }

    // Metodo para eliminar una categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id) {
        adminCategoryService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}