package com.mystore.wishlist.infraestructure.repository.mongo;

import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.service.mapper.MyStoreMapperConfig;
import com.mystore.wishlist.core.repository.dto.GetWishlistOutputDS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MyStoreMapperConfig.class)
public interface WishlistMapper {

    WishlistDocument toDocument(Wishlist wishlist);

    @Mapping(target = "isDefault", source = "default")
    GetWishlistOutputDS toWishlistOutputDS(WishlistDocument wishlist);

}
