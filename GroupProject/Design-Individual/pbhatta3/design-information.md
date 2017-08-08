GroceryListManager

Design Rationale:

GroceryListManager is an application that helps you to manage your grocery list. Users can login with their name, email and password to login into app. A user can create number of grocery list as per the requirement where the relationship between the user and GroceryList in n to n i.e. users can create n number of list. Users can create new grocery list, rename the old grocery list, delete the grocery list. The method sortByOrderListName() sorts the List names in ascending order by name and the method sortListItemsByItemTypeAndItemNames() sorts the item type and the items in item type by ascending order as well.
The class "Item" has "itemName" and "itemQuantity" as the attributes. "Item" class have n-n relationship with GroceryList. This class has methods deleteItemFromGroceryList to delete an item, updateQuantity to update the quantity of an item, checkOff to checkOff the items in list, clearAll to clear the check-off items from the list. The saveItemsUpdate method is called everytime when you operate any methods in the class to reflect the changes. 

The addItemInGroceryList method of Item class adds an item into the list. If an item does not exist, it looks if the item type for the item exist. If the itemType for the item exits, the user is able to add the item on the database as a new item else and item will not be added. The method saveItemsUpdate is called as well after this operation is performed.

ItemType is a class that has an itemType attribute. Item has n to 1 relationship with ItemType i.e. one ItemType can have many items but one item cannot have more than one itemType.

"Database (Item, ItemType)" is a database class that has itemName and itemType as an attributes. itemType is a primary key i.e. they can be only one unique itemType for every itemName. Method checkItemsInList(ItemEntered, itemType) checks/search the item from DB and provides it to the Item class. Method checkIsItemTypeInDatabase(ItemEntered, itemType) checks if the itemType exists or not in the database. This method helps the app whether the itemType is on the database or not. If not an item cannot be added. Method enterNewItemInDB(ItemEntered, itemType) method is used to enter new item in the database with the precondition checks.



Design Information Details:

1.	A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples). 
>>	To realize this requirement, I have added a “GroceryList”, “Item” and “User” classes. The "User" have “name” , "loginEmail" and “password” attribute and "addUser" operation to add new user. The “GroceryList” has an array of GroceryList that consists of all the grocery lists of the user. The “Item” class have “ItemName” and “Item Quantity” attribute. Any selection of an item will list the item quantity = 1. The “Item” class has the operations “addIteemInGroceryList”, “deleteItemInGroceryList” and “updateQuantity” to add , delete, change quantity from the list.

2.	The application must contain a database (DB) of items and corresponding item types. 
>>	This is represented by the “Database (Item, Item Type)” utility class which has "itemType" and "itemName" as Item type and Item Name attribute. Each item type can have many items but one item can have only one item type.

3.	Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
>>	The hierarchical list is being populated in heirarchial order by the method "sortListItemsByItemTypeAndItemNames()" from Grocerylist class. This method first lists the item type in the chronological order and then lists the items in "Item Type" in ascending order. When the user adds a new item, the quantity of the new item is added with the inputs parameter of method addItemInGroceryList(ItemEntered, itemType, quantity) method.

4.	Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB. 
>>	When you add an item using addItemInGroceryList(ItemEntered, itemType, quantity) method, the database looks if the item exists in the database. If the item does not exist, the application checks if the item type exist for that especif item. If the item type exist, it will call the method enterNewItemInDB(ItemEntered, itemType) to enter new item in the database.


5.	Lists must be saved automatically and immediately after they are modified.
>>	An operation saveItemsUpdate() method is called after each operations to save any modification that is made to the list.

6.	Users must be able to check off items in a list (without deleting them). 
>>	An operation checkOff() is implemented on the item class to check off item(s) that is on the list.

7.	Users must also be able to clear all the check-off marks in a list at once. 
>>	An operation clearAll() is implemented on the item class to clear all the check off mark(s) that is on the list.

8.	Check-off marks for a list are persistent and must also be saved immediately. 
>>	An operation saveItemsUpdate() method is called after Check-offs to save any modification that is made to the list.

9.	The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles). 
>>	The method "sortListItemsByItemTypeAndItemNames()" from Grocerylist will do the operations to present the items in a list group by type.

10.	The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists. 
>>	This is mentioned in the design as the relationship with the "User" to the "Grocerylist" class is 1 to n relationship meaning an user can have n number of lists.

11.	The User Interface (UI) must be intuitive and responsive. 
>>	This is shown more at the time of implementation part not on the design part.


Constraints:
- The user cannot have more than 20 list. Hence n<=20
- When you are adding new item using addItemInGroceryList(ItemEntered, itemType, quantity) , the quantity must be greater than 0.
