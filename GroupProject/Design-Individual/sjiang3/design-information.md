#Design Information

##Summary
Gettor and settor functions are not explicitly defined in the design.

The design relies on the assumption that the aspection of system memory management are managed by the application lifecycle within the operating system. For example, on Android, an application has essentially four states: active, paused, stopped, and finished. Of those four, active and paused are where the bulk of the care is placed because there is less application control of what happens in the stopped and finished states. In the active state, requirements pertaining to persistency would be managed by the operating system because it is at the top of the stack and is running. When the application enters the paused state, the application will save off content to long term storage because the aplication is no longer completely active and could be killed off by the system in low memory situations. As a result, saving the state information is paramount to not losing user data. 

##Requirements
**1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).**
This requirement is met by the aggregation relationship between the GroceryList, ItemType, and Item classes. Items is the smallest unit capable of being added, deleted or modified. Adding of the Item to GroceryList isn't visibly handled with a member function in GroceryList because the application design is event-driven. Therefore, the addition of an item to the list is initiated by the GUI (not pictured) and the item is added (or deleted) directly to ItemType (which is a part of GroceryList).

**2. The application must contain a database (DB) of items and corresponding item types.**
The database is represented as a container of items and corresponding item types that is accessed by a DatabaseAccessor that can get or insert item names and types into the database.

**3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.**
This requirement is fulfilled by ItemType having a list of Items within it. GroceryList has a list of ItemTypes as an attribute such that a heirarchical list is formed. Picking the item type and subsequently the item is handled by the event-driven GUI and getter functions in GroceryList and ItemType.  

**4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.**
The DatabaseAccessor class exists as a controller for getting item names and item types from the database. It is also the interface with which items and associated item types are also added to the database. The requirement asking the users whether or not the item is intended to be added as well as the application asking the user to select a type is not included in the design because they do not affect the design directly.

**5. Lists must be saved automatically and immediately after they are modified.**
This is not explicitly in the design and is expected to be handled as part of the application lifecycle. A saveToMemory() function is provided in GroceryListsManager that will write all the contents of the application to long term storage when the application enters the pause state. When the application is in the foreground and running, the list will be persistently saved by the operating system. Along those lines, a loadFromMemory function is also provided in the design to load the contents of the application from long term memory.

**6. Users must be able to check off items in a list (without deleting them).**
The checkedOff attribute within Item is used for checking off items in a list. Not explicitly defined in the design is the settor function that sets the flag.   

**7. Users must also be able to clear all the check-off marks in a list at once.**
The clearAllCheckedOffItems function in GroceryList is used to clear all the checkedOff flags within the grocery list.

**8. Check-off marks for a list are persistent and must also be saved immediately.**
As expressed in requirement #5, persistency itself is handled directly by the application lifecycle, but long term storage is handled by the saveToMemory function in GroceryListsManager that would be called when the application enters the paused state.

**9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).**
This requirement is fulfilled by having the GroceryList made up of ItemType lists. GroceryList and ItemType have gettor functions for getting member attributes. The presentation itself is not included in the design because it doesn't affect the directly.  

**10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.**
GroceryListsManager keeps track of multiple lists in a GroceryList array. Along with that, it also has a member attribute that sets the active list to reference the given GroceryList in the GroceryList array.  

**11. The User Interface (UI) must be intuitive and responsive.**
This does not affect the design directly and deals more with the implementation of the application.  
