package com.mystore.wishlist.apprest.dto;

public record RemoveProductFromWishlistResponseOk(String wishlistId, String wishlistName, String productIdRemoved)
        implements RemoveProductFromWishlistResponse {
}
