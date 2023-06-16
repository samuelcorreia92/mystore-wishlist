package com.mystore.wishlist.core.service.dto;

public record HasProductInWishlistInput(
        String wishlistId,
        String productId
) {
}
