package com.mystore.wishlist.core.service.impl;

import com.mystore.shared.core.auth.StoreAuthProvider;
import com.mystore.shared.core.exception.BusinessException;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.factory.WishlistFactory;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.service.GetProductsFromWishlistService;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistInput;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

@Log
@RequiredArgsConstructor
public class GetProductsFromWishlistServiceImpl implements GetProductsFromWishlistService {

    private final StoreAuthProvider storeAuthProvider;
    private final WishlistDataSource wishlistDataSource;

    @Override
    public GetProductsFromWishlistOutput getAllProducts(GetProductsFromWishlistInput input) {
        // get wishlist
        var wishlist = getWishlist(input);

        return WishlistFactory.convert(wishlist);
    }

    private Wishlist getWishlist(GetProductsFromWishlistInput input) {
        var currentUser = storeAuthProvider.getCurrentUser();
        var useDefaultWishlist = StringUtils.isBlank(input.wishlistId());

        var wishlistOutputDS = useDefaultWishlist
                ? wishlistDataSource.getDefaultByClientId(currentUser.clientId())
                : wishlistDataSource.getByWishlistId(input.wishlistId());

        if (wishlistOutputDS == null || !wishlistOutputDS.clientId().equals(currentUser.clientId())) {
            throw BusinessException.fromErrorCode("Wishlist.notFound");
        }

        return WishlistFactory.create(wishlistOutputDS, currentUser);
    }

}
