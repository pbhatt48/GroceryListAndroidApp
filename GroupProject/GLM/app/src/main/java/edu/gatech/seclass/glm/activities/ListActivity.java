package edu.gatech.seclass.glm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.R.drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.adapters.ItemAdapter;
import edu.gatech.seclass.glm.db.ItemManager;
import edu.gatech.seclass.glm.models.GroceryItem;

public class ListActivity extends AppCompatActivity {
    private ItemAdapter ir;
    private int listId;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listId = getIntent().getIntExtra("listId", 1);
        final String listName = getIntent().getStringExtra("listName");

        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(listName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadList();

        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.add);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("listId", listId);
                intent.putExtra("listName", listName);
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton toggleFab = (FloatingActionButton) findViewById(R.id.toggle);
        toggleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ItemManager.toggleList(listId, checked);
                loadList();
                updateCheck(checked);
            }
        });
    }

    private void loadList() {
        ListView listView = (ListView) findViewById(R.id.list);
        List<GroceryItem> list = ItemManager.getItemsInList(listId);

        // Display the data
        ir = new ItemAdapter(this, list);
        checked = ir.isNoneChecked();
        listView.setAdapter(ir);
    }

    public void updateCheck(boolean checked) {
        this.checked = checked;
        FloatingActionButton toggleFab = (FloatingActionButton) findViewById(R.id.toggle);

        if (checked) {
            toggleFab.setImageResource(drawable.checkbox_on_background);
        } else {
            toggleFab.setImageResource(drawable.checkbox_off_background);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            int id = intent.getIntExtra("itemId", -1);

            if (id > -1) {
                // Refresh list
                loadList();

                GroceryItem item = ir.getItemById(id);
                Spanned message = Html.fromHtml("Added item <strong>" + item.getName()
                        + "</strong> to list");

                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                ir.showEditDialog(item);
            } else {
                Toast.makeText(this, "Item already exists on list", Toast.LENGTH_LONG).show();
            }
        }
    }
}
