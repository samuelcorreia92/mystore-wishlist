package com.mystore.wishlist.core.service;

import com.mystore.shared.core.service.IService;
import com.mystore.wishlist.core.service.dto.AddProductInput;
import com.mystore.wishlist.core.service.dto.AddProductOutput;

public interface AddProductToWishlistService extends IService {

    AddProductOutput addNewProduct(AddProductInput input);

}
