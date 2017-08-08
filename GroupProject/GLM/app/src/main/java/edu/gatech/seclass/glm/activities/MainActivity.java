package edu.gatech.seclass.glm.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import edu.gatech.seclass.glm.GLM;
import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.adapters.ListAdapter;
import edu.gatech.seclass.glm.db.DbHelper;
import edu.gatech.seclass.glm.db.ListManager;
import edu.gatech.seclass.glm.models.GroceryList;

public class MainActivity extends android.app.ListActivity {
    private ListAdapter lr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Grocery List Manager | Team 87");

        // Initialize the database and load data
        loadDb();
        loadList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showAddDialog();
            }
        });
    }

    // Initialize the database from the assets folder
    public void loadDb() {
        Context context = GLM.getContext();
        DbHelper dbHelper = new DbHelper(context);

        try {
            dbHelper.createDataBase(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Open the DB - Critical for onUpgrade
        dbHelper.openDataBase();
    }

    private void loadList() {
        List<GroceryList> list = ListManager.getGroceryLists();

        // Display the data
        lr = new ListAdapter(this, 1, list);
        setListAdapter(lr);

        // If a press is detected
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroceryList groceryList = (GroceryList)parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                intent.putExtra("listId", groceryList.getId());
                intent.putExtra("listName", groceryList.getName());
                startActivity(intent);
            }
        });

        // If a long press is detected
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the item and edit it
                showEditDialog((GroceryList)parent.getItemAtPosition(position));

                return true;
            }
        });
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Grocery List");

        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Overridden
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();

        // Override defaults to prevent closing the dialog (validation)
        Button btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = input.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
                } else {
                    if(lr.listNameIsUnique(name)) {
                        GroceryList newList = ListManager.addGroceryList(name);
                        lr.add(newList);

                        Spanned message = Html.fromHtml("Grocery list <strong>" + name
                                + "</strong> added");
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(MainActivity.this, "You already have a list called " + name + ".", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void showEditDialog(final GroceryList groceryList) {
        final String oldName = groceryList.getName();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(oldName + " - Delete or Edit ");

        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        input.setText(oldName);
        input.setSelection(input.getText().length());
        builder.setView(input);

        builder.setPositiveButton("Rename",
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
                        ListManager.delete(groceryList.getId());
                        lr.remove(groceryList);
                        Spanned message = Html.fromHtml("Grocery list <strong>" + oldName +
                                "</strong> deleted");
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                        dialog.cancel();
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();

        // Override defaults to prevent closing the dialog (validation)
        Button btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = input.getText().toString();

                if (newName.isEmpty()) {
                    String message = "Name is required";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!newName.equals(oldName)) {
                    // Check if list with that name exists already
                    if(lr.listNameIsUnique(newName)){
                        // Update record
                        ListManager.update(groceryList.getId(), newName);
                        groceryList.setName(newName);
                        lr.notifyDataSetChanged();

                        Spanned message = Html.fromHtml("Grocery list renamed to <strong>"
                                + newName + "</strong>");
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }else{
                        String message = "You already have a list called " + newName + ".";
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dialog.dismiss();
                }
            }
        });
    }
}
