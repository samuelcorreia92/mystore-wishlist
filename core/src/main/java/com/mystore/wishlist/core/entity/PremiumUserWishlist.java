package com.mystore.wishlist.core.entity;

import com.mystore.shared.core.exception.ValidationBusinessException;
import com.mystore.shared.core.util.ValidationUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString
public class PremiumUserWishlist extends CommonWishlist {

    @Override
    public boolean canAddProduct() {
        // Premium users can add unlimited products to their wishlist
        return true;
    }

    @Override
    public PremiumUserWishlist validate() throws ValidationBusinessException {
        ValidationUtil.init()
                // name
                .notNullOrEmpty(getName(), "name", "Wishlist.name.required")
                .minLength(getName(), "name", 3, "Wishlist.name.minLength")
                .regex(getName(), "name", "^[a-zA-Z0-9_()\\s]+$", "Wishlist.name.invalidPattern")

                // clientId
                .notNullOrEmpty(getClientId(), "clientId", "Wishlist.clientId.required")

                .build(true);

        return this;
    }
}
