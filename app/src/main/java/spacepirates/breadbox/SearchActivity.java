package spacepirates.breadbox;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Model;

public class SearchActivity extends AppCompatActivity {


    TextView noMatchesView;
    TextView nameInput;
    Spinner categorySpinner;
    RecyclerView recyclerView;
    Button nameFilterButton;
    Button categoryFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //NoMatchesView's visibility should be set to GONE when the recyclerView is populated.
        noMatchesView = findViewById(R.id.search_failure_display);
        nameInput = findViewById(R.id.name_filter_input);
        categorySpinner = findViewById(R.id.category_filter_spinner);
        recyclerView = findViewById(R.id.filter_display_rv);
        nameFilterButton = findViewById(R.id.name_filter_button);
        categoryFilterButton = findViewById(R.id.category_filter_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //Set Spinner with values of category enum.
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Category.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        nameFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<DonationItem> filterResult;
                String name = nameInput.getText().toString();
                Model model = Model.getInstance();
                filterResult = model.filterDonationItems(name);
                setRecycler(filterResult);
            }
        });

        categoryFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<DonationItem> filterResult;
                Category category = (Category) categorySpinner.getSelectedItem();
                Model model = Model.getInstance();
                filterResult = model.filterDonationItems(category);
                setRecycler(filterResult);
            }
        });

    }

    private void setRecycler (ArrayList<DonationItem> donationItems) {
        DonationItemRecyclerAdapter adapter = new DonationItemRecyclerAdapter(donationItems);
        recyclerView.setAdapter(adapter);
        if (donationItems.size() == 0) {
            noMatchesView.setVisibility(View.VISIBLE);
        } else {
            noMatchesView.setVisibility(View.GONE);
        }
    }

}
