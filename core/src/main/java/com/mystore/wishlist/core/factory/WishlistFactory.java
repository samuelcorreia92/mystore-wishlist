package com.mystore.wishlist.core.factory;

import com.mystore.shared.core.auth.PrincipalUser;
import com.mystore.wishlist.core.entity.CommonWishlist;
import com.mystore.wishlist.core.entity.PremiumUserWishlist;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.repository.dto.GetWishlistOutputDS;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistOutput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WishlistFactory {


    public static Wishlist create(GetWishlistOutputDS data, PrincipalUser user) {
        if (data == null) return null;

        Set<Wishlist.Product> products = data.productList().stream()
                .map(product -> Wishlist.Product.builder()
                        .productId(product.productId())
                        .productAddedAt(product.productAddedAt())
                        .build()
                )
                .collect(Collectors.toCollection(HashSet::new));

        return createInternal(
                data.id(),
                data.name(),
                data.clientId(),
                data.isDefault(),
                user.premium(),
                products);
    }

    public static GetProductsFromWishlistOutput convert(@NonNull Wishlist wishlist) {
        Collection<GetProductsFromWishlistOutput.Product> products = wishlist.getProductList().stream()
                .map(product -> new GetProductsFromWishlistOutput.Product(
                        product.getProductId(), product.getProductAddedAt()
                ))
                .collect(Collectors.toCollection(HashSet::new));

        return new GetProductsFromWishlistOutput(
                wishlist.getId(),
                wishlist.getName(),
                wishlist.isDefault(),
                wishlist.getProductList().size(),
                products);
    }

    private static Wishlist createInternal(String id,
                                           String name,
                                           String clientId,
                                           boolean isDefault,
                                           boolean isPremium,
                                           Set<Wishlist.Product> products) {

        if (isPremium) {
            return PremiumUserWishlist.builder()
                    .id(id)
                    .name(name)
                    .clientId(clientId)
                    .isDefault(isDefault)
                    .productList(products)
                    .build();
        }

        return CommonWishlist.builder()
                .id(id)
                .name(name)
                .clientId(clientId)
                .isDefault(isDefault)
                .productList(products)
                .build();
    }

}
