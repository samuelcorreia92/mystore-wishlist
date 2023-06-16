package com.mystore.wishlist.infraestructure.repository.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document("wishlist")
class WishlistDocument {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String clientId;
    private boolean isDefault;
    private List<Product> productList;

    @Getter
    @Setter
    public static class Product {
        private String productId;
        private LocalDateTime productAddedAt;
    }

}
