package com.mystore.wishlist.core.service.dto;

import java.time.LocalDateTime;
import java.util.Collection;

public record GetProductsFromWishlistOutput(
        String wishlistId,
        String wishlistName,
        boolean defaultWishlist,
        int productsCount,
        Collection<Product> productList
) {

    public record Product(String productId, LocalDateTime productAddedAt) {
    }

}
