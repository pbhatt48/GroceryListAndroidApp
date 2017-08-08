package edu.gatech.seclass.glm.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.seclass.glm.activities.ListActivity;
import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.db.ItemManager;
import edu.gatech.seclass.glm.models.GroceryItem;

/**
 * Created by Bj on 10/8/16.
 */

public class ItemAdapter extends BaseAdapter {
    private final int TYPE = -1;
    protected Context context;
    private LayoutInflater mInflater;
    private List<GroceryItem> items = new ArrayList<>();
    private Map<String, List<GroceryItem>> itemTypes = new HashMap<>();

    public ItemAdapter(Context context, List<GroceryItem> items) {
        this.context = context;
        mInflater = LayoutInflater.from(context);

        for (GroceryItem item : items) {
            addItem(item);
        }
    }

    public GroceryItem getItemById(int id) {
        for (GroceryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    private void addItem(GroceryItem item) {
        if (itemTypes.containsKey(item.getType())) {
            List<GroceryItem> list = itemTypes.get(item.getType());
            int index = items.indexOf(list.get(list.size() - 1));

            list.add(item);
            items.add(index + 1, item);
        } else {
            items.add(new GroceryItem(TYPE, null, item.getType()));
            List<GroceryItem> list = new ArrayList<>();
            list.add(item);

            itemTypes.put(item.getType(), list);
            items.add(item);
        }

        notifyDataSetChanged();
    }

    private void removeItem(GroceryItem item) {
        List<GroceryItem> list = itemTypes.get(item.getType());
        if (list.size() == 1) {
            // Last item, remove type
            items.remove(items.indexOf(item) - 1);
            itemTypes.remove(item.getType());
        } else {
            list.remove(item);
        }

        items.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public GroceryItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        GroceryItem item = items.get(position);

        if (item.getId() == TYPE) {
            convertView = mInflater.inflate(R.layout.grocery_list_type, null);
        } else {
            convertView = mInflater.inflate(R.layout.grocery_list_item, null);
            setupHolder(convertView, parent, holder, item);
        }

        holder.textView = (TextView)convertView.findViewById(R.id.text);
        holder.textView.setText(item.getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((ListView)parent).getPositionForView((View) v.getParent());
                showEditDialog(items.get(position));
            }
        });

        convertView.setTag(holder);

        return convertView;
    }

    protected void setupHolder(View convertView, final ViewGroup parent, ViewHolder holder, GroceryItem item) {
        holder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
        holder.checkBox.setChecked(item.isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                int position = ((ListView)parent).getPositionForView((View) v.getParent());
                GroceryItem item = items.get(position);

                ItemManager.update(item.getId(), isChecked, item.getQuantity());

                ((ListActivity)context).updateCheck(!isChecked && isNoneChecked());
            }
        });
    }

    public boolean isNoneChecked() {
        boolean anyChecked = false;
        for (GroceryItem item : items) {
            if (item.isChecked()) {
                anyChecked = true;
                break;
            }
        }

        return !anyChecked;
    }

    public void showEditDialog(final GroceryItem groceryItem) {
        if (groceryItem.getType() == null) {
            // Ignore types
            return;
        }

        final int quantity = groceryItem.getQuantity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(groceryItem.getName() + " - Delete or Edit Quantity");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        input.setText(String.valueOf(quantity));
        input.setSelection(input.getText().length());
        builder.setView(input);

        builder.setPositiveButton("Change Quantity",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Overridden
                    }
                });

        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Delete record
                        ItemManager.delete(groceryItem.getId());
                        Spanned message = Html.fromHtml("Grocery item <strong>" +
                                groceryItem.getName() + "</strong> removed");
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                        removeItem(groceryItem);
                        ((ListActivity)context).updateCheck(isNoneChecked());

                        dialog.dismiss();
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();

        // Override defaults to prevent closing the dialog (validation)
        Button btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = input.getText().toString();
                if (inputText.isEmpty()) {
                    String message = "Quantity is required";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    return;
                }

                int newQuantity = Integer.parseInt(inputText);
                if (newQuantity < 1) {
                    String message = "Quantity should be greater than zero";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (quantity != newQuantity) {
                    // Update record
                    ItemManager.update(groceryItem.getId(), groceryItem.isChecked(), newQuantity);
                    groceryItem.setQuantity(newQuantity);

                    Spanned message = Html.fromHtml("<strong>" + groceryItem.getName() +
                            "</strong> quantity updated to <strong>" + newQuantity + "</strong>");
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        });
    }

    static class ViewHolder {
        CheckBox checkBox;
        TextView textView;
    }
}
