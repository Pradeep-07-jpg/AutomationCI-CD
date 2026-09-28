@tag

Feature: purchase the order from E-Commerce Page

Background:
Given I landed on E-commerce page

@tag2
Scenario Outline: Positive test of submitting the order
Given logged in with <name> and enter the password as <password>
When I add the product  <productName> to Cart
And checkout the product with <productName> and click submit order
Then Enter personal details <Firstname> and <Secondname> with <Pincode> after clicking the submit button

Examples:
| name			| password	   | productName 				| Firstname		| Secondname 	| Pincode
| standard_user | secret_sauce | Sauce Labs Onesie			| Pradeep       | A 			| 600094



