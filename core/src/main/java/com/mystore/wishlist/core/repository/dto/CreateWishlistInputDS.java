package com.mystore.wishlist.core.repository.dto;

public record CreateWishlistInputDS(
        String name,
        String clientId,
        boolean isDefault
) {
}
