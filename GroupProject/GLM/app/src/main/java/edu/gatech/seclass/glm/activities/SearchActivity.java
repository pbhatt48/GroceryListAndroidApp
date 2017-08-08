package edu.gatech.seclass.glm.activities;

import android.R.layout;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.adapters.SearchAdapter;
import edu.gatech.seclass.glm.db.ItemManager;
import edu.gatech.seclass.glm.models.GroceryItem;

public class SearchActivity extends AppCompatActivity {
    private int listId;
    private SearchAdapter sr;
    private EditText inputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Search");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listId = getIntent().getIntExtra("listId", 1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog(view.getContext());
            }
        });


        // Initialize the input box
        inputBox = (EditText)findViewById(R.id.searchBox);
        inputBox.requestFocus();
        inputBox.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                android.text.InputType.TYPE_TEXT_FLAG_AUTO_CORRECT |
                android.text.InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
                android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        // Initialize the list view for results and other label
        final ListView listView = (ListView)findViewById(R.id.list);
        final TextView textView = (TextView)findViewById(R.id.results);

        // Listener to detect any input
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // Search using the input
                List<GroceryItem> list = ItemManager.searchItems(inputBox.getText().toString());

                if (list.isEmpty()) {
                    textView.setText("No results");
                } else if (list.size() == 1) {
                    textView.setText("1 result");
                } else {
                    textView.setText(list.size() + " results");
                }

                // Display the data
                sr = new SearchAdapter(SearchActivity.this, list);
                listView.setAdapter(sr);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        // Trigger initial search
        inputBox.setText("");
    }

    public void addOrIgnoreItem(GroceryItem groceryItem) {
        boolean newItem = true;

        // Get existing items in list
        List<GroceryItem> list = ItemManager.getItemsInList(listId);
        for (GroceryItem item : list) {
            if (item.getName().equals(groceryItem.getName())) {
                // Item already exists in list
                newItem = false;
                break;
            }
        }

        Intent intent = new Intent();

        if (newItem) {
            int itemId = ItemManager.addToList(listId, groceryItem.getId());
            intent.putExtra("itemId", itemId);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    private void showAddDialog(final Context context) {
        List<String> types = ItemManager.getTypes();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add New Item");

        final Spinner list = new Spinner(context, Spinner.MODE_DIALOG);
        list.setPrompt("Select a type");
        ArrayAdapter<String> ad = new ArrayAdapter<>(context, layout.simple_spinner_item, types);
        ad.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        list.setAdapter(ad);
        list.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        input.setSelection(input.getText().length());

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(list);
        layout.addView(input);
        builder.setView(layout);

        builder.setPositiveButton("Add New Item",
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

        final AlertDialog dialog = builder.create();
        dialog.show();

        // Override defaults to prevent closing the dialog (validation)
        Button btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = list.getSelectedItem().toString();
                String name = input.getText().toString();

                if (name.isEmpty()) {
                    String message = "Name is required";
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ItemManager.createItem(type, name) == -1) {
                    String message = "Item already exists";
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Refresh search
                inputBox.setText(inputBox.getText().toString());

                Spanned message = Html.fromHtml("New item <strong>" + name +
                        "</strong> added under <strong>" + type + "</strong>");
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}
