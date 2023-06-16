package com.mystore.wishlist.core.service;

import com.mystore.wishlist.core.service.dto.CreateNewWishlistInput;
import com.mystore.wishlist.core.service.dto.CreateNewWishlistOutput;
import com.mystore.shared.core.service.IService;

public interface CreateNewWishlistService extends IService {

    CreateNewWishlistOutput createNewWishlist(CreateNewWishlistInput input);

}
