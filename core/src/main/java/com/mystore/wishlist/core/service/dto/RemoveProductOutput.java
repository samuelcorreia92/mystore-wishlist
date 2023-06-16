package com.mystore.wishlist.core.service.dto;

public record RemoveProductOutput(
        String wishlistId,
        String wishlistName,
        String productIdRemoved
) {
}
