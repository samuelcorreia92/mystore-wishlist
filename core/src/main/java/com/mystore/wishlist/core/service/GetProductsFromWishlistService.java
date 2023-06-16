package com.mystore.wishlist.core.service;

import com.mystore.shared.core.service.IService;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistInput;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistOutput;

public interface GetProductsFromWishlistService extends IService {

    GetProductsFromWishlistOutput getAllProducts(GetProductsFromWishlistInput input);

}
