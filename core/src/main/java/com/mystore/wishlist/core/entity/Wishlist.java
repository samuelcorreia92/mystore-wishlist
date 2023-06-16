package com.mystore.wishlist.core.entity;

import com.mystore.shared.core.entity.IBusinessEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Wishlist extends IBusinessEntity {

    String getId();

    String getName();

    String getClientId();

    boolean hasProduct(String productId);

    boolean canAddProduct();

    void addProduct(String productId);

    void removeProduct(String productId);

    boolean isDefault();

    void setAsDefault();

    void setAsNonDefault();

    Collection<Product> getProductList();

    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(of = "productId")
    class Product {
        private String productId;
        private LocalDateTime productAddedAt;
    }

}
