package spacepirates.breadbox;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Tag;

/**
 * Class that assists obtaining Donation Items for display from donation item database
 */
public class DonationItemRecyclerAdapter extends
        RecyclerView.Adapter<DonationItemRecyclerAdapter.DonationViewHolder> {

    private final List<DonationItem> donations;

    //recycler allows a total of one card to be expanded,
    //and uses this reference to collapse old expanded card
    @Nullable
    private DonationViewHolder expandedViewHolder;

    DonationItemRecyclerAdapter(List<DonationItem> donations) {
        this.donations = donations;
    }

    //expands description of card view
    private void expandDonationView(DonationViewHolder v) {
        v.description.setVisibility(View.VISIBLE);
        v.category.setVisibility(View.VISIBLE);
        v.tags.setVisibility(View.VISIBLE);
    }

    //collapses description in card view
    private void collapseDonationView(DonationViewHolder v) {
        v.description.setVisibility(View.GONE);
        v.category.setVisibility(View.GONE);
        v.tags.setVisibility(View.GONE);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.donation_item_card, viewGroup, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull final DonationViewHolder donationViewHolder, final int i) {
        DonationItem donation = donations.get(i);
        String tags = "";
        if (donation.getTags() != null) {
            for (Tag t : donation.getTags()) {
                tags += t + " ";
            }
        }

        donationViewHolder.itemName.setText(donation.getName());
        donationViewHolder.description.setText(donation.getDescription());
        donationViewHolder.price.setText("- " + donation.getPrice());
        donationViewHolder.category.setText(donation.getCategory().toString());
        donationViewHolder.tags.setText(tags);

        //default for card view is collapsed form.
        collapseDonationView(donationViewHolder);

        donationViewHolder.cv.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO navigate to Donation detail view.

                //On expand
                if (expandedViewHolder != null) {
                    //collapse old expanded view if one is expanded
                    collapseDonationView(expandedViewHolder);

                    if (expandedViewHolder.equals(donationViewHolder)) {
                        //If this is view was expanded. Set expanded null.
                        //All cards should be collapsed.
                        expandedViewHolder = null;
                    } else {
                        //expand new view and relocate flag
                        expandDonationView(donationViewHolder);
                        expandedViewHolder = donationViewHolder;
                    }
                } else {
                    //a different view is expanded
                    //expand this description and mark as expanded
                    expandedViewHolder = donationViewHolder;
                    expandDonationView(donationViewHolder);
                }
            }


        }));
    }

    @Override
    public int getItemCount() {
        return donations.size();
    }

    public static class DonationViewHolder extends RecyclerView.ViewHolder {

        final CardView cv;
        final TextView itemName;
        final TextView description;
        final TextView price;
        final TextView category;
        final TextView tags;


        DonationViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.donation_item_card);
            itemName = itemView.findViewById(R.id.donation_card_name);
            description = itemView.findViewById(R.id.donation_card_description);
            price = itemView.findViewById(R.id.donation_card_price);
            category = itemView.findViewById(R.id.donation_card_category);
            tags = itemView.findViewById(R.id.donation_card_tags);

        }
    }
}