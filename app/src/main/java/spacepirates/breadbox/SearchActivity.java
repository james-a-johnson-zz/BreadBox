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

import java.util.List;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Model;

/**
 * Allows users to search/filter for donation items by name and category
 */
public class SearchActivity extends AppCompatActivity {


    private TextView noMatchesView;
    private TextView nameInput;
    private Spinner categorySpinner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
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
        Button nameFilterButton = findViewById(R.id.name_filter_button);
        Button categoryFilterButton = findViewById(R.id.category_filter_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //Currently setting to display all items. I feel it should initially be initialized empty
        DonationItemRecyclerAdapter adapter =
                new DonationItemRecyclerAdapter(Model.getInstance().getDonations());
        recyclerView.setAdapter(adapter);

        //Set Spinner with values of category enum.
        // Cast cannot be checked, because in the conversion from enum to list, java loses track
        // of the type and only sees Category and therefor can' be cast to String.
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, Category.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        nameFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DonationItem> filterResult;
                String name = nameInput.getText().toString();
                Model model = Model.getInstance();
                filterResult = model.filterDonationItems(name);
                setRecycler(filterResult);
            }
        });

        categoryFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DonationItem> filterResult;
                Category category = (Category) categorySpinner.getSelectedItem();
                Model model = Model.getInstance();
                filterResult = model.filterDonationItems(category);
                setRecycler(filterResult);
            }
        });

    }

    private void setRecycler(List<DonationItem> donationItems) {
        DonationItemRecyclerAdapter adapter = new DonationItemRecyclerAdapter(donationItems);
        recyclerView.setAdapter(adapter);
        if (donationItems.isEmpty()) {
            noMatchesView.setVisibility(View.VISIBLE);
        } else {
            noMatchesView.setVisibility(View.GONE);
        }
    }
}
