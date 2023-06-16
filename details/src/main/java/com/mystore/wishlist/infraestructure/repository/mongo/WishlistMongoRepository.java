package com.mystore.wishlist.infraestructure.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistMongoRepository extends MongoRepository<WishlistDocument, String> {

    @Query("{ 'clientId' : ?0, 'isDefault' : true }")
    Optional<WishlistDocument> findDefaultByClientId(String clientId);

}
