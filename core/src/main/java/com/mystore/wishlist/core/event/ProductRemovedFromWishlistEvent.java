package com.mystore.wishlist.core.event;

import com.mystore.shared.core.event.EventBase;
import com.mystore.wishlist.core.entity.Wishlist;

public class ProductRemovedFromWishlistEvent extends EventBase<ProductRemovedFromWishlistEvent.Payload> {

    public ProductRemovedFromWishlistEvent(Wishlist wishlist, String productIdRemoved) {
        super(new Payload(
                wishlist.getId(),
                wishlist.getName(),
                wishlist.getClientId(),
                productIdRemoved
        ));
    }

    public record Payload(
            String wishlistId,
            String wishlistName,
            String cliendId,
            String productIdRemoved
    ) {
    }

}
