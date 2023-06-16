package com.mystore.wishlist.core.event;

import com.mystore.shared.core.event.EventBase;
import com.mystore.wishlist.core.entity.Wishlist;

public class ProductAddedToWishlistEvent extends EventBase<ProductAddedToWishlistEvent.Payload> {

    public ProductAddedToWishlistEvent(Wishlist wishlist, String productIdAdded) {
        super(new Payload(
                wishlist.getId(),
                wishlist.getName(),
                wishlist.getClientId(),
                productIdAdded
        ));
    }

    public record Payload(
            String wishlistId,
            String wishlistName,
            String cliendId,
            String productIdAdded
    ) {
    }

}
