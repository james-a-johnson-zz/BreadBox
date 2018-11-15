package spacepirates.breadbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Model;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DonationItemFilterFragment} interface
 * to handle interaction events.
 * Use the {@link DonationItemFilterFragment} factory method to
 * create an instance of this fragment.
 */
public class DonationItemFilterFragment extends Fragment {

    private TextView noMatchesView;
    private TextView nameInput;
    private Spinner categorySpinner;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_item_filter, container, false);

        //NoMatchesView's visibility should be set to GONE when the recyclerView is populated.
        noMatchesView = view.findViewById(R.id.search_failure_display);
        nameInput = view.findViewById(R.id.name_filter_input);
        categorySpinner = view.findViewById(R.id.category_filter_spinner);
        recyclerView = view.findViewById(R.id.filter_display_rv);
        Button nameFilterButton = view.findViewById(R.id.name_filter_button);
        Button categoryFilterButton = view.findViewById(R.id.category_filter_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //Currently setting to display all items. I feel it should initially be initialized empty
        Model instance = Model.getInstance();
        DonationItemRecyclerAdapter adapter =
                new DonationItemRecyclerAdapter(instance.getDonations());
        recyclerView.setAdapter(adapter);

        //Set Spinner with values of category enum.
        // Cast cannot be checked, because in the conversion from enum to list, java loses track
        // of the type and only sees Category and therefor can' be cast to String.
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item, Category.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        nameFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DonationItem> filterResult;
                SpannableStringBuilder nameText = (SpannableStringBuilder) nameInput.getText();
                String name = nameText.toString();
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

        return view;
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
