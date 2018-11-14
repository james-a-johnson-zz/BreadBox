package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;
import spacepirates.breadbox.model.Tag;

/**
 * This activity allows users to add donation items to a location's inventory
 * Options include:
 * Naming the item
 * Categorizing the item and giving it relevant tags
 * Giving the item a price
 * Adding the donor of the item to its data
 * Adding a short description of the item
 */
public class AddDonationItemActivity extends AppCompatActivity {

    private EditText nameView;
    private EditText priceView;
    private EditText descriptionView;
    private Spinner categorySpinner;

    private Location location;
    private int i; //used for keeping track of the location while navigating between activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Location location = (Location)i.getParcelableExtra(getString(R.string.pass_location_key));
        //location = (Location)this.getIntent().getSerializableExtra("location");
        Intent intent = this.getIntent();
        i = intent.getIntExtra("location_index", -1);
        List<Location> locations;
        Model instance = Model.getInstance();
        locations = instance.getLocations();
        location = locations.get(i);

        Log.d("AddDonation", "Got Location: " + location);

        setContentView(R.layout.activity_add_donation_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameView = findViewById(R.id.add_donation_input_name);
        priceView = findViewById(R.id.add_donation_input_price);
        EditText tagView = findViewById(R.id.add_donation_input_tags);
        descriptionView = findViewById(R.id.add_donation_input_description);
        EditText donorView = findViewById(R.id.add_donation_input_donor);
        categorySpinner = findViewById(R.id.add_donation_category_spinner);


        // Category spinner uses the category enum to populate the spinner.
        // Cast cannot be checked, because in the conversion from enum to list, java loses track
        // of the type and only sees Category and therefor can' be cast to String.
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Category.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDonationItem();
            }
        });


    }

    private void createDonationItem() {
        Context context = getApplicationContext();
        String failureMessage = "Must provide a name and price.";
        boolean validDonation;
        String name;
        Category category;
        double price;
        //Category must be entered, because it might be used for mapping in the database
        category = (Category) categorySpinner.getSelectedItem();

        try {
            EditText priceText = (EditText) priceView.getText();
            price = Double.parseDouble(priceText.toString());
        } catch (NumberFormatException e) {
            //0 is not a valid price, will fail add
            price = 0;
        }
        EditText nameText = (EditText) nameView.getText();
        name = nameText.toString();
        EditText descriptionText = (EditText) descriptionView.getText();
        String description = descriptionText.toString();

        //tag view should be a multi selection spinner or similar, because tags are enums
        List<Tag> tags = new ArrayList<>();
        //splits the string in the tags box into words, and places in String array tags
        //( tagView.getText().toString()).split("\\W+");

        //implement adding users to donation items
        //User donor = donorView.getText().toString();



        DonationItem newItem = new DonationItem.DonationItemBuilder(name)
                .price(price)
                .category(category).tags(tags)
                .description(description)
                .address(location.getAddress())
                .build();

        //Add donation item to donation item database
        Model instance = Model.getInstance();
        validDonation = instance.addDonationItem(newItem);

        if (validDonation) {
            //create and display a toast to indicate success.
            String successMessage = name + " added to " + location + ".";
            Toast.makeText(getApplicationContext(), successMessage, Toast.LENGTH_SHORT).show();


            //navigate back to LocationActivity
            Intent intent = new Intent(context, LocationActivity.class);
            intent.putExtra("getString(R.string.pass_location_key)", Integer.valueOf(i));
            context.startActivity(intent);
        } else {
            //Display a toast if the Donation is not valid with an explanation.
            Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();
        }
    }

}
