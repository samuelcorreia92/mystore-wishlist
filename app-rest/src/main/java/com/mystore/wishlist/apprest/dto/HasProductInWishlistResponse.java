package com.mystore.wishlist.apprest.dto;

public record HasProductInWishlistResponse(
        String wishlistId,
        String wishlistName,
        String productIdSearched,
        boolean productFound
) {
}
