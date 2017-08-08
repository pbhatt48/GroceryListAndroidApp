package edu.gatech.seclass.glm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.models.GroceryList;

/**
 * Created by Bj on 10/8/16.
 */

public class ListAdapter extends ArrayAdapter<GroceryList> {
    private List<GroceryList> items;

    public ListAdapter(Context context, int textViewResourceId, List<GroceryList> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    public Boolean listNameIsUnique(String newListName){
        String curName;
        for(int i = 0; i < items.size(); i++){
            curName = items.get(i).getName();
            if(curName.equals(newListName)){
                return false;
            }
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the view
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.grocery_list_list, parent, false);
        }

        // Get the position of the item
        GroceryList o = items.get(position);

        if (o != null) {
            TextView name = (TextView) v.findViewById(R.id.grocery_list_name);

            // Update item if not null
            if (name != null) {
                name.setText(o.getName());
            }
        }

        return v;
    }
}
