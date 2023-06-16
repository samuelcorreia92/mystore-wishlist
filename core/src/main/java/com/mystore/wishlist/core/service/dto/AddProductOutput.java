package com.mystore.wishlist.core.service.dto;

public record AddProductOutput(
        String wishlistId,
        String wishlistName,
        String productIdAdded
) {
}
