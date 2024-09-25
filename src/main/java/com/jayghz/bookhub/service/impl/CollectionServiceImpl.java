package com.jayghz.bookhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.jayghz.bookhub.model.entity.Collection;
import com.jayghz.bookhub.repository.CollectionRepository;
import com.jayghz.bookhub.service.CollectionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {
    
    private final CollectionRepository collectionRepository;

    @Transactional
    @Override
    public Collection createCollection(Collection collection) {
        collection.setCreatedAt(LocalDateTime.now());
        return collectionRepository.save(collection);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Collection> getCollectionsByCustomer(Integer customerId) {
        return collectionRepository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection getCollectionById(Integer collectionId) {
        return collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RuntimeException("Collection not found with id: " + collectionId));
    }

    @Transactional
    @Override
    public Collection updateCollection(Integer collectionId, Collection collection) {
        Collection collectionToUpdate = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RuntimeException("Collection not found with id: " + collectionId));
        collectionToUpdate.setName(collection.getName());
        collectionToUpdate.setUpdatedAt(LocalDateTime.now());
        return collectionRepository.save(collectionToUpdate);
    }

    @Transactional
    @Override
    public void deleteCollection(Integer collectionId) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RuntimeException("Collection not found with id: " + collectionId));
        collectionRepository.delete(collection);        
    }

}
