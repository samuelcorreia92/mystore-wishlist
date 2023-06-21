Feature: Wishlist

  Scenario: A regular user can add the very first product on wishlist?
    Given a regular user
    When the user tries to add product ABC to wishlist
    Then the product(s) must be added succesfully

  Scenario: A premium user can add the very first product on wishlist?
    Given a premium user
    When the user tries to add product WYZ to wishlist
    Then the product(s) must be added succesfully

  Scenario: A user verify if some product is in an empty wishlist
    Given a regular user
    When the user verify if the product ABC is in wishlist
    Then the answer must be false

  Scenario: A user verify if a not added product is in the wishlist
    Given a regular user
    When the user tries to add product ABC to wishlist
    And the user verify if the product WYZ is in wishlist
    Then the answer must be false

  Scenario: A user verify if a added product is in the wishlist
    Given a regular user
    When the user tries to add product ABC to wishlist
    And the user verify if the product ABC is in wishlist
    Then the answer must be true

  Scenario: A regular user tries to add more than 20 products in the wishlist
    Given a regular user
    When the user tries to add 21 products to wishlist
    Then a failure with name "Wishlist.productList.maxLength" is expected

  Scenario: A premium user tries to add more than 20 products in the wishlist
    Given a premium user
    When the user tries to add 21 products to wishlist
    Then the product(s) must be added succesfully
    And the wishlist must contain 21 product(s)

  Scenario: A user tries to add duplicated product in the wishlist
    Given a regular user
    When the user tries to add product ABC to wishlist
    And the user tries to add product ABC to wishlist
    Then the wishlist must contain 1 product(s)

  Scenario: A user tries to remove product from the wishlist
    Given a regular user
    When the user tries to add product ABC to wishlist
    Then the wishlist must contain 1 product(s)
    When the user tries to add product WYZ to wishlist
    Then the wishlist must contain 2 product(s)
    When the user tries to remove product ABC from wishlist
    Then the wishlist must contain 1 product(s)

  Scenario: A user tries to remove a not added product from the wishlist
    Given a regular user
    When the user tries to add product ABC to wishlist
    Then the wishlist must contain 1 product(s)
    When the user tries to add product WYZ to wishlist
    Then the wishlist must contain 2 product(s)
    When the user tries to remove product NOTADDED from wishlist
    Then the wishlist must contain 2 product(s)
