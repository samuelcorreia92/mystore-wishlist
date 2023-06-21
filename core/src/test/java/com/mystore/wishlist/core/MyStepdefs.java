package com.mystore.wishlist.core;

import com.mystore.shared.core.auth.PrincipalUser;
import com.mystore.shared.core.util.MessageUtil;
import com.mystore.wishlist.core.entity.Wishlist;
import com.mystore.wishlist.core.factory.WishlistFactory;
import com.mystore.wishlist.core.repository.dto.GetWishlistOutputDS;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MyStepdefs {

    PrincipalUser principalUser;
    Wishlist wishlist;

    Boolean booleanAnswer;
    List<String> listOfProductsAdded = new ArrayList<>();
    Exception exception;


    @BeforeAll
    public static void setUp() {
        MessageUtil.setMessageSource((defaultMessage, code, args) -> code);
    }

    @Given("a regular user")
    public void aRegularUser() {
        principalUser = new PrincipalUser("samuel", "Samuel", "samuel@mail.com", false);
        wishlist = createWishlistFromUser();
    }

    @Given("a premium user")
    public void aPremiumUser() {
        principalUser = new PrincipalUser("daiely", "Daiely", "daiely@mail.com", true);
        wishlist = createWishlistFromUser();
    }

    @When("the user tries to add product {word} to wishlist")
    public void theUserTriesToAddProductToWishlist(String productId) {
        try {
            wishlist.addProduct(productId);
            listOfProductsAdded.add(productId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("the user tries to remove product {word} from wishlist")
    public void theUserTriesToRemoveProductFromWishlist(String productId) {
        wishlist.removeProduct(productId);
        listOfProductsAdded.remove(productId);
    }

    @When("the user tries to add {int} products to wishlist")
    public void theUserTriesToAddProductsToWishlist(int qtdProductsToAdd) {
        try {
            for (int i = 0; i < qtdProductsToAdd; i++) {
                var productId = UUID.randomUUID().toString();
                wishlist.addProduct(productId);
                listOfProductsAdded.add(productId);
            }
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("the user verify if the product {word} is in wishlist")
    public void theUserVerifyIfTheProductIsInWishlist(String productId) {
        booleanAnswer = wishlist.hasProduct(productId);
    }

    @Then("the product\\(s) must be added succesfully")
    public void theProductMustBeAddedSuccesfully() {
        Assertions.assertEquals(listOfProductsAdded.size(), wishlist.getProductList().size());
        listOfProductsAdded.forEach(productId -> Assertions.assertTrue(wishlist.hasProduct(productId)));
    }

    @Then("the answer must be {word}")
    public void theAnswerMustBe(String result) {
        Assertions.assertEquals(result, booleanAnswer == null ? "null" : booleanAnswer.toString());
    }

    @Then("a failure with name {string} is expected")
    public void aFailureWithNameIsExpected(String nameOrCodeFromError) {
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(nameOrCodeFromError, exception.getMessage());
    }

    @Then("the wishlist must contain {int} product\\(s)")
    public void theWishlistMustContainProductS(int qtdProducts) {
        Assertions.assertEquals(qtdProducts,wishlist.getProductList().size());
    }

    private Wishlist createWishlistFromUser() {
        return WishlistFactory.create(
                new GetWishlistOutputDS(
                        principalUser.clientId() + "-wishlist", "Wishlist", principalUser.clientId(), true, Collections.emptyList()
                ),
                principalUser
        );
    }
}
