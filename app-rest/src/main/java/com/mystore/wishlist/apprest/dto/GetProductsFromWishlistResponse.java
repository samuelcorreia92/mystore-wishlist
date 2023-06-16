package com.mystore.wishlist.apprest.dto;

import java.time.LocalDateTime;
import java.util.Collection;

public record GetProductsFromWishlistResponse(Wishlist wishlist, Collection<Product> productList) {

    public record Wishlist(String id, String name, boolean isDefault) {
    }

    public record Product(String productId, LocalDateTime productAddedAt) {
    }

}
