package com.mystore.wishlist.core.service.dto;

public record HasProductInWishlistOutput(
        String wishlistId,
        String wishlistName,
        String productIdSearched,
        boolean productFound
) {
}
