#Author Goddindla Raju

Feature: Purchasing the product on the AutomationTestStore application

Scenario: Registring the application by providing the valid details and then purchasing the product
Given Launch the requested browser with the url
When user clicks on Login or Register link
And user click on Continue button 
And user fill the required details
	| Fields      | Values |
  | firstname | Test |
  | lastname  | User1 |
  | address1   | #20, DownTown |
  | city      | London" |
  | telephone    | 0000000000 |
  | email      |abc_xyz_1235@gmail.com |
  | fax     | 12345 |
  | company |Desire Technologies Pvt Ltd |
  | zipcode | 010101                      |
  | loginname | loginuser11               |
  | loginpassword |  pass@123              |
  | passwordconfirm | pass@123            |

And user clicks on Continue button to register
Then verify whether the correct user name displayed on landing page
And user add the product to the cart
When user moves to the checkout page for payments
Then validate the product on payments page showing correctly
When user checks the order details
When user clicks on Save to PDF option
Then verify the order details in pdf
