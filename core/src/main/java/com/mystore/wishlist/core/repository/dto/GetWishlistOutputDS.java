package com.mystore.wishlist.core.repository.dto;

import java.time.LocalDateTime;
import java.util.Collection;

public record GetWishlistOutputDS(
        String id,
        String name,
        String clientId,
        boolean isDefault,
        Collection<Product> productList
) {

    public record Product(
            String productId,
            LocalDateTime productAddedAt
    ) {
    }

}
