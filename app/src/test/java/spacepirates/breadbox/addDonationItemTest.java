package spacepirates.breadbox;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;
import spacepirates.breadbox.model.Tag;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class addDonationItemTest {

    List<Tag> tags = new ArrayList<>();
    DonationItem donationItem;
    Location location;
    int oldSize;
    Model model = new Model();

    private void initializeTests() {
        model.addLocation(new Location.LocationBuilder("Location")
                .type("store")
                .latitude(0.0)
                .longitude(0.0)
                .address("It's an address")
                .phoneNumber("pizza time")
                .build());
        location = model.getLocations().get(0);
        //tags.add(Tag.TOPS);
    }

    @Test
    public void addsValidDonation() {
        initializeTests();
        donationItem = new DonationItem.DonationItemBuilder("Test Item")
                .price(25.99)
                .category(Category.APPAREL)
                .description("This is a test item.")
                .tags(tags)
                .address(location.getAddress())
                .build();
        int oldSize = model.getDonations().size();
        model.addDonationItem(donationItem);

        assert(model.getDonations().contains(donationItem));
        assert(location.getInventory().contains(donationItem));
        assert(model.getDonations().size() == oldSize + 1);
    }

    @Test
    public void addsDuplicateDonation() {
        initializeTests();
        donationItem = new DonationItem.DonationItemBuilder("Test Item")
                .price(25.99)
                .category(Category.APPAREL)
                .description("This is a test item.")
                .tags(tags)
                .address(location.getAddress())
                .build();
        int oldSize = model.getDonations().size();
        model.addDonationItem(donationItem);

        assert(model.getDonations().contains(donationItem));
        assert(location.getInventory().contains(donationItem));
        assert(model.getDonations().size() == oldSize + 1);

        oldSize = model.getDonations().size();
        model.addDonationItem(donationItem);

        assert(model.getDonations().contains(donationItem));
        assert(location.getInventory().contains(donationItem));
        assert(model.getDonations().size() == oldSize + 1);
    }

        //adding donationItems:
        //must have name, price, location, category
        //ncecessary for donationItem to have price?
        //price can not be null (double), can't do contructor without price

    @Test
    public void nullName() {
        //Name is Null
        initializeTests();
        donationItem = new DonationItem.DonationItemBuilder(null)
                .price(25.99)
                .category(Category.APPAREL)
                .description("This is a test item.")
                .tags(tags)
                .address(location.getAddress())
                .build();
        oldSize = model.getDonations().size();
        model.addDonationItem(donationItem);

        //assert not added
        assert(!model.getDonations().contains(donationItem));
        assert(!location.getInventory().contains(donationItem));
        assert(model.getDonations().size() == oldSize);

    }

    @Test
    public void noMatchingAddress() {
        initializeTests();
        //Location doesn't match an address
        donationItem = new DonationItem.DonationItemBuilder(null)
                .price(25.99)
                .category(Category.APPAREL)
                .description("This is a test item.")
                .tags(tags)
                .address("You've got the wrong address, bud")
                .build();
        oldSize = model.getDonations().size();
        model.addDonationItem(donationItem);

        //assert not added
        assert(!model.getDonations().contains(donationItem));
        assert(!location.getInventory().contains(donationItem));
        assert(model.getDonations().size() == oldSize);
    }

    @Test
    public void nullCategory() {
        initializeTests();
        //Category is null
        donationItem = new DonationItem.DonationItemBuilder(null)
                .price(25.99)
                .category(Category.APPAREL)
                .description(null)
                .tags(tags)
                .address(location.getAddress())
                .build();
        oldSize = model.getDonations().size();
        model.addDonationItem(donationItem);

        //assert not added
        assert(!model.getDonations().contains(donationItem));
        assert(!location.getInventory().contains(donationItem));
        assert(model.getDonations().size() == oldSize);
    }
}