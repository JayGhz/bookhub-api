package com.jayghz.bookhub.api;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.model.entity.Collection;
import com.jayghz.bookhub.service.CollectionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;
    
    @PostMapping()
    public ResponseEntity<Collection> createCollection(@RequestBody Collection collection) {
        Collection saveCollection = collectionService.createCollection(collection);
        return new ResponseEntity<>(saveCollection, HttpStatus.CREATED);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Collection>> getCollectionsByCustomerId(@PathVariable Integer customerId) {
        List<Collection> collections = collectionService.getCollectionsByCustomer(customerId);
        return ResponseEntity.ok(collections);
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable Integer collectionId) {
        Collection collection = collectionService.getCollectionById(collectionId);
        return ResponseEntity.ok(collection);
    }

    @PutMapping("/{collectionId}")
    public ResponseEntity<Collection> updateCollection(@PathVariable Integer collectionId, @RequestBody Collection collection) {
        Collection updatedCollection = collectionService.updateCollection(collectionId, collection);
        return ResponseEntity.ok(updatedCollection);
    }

    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Integer collectionId) {
        collectionService.deleteCollection(collectionId);
        return ResponseEntity.noContent().build();
    }
}
