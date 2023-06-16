package com.mystore.wishlist.apprest.dto;

public record AddProductToWishlistResponseOk(
        String wishlistId,
        String wishlistName,
        String productIdAdded
) implements AddProductToWishlistResponse {
}
