package edu.gatech.seclass.glm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import edu.gatech.seclass.glm.db.ItemManager;
import edu.gatech.seclass.glm.models.GroceryItem;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ItemManagerTest {

    List<GroceryItem> groceryItemList;
    int groceryListId = 0;
    int testItemId = 999;

    @Before
    public void setUp()
    {
        groceryItemList = ItemManager.getItemsInList(groceryListId);
    }

    @After
    public void tearDown()
    {

    }

    //@Test
    /**
     * This test checks to see if the item is added to the ItemManager
     */
    /*public void testAddItem()
    {
        assertNotEquals(-1, ItemManager.addToList(groceryListId, testItemId));
        groceryItemList = ItemManager.getItemsInList(groceryListId);
        boolean foundItem = false;
        for(GroceryItem item : groceryItemList)
        {
            if(item.getId() == testItemId)
            {
                foundItem = true;
            }
        }
        assertFalse(foundItem);

    }*/

    //@Test
    /**
     * This test checks to see if item is properly removed from the ItemManager
     */
    /*
    public void testRemoveItem()
    {
        groceryItemList = ItemManager.getItemsInList(groceryListId);
        boolean foundItem = false;
        for(GroceryItem item : groceryItemList)
        {
            if(item.getId() == testItemId)
            {
                foundItem = true;
            }
        }
        assertTrue(foundItem);
    }
    */

    //@Test
    /**
     * This test checks to see if the Item type and Item can be added when none exist
     */
    /*
    public void testCreateItem1()
    {
        assertEquals(-1, ItemManager.createItem("TestItemType", "TestItemValue"));
    }
    */

    //@Test
    /**
     * This test checks to see if the Item type and Item can not be added when the Item and Item type exist
     */
    /*
    public void testCreateItem2()
    {
        assertNotEquals(-1, ItemManager.createItem("TestItemType", "TestItemValue"));
    }
    */
}