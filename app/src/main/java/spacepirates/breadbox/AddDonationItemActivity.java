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

    EditText nameView;
    EditText priceView;
    EditText tagView;
    EditText descriptionview;
    EditText donorView;
    Spinner categorySpinner;

    Location location;
    int i; //used for keeping track of the location while navigating between activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Location location = (Location)i.getParcelableExtra(getString(R.string.pass_location_key));
        //location = (Location)this.getIntent().getSerializableExtra("location");
        i = this.getIntent().getIntExtra("location_index", -1);
        List<Location> locations;
        locations = Model.getInstance().getLocations();
        location = locations.get(i);

        Log.d("AddDonation", "Got Location: " + location);

        setContentView(R.layout.activity_add_donation_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameView = findViewById(R.id.add_donation_input_name);
        priceView = findViewById(R.id.add_donation_input_price);
        tagView = findViewById(R.id.add_donation_input_tags);
        descriptionview = findViewById(R.id.add_donation_input_description);
        donorView = findViewById(R.id.add_donation_input_donor);
        categorySpinner = findViewById(R.id.add_donation_category_spinner);


        // Category spinner uses the category enum to populate the spinner.
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
        String failureMessage = "";
        boolean validDonation = true;
        String name = nameView.getText().toString();
        Category category;
        double price = 0;
        if (name.isEmpty()) {
            validDonation = false;
            failureMessage += "Must enter a name. ";
        }
        try {
            price = Double.parseDouble(priceView.getText().toString());
        } catch (NumberFormatException e) {
            validDonation = false;
            failureMessage += "Price must be a number. ";
        }

        //TODO tag view should be a multi selection spinner or similar, because tags are enums;
        List<Tag> tags = new ArrayList<>();
        //splits the string in the tags box into words, and places in String array tags
        //( tagView.getText().toString()).split("\\W+");
        String description = descriptionview.getText().toString();

        //TODO implement adding users to donation items
        //User donor = donorView.getText().toString();

        //Category must be entered, because it might be used for mapping in the datbase
        category = (Category) categorySpinner.getSelectedItem();
        if (category == null) {
            //If no category, invalidate donation and make a note in the failure message.
            validDonation = false;
            failureMessage += "Must select a Category.";
        }
        if (validDonation) {
            //Create new Donation Item. DonationItem constructor adds it to the location inventory.
            DonationItem newItem = new DonationItem(name, price, category, description,
                    tags, location.getAddress());
            //Add donation item to donation item datbase
            Model.getInstance().addDonationItem(newItem);

            //create and display a toast to indicate success.
            String successMessage = name + " added to " + location + ".";
            Toast.makeText(getApplicationContext(), successMessage, Toast.LENGTH_SHORT).show();


            //navigate back to LocationActivity
            Intent intent = new Intent(context, LocationActivity.class);
            intent.putExtra("getString(R.string.pass_location_key)", new Integer(i));
            context.startActivity(intent);
        } else {
            //Display a toast if the Donation is not valid with an explanation.
            Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();
        }
    }

}
