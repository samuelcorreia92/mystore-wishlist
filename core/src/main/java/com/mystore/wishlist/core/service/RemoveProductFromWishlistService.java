package com.mystore.wishlist.core.service;

import com.mystore.shared.core.service.IService;
import com.mystore.wishlist.core.service.dto.RemoveProductInput;
import com.mystore.wishlist.core.service.dto.RemoveProductOutput;

public interface RemoveProductFromWishlistService extends IService {

    RemoveProductOutput removeProduct(RemoveProductInput input);

}
