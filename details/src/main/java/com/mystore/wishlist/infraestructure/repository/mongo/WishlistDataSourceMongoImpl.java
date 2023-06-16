package com.mystore.wishlist.infraestructure.repository.mongo;

import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.repository.dto.CreateWishlistInputDS;
import com.mystore.wishlist.core.repository.dto.CreateWishlistOutputDS;
import com.mystore.wishlist.core.repository.dto.GetWishlistOutputDS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class WishlistDataSourceMongoImpl implements WishlistDataSource {

    private final WishlistMongoRepository repository;
    private final WishlistMapper mapper;

    @Override
    public void save(Wishlist wishlist) {
        var document = mapper.toDocument(wishlist);
        this.repository.save(document);
    }

    @Override
    public CreateWishlistOutputDS create(CreateWishlistInputDS newWishlist) {
        var document = new WishlistDocument();
        document.setName(newWishlist.name());
        document.setClientId(newWishlist.clientId());
        document.setDefault(newWishlist.isDefault());
        document.setProductList(Collections.emptyList());
        var savedDocument = this.repository.save(document);

        return new CreateWishlistOutputDS(savedDocument.getId());
    }

    @Override
    public GetWishlistOutputDS getDefaultByClientId(String clientId) {
        return repository.findDefaultByClientId(clientId)
                .map(mapper::toWishlistOutputDS)
                .orElse(null);
    }

    @Override
    public GetWishlistOutputDS getByWishlistId(String wishlistId) {
        return repository.findById(wishlistId)
                .map(mapper::toWishlistOutputDS)
                .orElse(null);
    }

}
