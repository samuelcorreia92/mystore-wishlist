package com.mystore.wishlist.core.service.dto;

public record AddProductInput(
        String wishlistId,
        String productId
) {
}
