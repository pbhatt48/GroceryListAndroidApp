#Assignment5 Design Information

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
This requirement has be satisfied by adding to the design the class GroceryList with an attribute called items. Class GroceryList also has methods addItem(item), deleteItem(item), changeQuantity(item, newQuantity).

2. The application must contain a database (DB) of items and corresponding item types.
This requirement has been satisfied by adding to the design the class Database. The Database class has an attribute called items. The items attribute is an array of the Item classes. The Item class, which was also added to the design, has an attribute called type. The corresponding item type will be stored within the Items class which is stored in the Database class.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
This requirement has been satisfied by adding to the design the class HierarchicalList. The view structure with the item type is not pertinent to the class design diagram. The HierarchicalList class has a method of selectItem(item, quantity) to allow the user to select an item and specify the quantity.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type.
This requirement has been satisfied through the HierarchicalList class. The HierarchicalList class has a function called searchItemName(name). This function will use the Database class to search for the item. The HierarchicalList class also has a function called searchItemType(type). This works the same way as the searchItemName(name) function but for the type. If the match is not found the Database class function addNewItem(item) will be invoked to specify a new item with its type.

5. Lists must be saved automatically and immediately after they are modified.
This is accomplished throught the GroceryList saveList() function.

6. Users must be able to check off items in a list (without deleting them).
This is accomplished through the GroceryList crossOffItem(item) function.

7. Users must also be able to clear all the check-off marks in a list at once.
This is accomplished through the GroceryList clearAllCheckMarks() function.

8. Check-off marks for a list are persistent and must also be saved immediately.
This is also accomplished with the GroceryList saveList() function.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of 
products at once (i.e., without having to go back and forth between aisles).
Not considered because it does not affect the design directly.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
Multiple lists is supported through the User class. The User class has an attribute called lists, which holds an array of the user's GroceryLists. The GroceryList class has functions to create, rename, select and delete called createList(), setListName(name), selectList(), deleteList() respectively.

11. The User Interface (UI) must be intuitive and responsive.
Not considered because it does not affect the design directly.
