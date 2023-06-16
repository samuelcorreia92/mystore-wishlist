package com.mystore.wishlist.core.service;

import com.mystore.shared.core.service.IService;
import com.mystore.wishlist.core.service.dto.HasProductInWishlistInput;
import com.mystore.wishlist.core.service.dto.HasProductInWishlistOutput;

public interface HasProductInWishlistService extends IService {

    HasProductInWishlistOutput hasProductInWishlist(HasProductInWishlistInput input);

}
