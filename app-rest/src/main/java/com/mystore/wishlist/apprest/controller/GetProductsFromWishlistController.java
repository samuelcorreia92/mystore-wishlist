package com.mystore.wishlist.apprest.controller;

import com.mystore.wishlist.apprest.dto.GetProductsFromWishlistResponse;
import com.mystore.wishlist.core.service.GetProductsFromWishlistService;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistInput;
import com.mystore.wishlist.core.service.dto.GetProductsFromWishlistOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/wishlist")
public class GetProductsFromWishlistController {

    private final GetProductsFromWishlistService service;

    @GetMapping(path = "/get-products")
    public ResponseEntity<GetProductsFromWishlistResponse> getAllProducts() {
        var retorno = service.getAllProducts(
                new GetProductsFromWishlistInput(null)
        );

        return convertToResponse(retorno);
    }

    @GetMapping(path = "/{wishlistId}/get-products")
    public ResponseEntity<GetProductsFromWishlistResponse> getAllProducts(@PathVariable String wishlistId) {
        var retorno = service.getAllProducts(
                new GetProductsFromWishlistInput(wishlistId)
        );

        return convertToResponse(retorno);
    }

    private static ResponseEntity<GetProductsFromWishlistResponse> convertToResponse(GetProductsFromWishlistOutput retorno) {
        var wishlistResp = new GetProductsFromWishlistResponse.Wishlist(retorno.wishlistId(), retorno.wishlistName(), retorno.defaultWishlist());
        var productsResp = retorno.productList().stream()
                .map(p -> new GetProductsFromWishlistResponse.Product(p.productId(), p.productAddedAt()))
                .toList();

        if (productsResp.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new GetProductsFromWishlistResponse(wishlistResp, productsResp));
    }

}
