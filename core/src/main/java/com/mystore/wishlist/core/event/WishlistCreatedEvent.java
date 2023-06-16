package com.mystore.wishlist.core.event;

import com.mystore.shared.core.event.EventBase;

public class WishlistCreatedEvent extends EventBase<WishlistCreatedEvent.Payload> {

    public WishlistCreatedEvent(Payload source) {
        super(source);
    }

    public record Payload(
            String wishlistId,
            String wishlistName,
            String cliendId
    ) {
    }

}
