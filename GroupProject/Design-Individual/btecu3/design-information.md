# Assignment #5

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

    Added **User**, **GroceryList** and **Item** classes.
    Added **addItem**, **deleteItem** and **changeItemQuantity** operations to **GroceryList** class.


2. The application must contain a database (DB) of items and corresponding item types.

    Added **type** attribute to **Item** class.


3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

    Added **name** attribute to **Item** class.


4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type.

    Added **createItem** and **searchItem** operations to **User** class.


5. Lists must be saved automatically and immediately after they are modified.

    Does not affect the design directly.


6. Users must be able to check off items in a list (without deleting them).

    Added **checkOffItem** operation to **GroceryList** class.
    Added **checkedOff** attribute to **ItemOnList** associated class.


7. Users must also be able to clear all the check-off marks in a list at once.

    Added **clearAllCheckOffs** operation to **GroceryList** class.


8. Check-off marks for a list are persistent and must also be saved immediately.

    Does not affect the design directly.


9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).

    Added **groupItemsByType** operation to **GroceryList** class.


10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

    Added relationship between **User** and **GroceryList**.
    Added **createList**, **deleteList**, **renameList** and **selectList** operations to **User** class.


11. The User Interface (UI) must be intuitive and responsive.

    Does not affect the design directly.


-------



Note:
`The design left the database out on purpose since the database or database would not be very relevant in terms of UML. The design assumes standard database operation, where each operation that modifies data runs in a transaction, therefore saving immediately.`
