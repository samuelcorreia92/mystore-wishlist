package com.mystore.wishlist.core.service.impl;

import com.mystore.shared.core.auth.StoreAuthProvider;
import com.mystore.shared.core.exception.BusinessException;
import com.mystore.shared.core.util.ValidationUtil;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.factory.WishlistFactory;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.service.HasProductInWishlistService;
import com.mystore.wishlist.core.service.dto.HasProductInWishlistInput;
import com.mystore.wishlist.core.service.dto.HasProductInWishlistOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class HasProductInWishlistServiceImpl implements HasProductInWishlistService {

    private final StoreAuthProvider storeAuthProvider;
    private final WishlistDataSource wishlistDataSource;

    @Override
    public HasProductInWishlistOutput hasProductInWishlist(HasProductInWishlistInput input) {
        // validate input
        validateInput(input);

        // get wishlist
        var wishlist = getWishlist(input);

        // check if product is in wishlist
        var isProductInWishlist = wishlist.hasProduct(input.productId());

        return new HasProductInWishlistOutput(
                wishlist.getId(), wishlist.getName(), input.productId(), isProductInWishlist
        );
    }

    private static void validateInput(HasProductInWishlistInput input) {
        ValidationUtil.init()
                .notNullOrEmpty(input.productId(), "productId", "productId.required")
                .notNullOrEmpty(input.wishlistId(), "wishlistId", "wishlistId.required")
                .build(true);
    }

    private Wishlist getWishlist(HasProductInWishlistInput input) {
        var currentUser = storeAuthProvider.getCurrentUser();

        var wishlistOutputDS = wishlistDataSource.getByWishlistId(input.wishlistId());
        if (wishlistOutputDS == null || !wishlistOutputDS.clientId().equals(currentUser.clientId())) {
            throw BusinessException.fromErrorCode("Wishlist.notFound");
        }

        return WishlistFactory.create(wishlistOutputDS, currentUser);
    }

}
