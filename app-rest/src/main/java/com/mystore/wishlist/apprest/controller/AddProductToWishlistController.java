package com.mystore.wishlist.apprest.controller;

import com.mystore.wishlist.apprest.dto.AddProductToWishlistResponse;
import com.mystore.wishlist.apprest.dto.AddProductToWishlistResponseError;
import com.mystore.wishlist.apprest.dto.AddProductToWishlistResponseOk;
import com.mystore.wishlist.core.service.AddProductToWishlistService;
import com.mystore.wishlist.core.service.dto.AddProductInput;
import com.mystore.wishlist.core.service.dto.AddProductOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/wishlist")
public class AddProductToWishlistController {

    private final AddProductToWishlistService service;

    @PostMapping(path = "/add-product/{productId}")
    public ResponseEntity<AddProductToWishlistResponse> createWishlistAndAddProduct(@PathVariable String productId) {
        var retorno = service.addNewProduct(
                new AddProductInput(null, productId)
        );

        return convertToResponse(retorno);
    }

    @PostMapping(path = "/{wishlistId}/add-product/{productId}")
    public ResponseEntity<AddProductToWishlistResponse> addProductToWishlist(@PathVariable String wishlistId,
                                                                             @PathVariable String productId) {
        var retorno = service.addNewProduct(
                new AddProductInput(wishlistId, productId)
        );

        return convertToResponse(retorno);
    }

    private static ResponseEntity<AddProductToWishlistResponse> convertToResponse(AddProductOutput retorno) {
        if (retorno.productIdAdded() == null) {
            return ResponseEntity.badRequest().body(
                    new AddProductToWishlistResponseError("Product already exists in wishlist")
            );
        }

        return ResponseEntity.ok().body(new AddProductToWishlistResponseOk(
                retorno.wishlistId(), retorno.wishlistName(), retorno.productIdAdded()
        ));
    }

}
