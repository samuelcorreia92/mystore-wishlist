package com.mystore.wishlist.core.service.dto;

public record RemoveProductInput(
        String wishlistId,
        String productId
) {
}
