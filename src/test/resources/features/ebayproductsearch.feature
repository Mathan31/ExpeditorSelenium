Feature: Ebay product search

Background: Common Pre-Condition for Scenario and Scenario Outline
  Given User can open chrome browser
  And Navigate to Ebay home page
	
	@Smoke
  Scenario: Search Product by hardcoding the data from script
 
  When User search the product name and product catagory
  And click on search button
  Then User should able to see the search results
  And Close browser
  
  	@Sanity
    Scenario: Search Product by hardcoding the data from feature file as List of Map
    
    When User can search the preferred multipl product name and multiple catagory as below list of map
      | ProductName | ProductCatagory |
      | Java        | Books           |
      | Dress       | Baby            |
    And click on search button
    Then User should able to see the search results
    And Close browser
  #
  #Scenario: Search Product by hardcoding the data from feature file
  #Given User can open preferred browser 2
  #And Navigate to Ebay home page
  #When User can search the preferred product name as "iPhone" and product catagory as "Cell Phones & Accessories"
  #And click on search button
  #Then User should able to see the search results
  #And Close browser
  #Scenario Outline: Search Product by hardcoding the data from feature file
  #Given User can open preferred multiple browsers <browser>
  #And Navigate to Ebay home page
  #When User can search the preferred multiple product name as <productName> and catagory as <productCatagory>
  #And click on search button
  #Then User should able to see the search results
  #And Close browser
  #
  #Examples:
  #| browser | productName | productCatagory |
  #|       1 | Java        | Books           |
  #|       2 | Dress       | Baby            |
  #Scenario: Search Product by hardcoding the data from feature file as List of List
  #Given User can open preferred browser 2
  #And Navigate to Ebay home page
  #When User can search the preferred multipl product name and multiple catagory as below
  #| Java  | Books |
  #| Dress | Baby  |
  #And click on search button
  #Then User should able to see the search results
  #And Close browser
  #
  #Scenario: Search Product by hardcoding the data from feature file as List of Map
    #Given User can open preferred browser 2
    #And Navigate to Ebay home page
    #When User can search the preferred multipl product name and multiple catagory as below list of map
      #| ProductName | ProductCatagory |
      #| Java        | Books           |
      #| Dress       | Baby            |
    #And click on search button
    #Then User should able to see the search results
    #And Close browser
