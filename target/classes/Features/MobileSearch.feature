#Author: sarath@your.domain.com
Feature: Mobile Search Validation


  Scenario: Testing Mobile Purchase
    Given user lauches flipkart application
    And user enters credentials and click on login
    When user searching the mobile
    And user click on add to cart
    Then navigates into add to cart and check the order to updated
    
    
