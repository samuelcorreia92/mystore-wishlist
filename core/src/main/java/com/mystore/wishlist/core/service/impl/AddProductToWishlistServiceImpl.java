package com.mystore.wishlist.core.service.impl;

import com.mystore.shared.core.auth.StoreAuthProvider;
import com.mystore.shared.core.event.IEventDispatcher;
import com.mystore.shared.core.exception.BusinessException;
import com.mystore.shared.core.util.ValidationUtil;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.event.ProductAddedToWishlistEvent;
import com.mystore.wishlist.core.factory.WishlistFactory;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.service.AddProductToWishlistService;
import com.mystore.wishlist.core.service.CreateNewWishlistService;
import com.mystore.wishlist.core.service.dto.AddProductInput;
import com.mystore.wishlist.core.service.dto.AddProductOutput;
import com.mystore.wishlist.core.service.dto.CreateNewWishlistInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log
@RequiredArgsConstructor
public class AddProductToWishlistServiceImpl implements AddProductToWishlistService {

    private final StoreAuthProvider storeAuthProvider;
    private final CreateNewWishlistService createNewWishlistService;
    private final WishlistDataSource wishlistDataSource;
    private final IEventDispatcher eventDispatcher;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AddProductOutput addNewProduct(AddProductInput input) {
        // validate input
        validateInput(input);

        // get or create wishlist
        var wishlist = getOrCreateWishlist(input);

        // add product
        var isProductInWishlist = wishlist.hasProduct(input.productId());
        if (!isProductInWishlist) {
            wishlist.addProduct(input.productId());
            this.wishlistDataSource.save(wishlist);

            // send event
            eventDispatcher.sendEvent(new ProductAddedToWishlistEvent(wishlist, input.productId()));
        }

        return new AddProductOutput(
                wishlist.getId(), wishlist.getName(), isProductInWishlist ? null : input.productId()
        );
    }

    private static void validateInput(AddProductInput input) {
        ValidationUtil.init()
                .notNullOrEmpty(input.productId(), "productId", "productId.required")
                .build(true);
    }

    private Wishlist getOrCreateWishlist(AddProductInput input) {
        var currentUser = storeAuthProvider.getCurrentUser();
        var useDefaultWishlist = StringUtils.isBlank(input.wishlistId());

        var wishlistOutputDS = useDefaultWishlist
                ? wishlistDataSource.getDefaultByClientId(currentUser.clientId())
                : wishlistDataSource.getByWishlistId(input.wishlistId());

        if (wishlistOutputDS == null) {
            if (!useDefaultWishlist) {
                throw BusinessException.fromErrorCode("Wishlist.notFound");
            }

            var wishlistCreated = createNewWishlistService.createNewWishlist(
                    new CreateNewWishlistInput(true, "Lista de Desejos")
            );

            wishlistOutputDS = wishlistDataSource.getByWishlistId(wishlistCreated.id());
        }

        if (!wishlistOutputDS.clientId().equals(currentUser.clientId())) {
            throw BusinessException.fromErrorCode("Wishlist.notFound");
        }

        return WishlistFactory.create(wishlistOutputDS, currentUser);
    }

}
