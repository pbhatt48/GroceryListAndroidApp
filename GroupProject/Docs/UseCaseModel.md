# Use Case Model
**Author**:  estenger3 @ team87

## 1 Use Case Diagram

![alt tag](Images/Use-Case-Diagram.png)

## 2 Use Case Descriptions
##### Add Item to List #####
- **Requirements:** *User*s must be able to add *Item*s to a *List*.
- **Pre-conditions:** A *List* must already be selected. 
- **Post-conditions:** *User* must specify a quantity for the selected *Item*.
- **Scenarios:** The *User* may select this *Item* from the hierarchical *List* of *Item*s. This is the default behavior. The *User* can search for an *Item* by name. If the *Item* is not found, the *User* can specify the *Item* type and add the new *Item* to the Database. The *User* can then select the newly created *Item* to be added to the *List*. Once an *Item* is selected from the aforementioned methods the *User* then selects the *Item* quantity to add it to the *List*.

##### Delete Item from List #####
- **Requirements:** *User*s must be able to delete *Item*s from a *List*.
- **Pre-conditions:** A *List* must already be selected. The *List* must have at least 1 *Item*.
- **Post-conditions:** *Item* is removed from selected *List*.
- **Scenarios:** A *User* opens the *GroceryListManager* application and selects a *List*. This pulls up a hierarchical *List* of all the *Item*s in the *List*. The *User* then presses an *Item*. This brings up a dialogue menu with a delete button. The *User* then presses the delete button and the *Item* is removed from the *List*. 

##### Change Quantity of Item #####
- **Requirements:** *User*s must be able to change the quantity of *Item*s in a *List*.
- **Pre-conditions:** A *List* must already be selected. The *List* must have at least 1 *Item*.
- **Post-conditions:** The quantity of the *Item* in the *List* is changed. The *List* is then saved. 
- **Scenarios:** A *User* opens the *GroceryListManager* application and selects a *List*. This pulls up a hierarchical *List* of all the *Item*s in the *List*. The *User* then clicks an *Item*. This brings up a dialogue menu with a quantity field. The *User* types in the new quantity and presses *CHANGE QUANTITY*. The quantity is updated at this time.

##### Pick Item from Hierarchical List #####
- **Requirements:** *User*s must be able to add *Item*s to a *List* by picking them from hierarchical *List* where the first level is the *Item* type (e.g., cereal), and the second level is the name of the actual *Item* (e.g., shredded wheat).
- **Pre-conditions:** A *List* must already be selected. The *User* presses a button to enter the Add *Item* GUI. The *User* selects an *Item* from the Add *Item* GUI to be added to the selected *List*.
- **Post-conditions:** A GUI appears to specify the *Item* quantity.
- **Scenarios:** A *User* opens the *GroceryListManager* application and selects a *List*. This pulls up a hierarchical *List* of all the *Item*s in the *List*. The *User* then presses the + button. The GUI then changes to show a hierarchical *List* of all the *Item*s the *User* can choose from. The *User* Selects one of these *Item*s to add to the *List*.

##### Specify Item Quantity #####
- **Requirements:** *User*s must be able to specify a quantity for the *Item* being added to a *List*.
- **Pre-conditions:** A *List* must already be selected. The *User* has entered the add *Item* GUI for the selected *List*. The *User* has selected an *Item* from the add *Item* GUI. 
- **Post-conditions:** The *Item* is added to the selected *List*. The *List* is updated and saved. 
- **Scenarios:** The *User* specifies the quantity for the *Item* and it is added to the selected *List*. The *User* could try to proceed without specifying a quantity. In this case a message would appear to notify the *User* to specify a quantity and the GrocerListManager would not allow the *User* to procceed without specifying a quantity. 

##### Search Item by Name #####
- **Requirements:** *User*s must also be able to specify an *Item* by typing its name. 
- **Pre-conditions:** A *List* must already be selected. The *User* has entered the add *Item* GUI for the selected *List*. The *User* has typed the name of the desired *Item* in the search bar.
- **Post-conditions:** The *GroceryListManager* then provides the search results to the *User*. The *User* can then select an *Item* from the search results to be added to the selected *List*.
- **Scenarios:** The *User* finds the desired *Item* and adds it to the *List*. The desired *Item* might not be in the Database. In this case the *GroceryListManager* will prompt the *User* to specify an *Item* type for the desired *Item* and add this new *Item* to the Database. This would then add the newly created *Item* to the selected *List*.

##### Add New Item to DB #####
- **Requirements:** The application must ask the *User* to select a type for the *Item* and then save the new *Item* if an *Item* cannot be found in the DB.
- **Pre-conditions:** A *List* must already be selected. The *User* has entered the add *Item* GUI for the selected *List*. The *User* has typed the name of the desired *Item* in the search bar. No results for the desired *Item* were found. The user then presses the + button to add a new item. A GUI for adding a new *Item* to the DB is rendered and the *User* specifies an *Item* type for the desired *Item*. 
- **Post-conditions:** This *Item* is added as a new *Item* to the DB.
- **Scenarios:** The *User* specifies an *Item* type and the *Item* is added to the DB. The *User* presses *CANCEL*. In this case the new item dialogue is closed and nothing happens.

##### Specify Item Type #####
- **Requirements:** The application must ask the *User* to select a type for the *Item* and then save the new *Item* together with its type.
- **Pre-conditions:** A *List* must already be selected. The *User* has entered the add *Item* GUI for the selected *List*. The *User* has typed the name of the desired *Item* in the search bar. No results for the desired *Item* were found. The *User* presses the + button to add a new *Item*. A GUI for adding a new *Item* to the DB is rendered.
- **Post-conditions:** The *Item* has been saved to the DB with an associated *Item* type.
- **Scenarios:**  The *User* specifies an *Item* type and the *User* then Continues with the Add new *Item* to DB use case. 

##### Check off Item in List #####
- **Requirements:** *User*s must be able to check off *Item*s in a *List* (without deleting them).
- **Pre-conditions:** A *List* must already be selected. The *List* must have at least 1 *Item*.
- **Post-conditions:** A checkbox next to the *Item* in the *List* is checked. The *List* is then updated and saved.
- **Scenarios:** The *User* clicks the checkbox next to the *Item* in the *List* and the *List* is updated and saved. 

##### Clear all check off marks in List #####
- **Requirements:** *User*s must be able to clear all the check-off marks in a *List* at once.
- **Pre-conditions:** A *List* must already be selected. The *List* must have at least 1 *Item*.
- **Post-conditions:** All checkboxes next to *Item*s will be unchecked. 
- **Scenarios:** The *User* presses the clear all check-off marks button and all the check boxes next to *Item*s in the *List* are unchecked.

##### Create List #####
- **Requirements:** The application must provide the *User*s with the ability to create *List*s.
- **Pre-conditions:** The *User* has opened the *GroceryListManager* application. 
- **Post-conditions:** A new *List* has been created. This *List* is linked to the *User* in the DB.
- **Scenarios:** The *User* opens the *GroceryListManager* and presses the + button. The application renders a GUI to request the name for the new *List*. The *User* fills in the name and presses *OK*. A new *List* is created.

##### Rename List #####
- **Requirements:** The application must provide the *User*s with the ability to (re)name a *List*. 
- **Pre-conditions:** The *User* has performed a long press on a *List*. A dialogue has been rendered and supplies the *User* with the *List* name field to be specified.
- **Post-conditions:** The *List* is updated with the newly specified name and saved to the DB.
- **Scenarios:** The *User* specifies a new *List* name and presses *RENAME*. The *List* is updated and the saved to the DB. The *User* might try to continue without specifying a name. In this case the application will notify the *User* to specify a *List* name and not allow the *User* to continue. The *User* might also specify a name that has already been used before. In this case the application will notify the *User* to pick a new name that hasn't been used before. The *User* might also specify the original name of the *List*. In this case the app exits from the *settings* GUI and nothing is done because nothing has changed. 

##### Select List #####
- **Requirements:** The application must provide the *User*s with the ability to select *List*s.
- **Pre-conditions:** The *User* has opened the *GroceryListManager*. A *List* of all the *User*'s *List*s is displayed.
- **Post-conditions:** The selected *List* is displayed in the hierarchical *List* format. 
- **Scenarios:** The *User* clicks on one of the *List*s. The *List* is displayed in the hierarchical *List* format. 

##### Delete List #####
- **Requirements:** The application must provide the *User*s with the ability to delete *List*s.
- **Pre-conditions:** The *User* has opened the *GroceryListManager*. The *User* has performed a long press on the *List*. A dialogue appears to allow the user to delete the *List*. 
- **Post-conditions:** A *List* has been removed from the *List* of *User*'s *List*s. The *List* is removed from the DB.
- **Scenarios:** The *DELETE* button is pressed for one of the *User*'s *List*s. The *List* is removed from *List* of *List*s and from the DB. 

