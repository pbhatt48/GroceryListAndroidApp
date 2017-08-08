package edu.gatech.seclass.glm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


import java.util.List;

import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.activities.SearchActivity;
import edu.gatech.seclass.glm.models.GroceryItem;

/**
 * Created by Bj on 10/8/16.
 */

public class SearchAdapter extends ItemAdapter {
    public SearchAdapter(Context context, List<GroceryItem> items) {
        super(context, items);
    }

    @Override
    protected void setupHolder(View convertView, final ViewGroup parent, ViewHolder holder, GroceryItem item) {
        holder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
        holder.checkBox.setVisibility(View.GONE);
    }

    public void showEditDialog(final GroceryItem groceryItem) {
        if (groceryItem.getType() == null) {
            // Ignore types
            return;
        }

        // Add item to grocery list
        ((SearchActivity)context).addOrIgnoreItem(groceryItem);
    }
}
