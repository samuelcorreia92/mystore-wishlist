package com.mystore.wishlist.core.service.impl;

import com.mystore.shared.core.auth.StoreAuthProvider;
import com.mystore.shared.core.event.IEventDispatcher;
import com.mystore.wishlist.core.event.WishlistCreatedEvent;
import com.mystore.wishlist.core.factory.WishlistFactory;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.repository.dto.CreateWishlistInputDS;
import com.mystore.wishlist.core.service.CreateNewWishlistService;
import com.mystore.wishlist.core.service.dto.CreateNewWishlistInput;
import com.mystore.wishlist.core.service.dto.CreateNewWishlistOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log
@RequiredArgsConstructor
public class CreateNewWishlistServiceImpl implements CreateNewWishlistService {

    private final IEventDispatcher eventDispatcher;
    private final StoreAuthProvider storeAuthProvider;
    private final WishlistDataSource wishlistDataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CreateNewWishlistOutput createNewWishlist(CreateNewWishlistInput input) {
        var currentUser = storeAuthProvider.getCurrentUser();
        var currentDefaultWishlist = WishlistFactory.create(
                wishlistDataSource.getDefaultByClientId(currentUser.clientId()),
                currentUser
        );

        // remove default flag from current default wishlist, if any
        if (input.isDefault() && currentDefaultWishlist != null) {
            currentDefaultWishlist.setAsNonDefault();
            wishlistDataSource.save(currentDefaultWishlist);
        }

        // create new wishlist
        var defaultWishlist = currentDefaultWishlist == null || input.isDefault();
        var createWishlistInputDS = new CreateWishlistInputDS(input.name(), currentUser.clientId(), defaultWishlist);
        var createdId = wishlistDataSource.create(createWishlistInputDS).id();

        // send event
        eventDispatcher.sendEvent(
                new WishlistCreatedEvent(new WishlistCreatedEvent.Payload(
                        createdId,
                        input.name(),
                        currentUser.clientId()
                ))
        );

        return new CreateNewWishlistOutput(createdId, defaultWishlist);
    }

}
