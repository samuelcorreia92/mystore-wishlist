package com.mystore.wishlist.core.service.impl;

import com.mystore.shared.core.auth.StoreAuthProvider;
import com.mystore.shared.core.event.IEventDispatcher;
import com.mystore.shared.core.exception.BusinessException;
import com.mystore.shared.core.util.ValidationUtil;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.event.ProductRemovedFromWishlistEvent;
import com.mystore.wishlist.core.factory.WishlistFactory;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.service.RemoveProductFromWishlistService;
import com.mystore.wishlist.core.service.dto.RemoveProductInput;
import com.mystore.wishlist.core.service.dto.RemoveProductOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log
@RequiredArgsConstructor
public class RemoveProductFromWishlistServiceImpl implements RemoveProductFromWishlistService {

    private final IEventDispatcher eventDispatcher;
    private final StoreAuthProvider storeAuthProvider;
    private final WishlistDataSource wishlistDataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RemoveProductOutput removeProduct(RemoveProductInput input) {
        // validate input
        validateInput(input);

        // get wishlist
        var wishlist = getWishlist(input);

        // remove product
        var isProductInWishlist = wishlist.hasProduct(input.productId());
        if (isProductInWishlist) {
            wishlist.removeProduct(input.productId());
            this.wishlistDataSource.save(wishlist);

            // send event
            eventDispatcher.sendEvent(new ProductRemovedFromWishlistEvent(wishlist, input.productId()));
        }

        return new RemoveProductOutput(
                wishlist.getId(), wishlist.getName(), isProductInWishlist ? input.productId() : null
        );
    }

    private static void validateInput(RemoveProductInput input) {
        ValidationUtil.init()
                .notNullOrEmpty(input.productId(), "productId", "productId.required")
                .notNullOrEmpty(input.wishlistId(), "wishlistId", "wishlistId.required")
                .build(true);
    }

    private Wishlist getWishlist(RemoveProductInput input) {
        var currentUser = storeAuthProvider.getCurrentUser();

        var wishlistOutputDS = wishlistDataSource.getByWishlistId(input.wishlistId());
        if (wishlistOutputDS == null || !wishlistOutputDS.clientId().equals(currentUser.clientId())) {
            throw BusinessException.fromErrorCode("Wishlist.notFound");
        }

        return WishlistFactory.create(wishlistOutputDS, currentUser);
    }

}
