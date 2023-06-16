package com.mystore.wishlist.apprest.controller;

import com.mystore.wishlist.apprest.dto.HasProductInWishlistResponse;
import com.mystore.wishlist.core.service.HasProductInWishlistService;
import com.mystore.wishlist.core.service.dto.HasProductInWishlistInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/wishlist")
public class HasProductInWishlistController {

    private final HasProductInWishlistService service;

    @GetMapping(path = "/{wishlistId}/has-product/{productId}")
    public ResponseEntity<HasProductInWishlistResponse> process(@PathVariable String wishlistId,
                                                                @PathVariable String productId) {
        var retorno = service.hasProductInWishlist(
                new HasProductInWishlistInput(wishlistId, productId)
        );

        return ResponseEntity.ok(new HasProductInWishlistResponse(
                retorno.wishlistId(), retorno.wishlistName(), retorno.productIdSearched(), retorno.productFound()
        ));
    }


}
