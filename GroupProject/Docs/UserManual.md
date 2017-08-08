# Grocery List Manager – User Manual

## Introduction
Grocery List Manager is an application that helps users to
manage their grocery lists. It has various functionalities to customize and edit the grocery lists. It is built for Android and can run on any device with Android 4.4 or newer.


## Main Menu
The first screen when opening the application is the main menu. It displays a user's list of grocery lists. Users can create, delete, rename and open their desired grocery list.  
![alt text](Images/mainMenu.png "Main Menu")

### Create List
To create a list, the user can press on the red **+** button located at the bottom right of the screen.  
A dialog will ask for the name of the new grocery list. The user can enter a grocery list name and press **OK**. The user has the option to cancel the operation.  
![alt text](Images/createList1.png "Create Grocery List")

### Rename & Delete List
To rename or delete a grocery list, the user can (long) press and hold the grocery list to be edited.
A dialog will ask for a new name and the user can edit current name and press **Rename**. Alternatively, the user can press **Delete** to delete the grocery list. As always, the user has the option to cancel the operation.  
![alt text](Images/renameAndDelete1.png "Rename and Delete")

### Select List
To view a list, the user can press on the grocery list name, which will transition to a different screen.


## Grocery List
After selecting a grocery list, the use will see either an empty list or a list of items, if the the list is not new.
When the grocery list has items, they will be displayed grouped by their type in alphabetical order, both by type and by name.  
![alt text](Images/addItem1.png "Grocery List")

### Add Item
To add add a new item to the list, the user can press on the red **+** button located at the bottom right of the screen.  
The application will transition to a new screen with the available grocery items in a sorted hierarchical list (grouped by type and name).  
![alt text](Images/searchItems.png "Browse Grocery Items")

The user can use the top search functionality to further filter the list and quickly find the required item. The search is intelligent to look both for type and name, however, it will not display empty types.  
![alt text](Images/addItem2.png "Search Grocery Items")

To add a specific grocery item to the grocery list, the user can simply press on the item name. If the grocery list already contains the grocery item, it will display a notification indicating so. Otherwise, a dialog will prompt the user to enter the quantity desired.  
![alt text](Images/addItem3.png "Enter Quantity")

Once the user presses **Change Quantity**, the application will return to the grocery list screen.

### Create Item
After searching for a specific item, if there are no matches, the user can create a new item.  
To create a grocery item, the user can press on the red **+** button located at the bottom right of the screen.  
![alt text](Images/createItem1.png "Create Grocery Item")

A dialog will ask for the type and a new name.  
![alt text](Images/createItem2.png "Enter Grocery Item Name")

The user can select a type from the existing list of types.  
![alt text](Images/createItem3.png "Select Type")

To confirm the operation, the user can press **Add New Item**. As always, the user has the option to cancel the operation.  
![alt text](Images/createItem4.png "Add New Grocery Item")

### Change Quantity & Delete Item
To change quantity or delete a grocery item, the user can press the grocery item to be edited.
A dialog will ask for a new quantity and the user can edit current quantity and press **Change Quantity**. Alternatively, the user can press **Delete** to delete the grocery item from the list. As always, the user has the option to cancel the operation.  
![alt text](Images/addItem3.png "Enter Quantity")

### Check Off Item
To check off a grocery item, the user can press the checkbox on the left side of the item name.  
![alt text](Images/checkOffItem2.png "Check Off Grocery Item")

### Clear All Check Offs
The user can clear all checked off grocery items by pressing on the red checkbox button located at the bottom left of the screen.  
![alt text](Images/clearCheckOff1.png "Check Off All")

**Bonus**: If no grocery item is checked off, the check off all button will check off all grocery items.