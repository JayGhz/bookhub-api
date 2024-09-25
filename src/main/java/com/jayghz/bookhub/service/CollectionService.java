package com.jayghz.bookhub.service;

import java.util.List;

import com.jayghz.bookhub.model.entity.Collection;

public interface CollectionService {
    Collection createCollection(Collection collection);
    List<Collection> getCollectionsByCustomer(Integer customerId);
    Collection getCollectionById(Integer collectionId);
    Collection updateCollection(Integer collectionId, Collection collection);
    void deleteCollection(Integer collectionId);
}
