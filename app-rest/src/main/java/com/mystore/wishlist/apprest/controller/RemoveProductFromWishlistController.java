package com.mystore.wishlist.apprest.controller;

import com.mystore.wishlist.apprest.dto.RemoveProductFromWishlistResponse;
import com.mystore.wishlist.apprest.dto.RemoveProductFromWishlistResponseError;
import com.mystore.wishlist.apprest.dto.RemoveProductFromWishlistResponseOk;
import com.mystore.wishlist.core.service.RemoveProductFromWishlistService;
import com.mystore.wishlist.core.service.dto.RemoveProductInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/wishlist")
public class RemoveProductFromWishlistController {

    private final RemoveProductFromWishlistService service;

    @DeleteMapping(path = "/{wishlistId}/remove-product/{productId}")
    public ResponseEntity<RemoveProductFromWishlistResponse> process(@PathVariable String wishlistId,
                                                                     @PathVariable String productId) {
        var retorno = service.removeProduct(
                new RemoveProductInput(wishlistId, productId)
        );

        if (retorno.productIdRemoved() == null) {
            return ResponseEntity.badRequest().body(
                    new RemoveProductFromWishlistResponseError("Product not found in wishlist")
            );
        }

        return ResponseEntity.ok(new RemoveProductFromWishlistResponseOk(
                retorno.wishlistId(), retorno.wishlistName(), retorno.productIdRemoved()
        ));
    }


}
