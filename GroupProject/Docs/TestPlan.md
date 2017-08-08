# Test Plan

**Author**: sjiang3 @ Team87

## 1 Testing Strategy

### 1.1 Overall strategy

Unit tests will be performed at the use case level. Each use case will have a test that tests for functionality when the inputs are empty as well as when there is a valid input. Depending on the use case, additional tests may be added to test for edge cases such as special characters as well as invalid inputs. White-box unit tests should be developed by the unit owner while black box unit tests can be developed by everyone on the team (ideally by someone other than the original developer).  

Integration tests will be developed to test for functionality on the unit as well as the functions that the unit may affect. The unit owner will work with the team to define which units are affected by the unit, as well as how to integrate the unit into the integration test suite.   

System tests will developed to test for overall functionality as a whole, as well as performance.  

Both system and integration tests will be developed to cover as much specified behavior as possible as well as to reveal errors due to implementation.  

One of the most difficult aspects of automated testing is due to the applications persistent nature. That is, whenever a test is run, the data from that test persists throughout the application on subsequent executions. As a result, what may have been a valid and correct test case on the initial iteration of the test now becomes unpredictable on subsequent test iterations. That being said, since the application is designed to be persistent, additional functionality to clear persistent data needs to also be built into the application in order to facilitate cleaner and easier ways to clean up after a test or test suite. 

~~Regression tests will be performed when functions are added. The regression tests will be automated in order to reduce developer overhead. Each unit test developer should develop additions to the regression test suite for their unit.~~  

~~White-box testing of the application will utilize the built in code coverage reporter in Android Studio, Jacoco, to go along with our white-box test development. The white-box tests will mostly focus on branch and condition coverage since the expectation is that the application is not error intolerable.~~     

### 1.2 Test Selection

Test selection will depend on how the use case is defined. If the use case involves user input, then mostly black box techniques will be utilized. The black box technique utilized most frequently will be partitioning. As a result, the test developer will have to determine valid boundaries to test for while also eliminating meaningless combinations. The test developer will need to decide what types of tests need to be developed and whether they can be tested for a single input or for multiple inputs.

Black box testing will also be developed for integration and system level testing.  

White box testing will mostly be developed for behind the scenes structures as well as unit tests. The white-box tests will mostly be targetting branches and conditions. 

### 1.3 Adequacy Criterion

The quality of the test cases will be assessed based on how it performs on the JUnit tests. The unit tests are designed to see whether the actual results of a given set of inputs matches up with the expected results for that input. The system and integration level tests will run in a finite state machine manner. That is, given a set of inputs and commands at the beginning of the application, does the system end up in the expected state at the end of that sequence with the expected values. 

### 1.4 Bug Tracking

Bugs and enhancement requests will be tracked in the github repository. Whenever a bug or enhancement is requested, the requestor will open up an issue in the github repository. Bugs will have the bug label attached to them whereas enhancements will have the enhancement label. Using github also makes it easy for bugs and enhancement resolution to be easily visible within the project. Pull requests can easily reference the issues so that our team can see who and how a developer is resolving the issue. When the code review for the pull request is complete, the issue can be closed.

### 1.5 Technology

Android has a few different test support libraries. AndroidJUnitRunner is the choice for running white box and black tests. 

Espresso is a UI testing framework that can be used to do functional UI testing.

Along with the test support libraries, Android Studio also comes included with a test coverage reporter, Jacoco. Jacoco will be used to generate test coverage reports to help the team continue to develop tests. Jacoco will be integrated into the build script so that the test coverage report will be run after compiling, and running the test program.  


## 2 Test Cases

#### Unit tests:
##### Black Box tests:

######Add Item to List: 

| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
|  Add Item to list with List selected | Test for item addition. This is the primary use case. | 1. Select List 2. Click + button 3. specify positive quantity | Item should only be added when a list is selected and positive quantity. | When an Item is added to a list, the item then shows up within that list and not anywhere else. | Pass |  |
|  Add Item to list with List not selected | Test for item addition with no List selected | 1. Click + button 2. specify positive quantity | Do nothing | Does nothing | Pass |  |
|  Add Item to list with List selected negative quantity | Test for item addition with negative quantity. | 1. Select List 2. Click + button 3. Specify negative quantity | Item should not be added to the List. Should error out. | Item not added to List. Error pop up. | Pass |  |
|  Add Item to list with List selected no quantity | Test for item addition with no quantity. | 1. Select List 2. Click + button | Item should not be added to the List. | Does nothing. | Pass |  |
|  Add Item to list with List selected, special characters | Test for item addition with special characters. | 1. Select List 2. Click + button 3. Specify special characters | Item should not be added to the List. Should error out. | Item not added to List. Error pop up. | Pass |  |
This was written as a JUnit test (but is not tested due the database portion that is private within the Item Manger) and was instead tested manually. The reason no complete JUnit test was written for this is because the addItem logic is tied into the GUI. As a result, any JUnit test would have been required to run portions of the GUI as well. The GUI has the item name and item type as well as the item ID, whereas the database keeps track of the attributes checked and quantity. The database is also key-ed off of the item ID, but that requires the GUI to in order to properly test functionality.

######Delete Item from List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Delete Item from List | Test for item deletion. This is the primary use case. | 1. Select list 2. Long press to select item 3. Click Delete Button | If list selected and item selected, selected item should not be present in list. | Item is deleted | Pass | |
| Delete Item from List with List selected | Test for item deletion. This is the primary use case. | 1. Select list 2. Select item | If list selected and item selected, selected item should not be present in list. | Item is deleted | Pass | |
| Delete Item from List with List selected | Test for item deletion. This is the primary use case. | 1. Select list 2. Select item | If list selected and item selected, selected item should not be present in list. | Item is deleted | Pass | |
This was coded as a JUnit test (but not enabled due to the database portion of the code that remains private). No complete JUnit test was written due to the fact that parts the Item list is tied into the GUI. 

######Change Quantity of Item :
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Change Quantity of Item to positive value | Test for item quantity change | 1. Select List. 2. Select Item. 3. Get Quantity 4. Change quantity. | Item quantity should be the new quantity. | Item gets its quantity updated when a new quantity is given | Pass |  |
| Change Quantity of Item to negative value | Test for item quantity error | 1. Select List. 2. Select Item. 3. Get Quantity 4. Change quantity. | Quantity cannot be negative. | Quantity is not updated. Quantity cannot be negative. | Pass |  |
| Change Quantity of Item to zero value | Test for item quantity error | 1. Select List. 2. Select Item. 3. Get Quantity 4. Change quantity. | Quantity cannot be zero. | Quantity is not updated. Quantity cannot be zero. | Pass |  |
This was tested manually because the item quantity is managed by the DBHelper class which is private by design. As a result JUnit tests cannot be run on the class managing the Item quantity. A GUI test was written for this but Item quantity cannot be accessed due to the database helper class being private.

######Pick Item from Hierarchical List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Pick Item from Heirarchical List | Test for ability to add Items to a List by picking from a heirarchical List where the first level is the Item type and the second level is the actual Item. This is the primary use case | 1. Select List 2. Click Add Item button to enter Add Item GUI 3. Select Item type 4. Select corresponding Item 5. Enter quantity 6. Click Change Quantity | When an Item type is input, Items belonging to the Item type should show up in the results list. When an Item is chosen from that list, a dialog should be displayed that lets the user specify a quantity. Once the CHANGE QUANTITY button is clicked, the Item will be added to the List with the given quantity. | Items are returned in the results list when an Item is entered. | Pass |  |
| Pick Item from Heirarchical List with empty Item Type | Test for ability to add Items to a List by having empty Item type | 1. Select List 2. Click Add Item button to enter Add Item GUI 3. Select empty Item type 4. Select corresponding Item 5. Enter quantity 6. Click Change Quantity | Do nothing. | No user input is allowed so not possible to have empty Item type. | Pass |  |
| Pick Item from Heirarchical List with non-existent Item Type | Test for ability to add Items to a List by having non-existent Item type | 1. Select List 2. Click Add Item button to enter Add Item GUI 3. Select non-existent Item type 4. Select corresponding Item 5. Enter quantity 6. Click Change Quantity | Do nothing. | No user input is allowed for Item type test case is invalid. | Pass |  |

######Search Item by Name:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Search Item by Name valid String | Test for ability to specify item by typing its name. This is the targeted use case. | 1. Select List. 2. Enter add Item GUI. 3. Type name of desired Item | Relevant values for valid String. | Search results are returned when the Item exists in the Database. | Pass | |
| Search Item by Name empty String | Test for ability to show all items if empty String. | 1. Select List. 2. Enter add Item GUI. 3. Empty String in text box | Show all Items in DB. | Shows all Items in DB. | Pass | |
| Search Item by Name invalid String | Test for ability to ask user for Item type when not found. | 1. Select List. 2. Enter add Item GUI. 3. Type name of desired invalid Item. | Shows nothing. Prompts user to input Item type. | The GroceryListManager app prompts the User to specify an Item type for the desired Item if the Item is not found. | Pass | |
This was tested manually.

######Add New Item to DB:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Add New Item to DB with new String | Tests for the ability to add Item to DB if the item is not found in the Database. This is the targeted use case. | 1. Select List 2. Enter add Item GUI 3. Type name of desired Item in search bar 4. Click + button to add new Item 5. Specify Item type for Item 6. Click Add Item | The specified Item is added to the selected List. | The specified Item is added to the selected List. | Pass | |
| Add New Item to DB with empty String | Tests for the ability to do nothing if String is empty. | 1. Select List 2. Enter add Item GUI 3. Type name of desired Item in search bar 4. Click + button to add new Item 5. Specify Item type for Item 6. Click Add Item | Do nothing. | Does nothing. | Pass | |
| Add New Item to DB with new String | Tests for the ability to do nothing if String is a duplicate. | 1. Select List 2. Enter add Item GUI 3. Type name of desired Item in search bar 4. Click + button to add new Item 5. Specify Item type for Item 6. Click Add Item | Do nothing. | Does nothing. | Pass | |
| Add New Item to DB with special characters String | Tests for the ability to show Toast. | 1. Select List 2. Enter add Item GUI 3. Type name of desired Item in search bar 4. Click + button to add new Item 5. Specify Item type for Item 6. Click Add Item | Toast. | Shows Toast. | Pass | |
This was tested manually due to being unable to write unit test to test the DB (DBHelper) functionality due to the fact that the database functionality is incorporated into the GUI. As a result, this had to be tested manually.

######Specify Item Type:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Specify Item Type with no Item type  | Test for ability to specify item type | 1. Select List. 2. Enter add Item GUI. 3. Specify new Item with no Item Type. | The Item is saved to the DB with associated Item type. | The Item is saved to the DB with the associated Item type. | Pass | |
| Specify Item Type with Item type  | Test for ability to not show specify item type GUI. | 1. Select List. 2. Enter add Item GUI. 3. Specify new Item with Item Type. | The Item is saved to the DB with Item type unchanged. | The Item is saved to the DB with the unchanged Item type. | Pass | |
This was tested manually by following the steps listed. Then, due to the fact that there is no method to get the Item type for an given Item, a new List was selected and the Item type specified before was selected. The check was to see if the Item existed in the results for the specified Item type.

######Check off Item in List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Check off Item in List | Test to see if user can check off a single Item | 1. Select List. 2. In GUI click check box. 3. Read check off status | Check off attribute should match the expected state. | Items that are not checked get checked and vice versa. | Pass (When Item is initially unchecked) | This was implemented as an Espresso test. This test may assert sometimes due to the persistent nature of the application. |

######Clear all check off marks in List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Clear all check off marks in List | Test to see if all the check marks are cleared all Items in List | 1. Select List. 2. In GUI, randomly check off items 3. Clear all check offs. 4. Check all check off marks in List. | All check-off marks should be cleared | All checkoff marks are cleared if there are any check off marks. Otherwise all checkoff marks are toggled on. | Pass (for check off all) | This was implemented as an Espresso test. Depending on the initial state of the checkoff marks, may assert fail due to persistent data sometimes. |

######Create List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Create List with valid List name | Test for functionality to add new List | 1. Click Add Button 2. Enter valid List name | List should be added into grocery list manager | The List was added into the Grocery List manager test | Pass | This was added as an espresso test. |
| Create List with empty List name | Test for functionality to not add new List | 1. Click Add Button 2. Enter empty List name | Do nothing. | Does nothing. | Pass | |
| Create List with special characters List name | Test for functionality to show Toast. | 1. Click Add Button 2. Enter List name with special characters. | Show Toast. | Shows Toast. | Pass | |
######Rename List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Rename List with new valid name | Test for ability to rename a List | 1. Long press on a List name 2. Edit text with desired name in dialog 3. Press RENAME | List name is updated and saved to DB. | List name is updated. | Pass | |
| Rename List with empty name | Test for ability to do nothing when empty. | 1. Long press on a List name 2. Edit text with desired name in dialog 3. Press RENAME | User cannot continue. | User cannot continue. | Pass | |
| Rename List with duplicate name | Test for ability to do nothing when empty. | 1. Long press on a List name 2. Edit text with desired name in dialog 3. Press RENAME | User cannot continue with duplicate name.  | User cannot continue with duplicate name. | |
| Rename List with same name | Test for ability to do nothing when same name is given. | 1. Long press on a List name 2. Edit text with desired name in dialog 3. Press RENAME | Exits Dialog. | Exits Dialog. | Pass | |
| Rename List with special characters name | Test for ability to throw Toast on special characters. | 1. Long press on a List name 2. Edit text with desired name in dialog 3. Press RENAME | Throw Toast. | Throw Toast. | Pass | |
This was tested manually

######Select List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Select List | Test for ability to select a List | 1. Click on a List | The selected List is displayed in the hierarchical List format. | The selected List is displayed with Items on the List in hierarchical format. | Pass | This was tested manually by checking to see if Items in the List are displayed. |
| Select List | Test for ability to select a List | 1. Click on a List 2. Click on a different List | The selected List is displayed in the hierarchical List format. | The selected List is displayed with Items on the List in hierarchical format. | Pass | This was tested manually by checking to see if Items in the List are displayed. |

######Delete List:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Delete List | Test for ability to delete a List | 1. Long press on a List 2. Click DELETE button on the dialog that appears | List should be deleted from the list of Lists. | List is removed from list of Lists and from the DB. | Pass | This was tested manually by checking to see if the selected List is removed from the list of Lists. |   

######Specify Item Quantity:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Specify Item Quantity with positive quantity | Test for specifying quantity when adding Item to List. | 1. Select List. 2. Add Item. 3. Specify quantity | Add item to list if positive. | Item gets its quantity updated when a new quantity is given. | Pass | |
| Specify Item Quantity with negative quantity | Test for specifying negative quantity when adding Item to List. | 1. Select List. 2. Add Item. 3. Specify quantity | Error if negative. | Toast when negative. | Pass | |
| Specify Item Quantity with zero quantity | Test for specifying quantity when adding Item to List. | 1. Select List. 2. Add Item. 3. Specify quantity | Warning when zero. | Warning when zero. | Pass | |
 This was tested manually because the item quantity is managed by the DBHelper class which is private by design. As a result JUnit tests cannot be run on the class managing the Item Quantity.

######Create Item:
| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
|  --------- |  ------ | ----- | --------------- | ------------- | --------- | ---------------------- |
| Create New Item | Test for creating new Item | 1. Create Item by specifying Item Type and Item | Item Type and Item should both be added, if not already there. | Item type and Item are added when there is none there | Pass | Implemented as a JUnit test testing for valid and invalid cases | 
| Create New "Existing" Item | Test for creating new "Existing" Item | 1. Create Item by specifying Item Type and Item | Throw error when creating new "Existing" Item. | Throws error. | Pass | Implemented as a JUnit test testing for valid and invalid cases |

#####White Box tests:

~~White box tests will be developed once some code is in place. The white box tests will target mostly branch and conditions as well as code coverage.~~  
No White box testing was added because the branches and conditionals in the code are tied-in with the GUI.  
