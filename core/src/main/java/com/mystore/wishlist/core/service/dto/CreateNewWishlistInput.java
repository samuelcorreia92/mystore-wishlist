package com.mystore.wishlist.core.service.dto;

public record CreateNewWishlistInput(
        boolean isDefault,
        String name
) {
}
