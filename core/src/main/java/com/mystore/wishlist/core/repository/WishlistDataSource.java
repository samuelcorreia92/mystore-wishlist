package com.mystore.wishlist.core.repository;

import com.mystore.shared.core.datasource.IDataSource;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.repository.dto.CreateWishlistInputDS;
import com.mystore.wishlist.core.repository.dto.CreateWishlistOutputDS;
import com.mystore.wishlist.core.repository.dto.GetWishlistOutputDS;

public interface WishlistDataSource extends IDataSource {

    void save(Wishlist wishlist);

    CreateWishlistOutputDS create(CreateWishlistInputDS newWishlist);

    GetWishlistOutputDS getDefaultByClientId(String clientId);

    GetWishlistOutputDS getByWishlistId(String wishlistId);

}
