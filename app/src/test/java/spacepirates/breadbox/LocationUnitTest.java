package spacepirates.breadbox;

import org.junit.Test;
import org.junit.Assert;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;

/**
 * JUnit Test(s) for the Location class
 * @author Michaelb
 */
public class LocationUnitTest {

    /**
     * Test adding an item to a location's inventory and make sure it's actually there
     */
    @Test
    public void checkCorrectlyAddedItem() {
        DonationItem item = new DonationItem
                .DonationItemBuilder("Bike")
                .price(50.0)
                .category(Category.TOY)
                .build();
        spacepirates.breadbox.model.Location narnia = new spacepirates.breadbox.model.Location();
        narnia.addItem(item);

        DonationItem check = new DonationItem
                .DonationItemBuilder("Bike")
                .price(50.0)
                .category(Category.TOY)
                .build();

        Assert.assertEquals(item, check);
        Assert.assertTrue(narnia.getInventory().contains(check));
        Assert.assertEquals(1, narnia.getInventory().size());
    }

    @Test
    public void checkAddingTwoItems() {
        DonationItem item = new DonationItem
                .DonationItemBuilder("Bike")
                .price(50.0)
                .category(Category.TOY)
                .build();
        DonationItem wrongItem = new DonationItem
                .DonationItemBuilder("Used Bike")
                .price(25.0)
                .category(Category.TOY)
                .build();
        spacepirates.breadbox.model.Location acrossThePond = new spacepirates.breadbox.model.Location();

        Assert.assertNotEquals(item, wrongItem);

        acrossThePond.addItem(item);
        Assert.assertFalse(acrossThePond.getInventory().contains(wrongItem));

        acrossThePond.addItem(wrongItem);
        Assert.assertEquals(2, acrossThePond.getInventory().size());
    }
}
