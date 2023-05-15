#Author Goddindla Raju

Feature: Purchasing the product on the AutomationTestStore application

Scenario: Registring the application by providing the valid details and then purchasing the product
Given Launch the requested browser with the url
When user clicks on Login or Register link
And user click on Continue button to fill the required details
  | firstname | Test |
  | lastname  | User1 |
  | address1   | #20, DownTown |
  | city      | London |
  | telephone     | 0000000000 |
  | email      | abc_xyz_1234@gmail.com |
  | fax     | 12345 |
  | company | Desire Technologies Pvt Ltd |
  | zipcode | 010101                      |
  | loginname | loginuser11               |
  | loginpassword|  pass@123              |
  | passwordconfirm | pass@123            |

  When user fill the required details on create account page
And user clicks on Continue button to register
Then verify whether the correct user name displayed on landing page
And user add the product to the cart
  | searchitem | shoes                      |
When user moves to the checkout page for payments
Then validate the product on payments page showing correctly