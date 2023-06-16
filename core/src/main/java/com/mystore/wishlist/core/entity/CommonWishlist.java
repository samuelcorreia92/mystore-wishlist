package com.mystore.wishlist.core.entity;

import com.mystore.shared.core.exception.BusinessException;
import com.mystore.shared.core.exception.ValidationBusinessException;
import com.mystore.shared.core.util.ValidationUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@SuperBuilder
@ToString
public class CommonWishlist implements Wishlist {

    private String id;
    private String name;
    private String clientId;
    private boolean isDefault;
    private Set<Product> productList;

    @Override
    public boolean hasProduct(String productId) {
        return productList.contains(new Product(productId, null));
    }

    @Override
    public boolean canAddProduct() {
        return productList.size() < 20;
    }

    @Override
    public void addProduct(String productId) {
        if (!canAddProduct()) {
            throw BusinessException.fromErrorCode("Wishlist.productList.maxLength");
        }

        if (!hasProduct(productId)) {
            productList.add(
                    Product.builder()
                            .productId(productId)
                            .productAddedAt(LocalDateTime.now())
                            .build()
            );
        }
    }

    @Override
    public void removeProduct(String productId) {
        // If the product does not exist, nothing happens (no exception is thrown)
        productList.remove(new Product(productId, null));
    }

    @Override
    public void setAsDefault() {
        isDefault = true;
    }

    @Override
    public void setAsNonDefault() {
        isDefault = false;
    }

    @Override
    public CommonWishlist validate() throws ValidationBusinessException {
        ValidationUtil.init()
                // name
                .notNullOrEmpty(name, "name", "Wishlist.name.required")
                .minLength(name, "name", 3, "Wishlist.name.minLength")
                .regex(name, "name", "^[a-zA-Z0-9_()\\s]+$", "Wishlist.name.invalidPattern")

                // clientId
                .notNullOrEmpty(clientId, "clientId", "Wishlist.clientId.required")

                // productList
                .maxLength(productList, "productList", 20, "Wishlist.productList.maxLength")

                .build(true);

        return this;
    }
}
