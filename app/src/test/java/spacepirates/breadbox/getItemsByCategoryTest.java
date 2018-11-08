package spacepirates.breadbox;

import org.junit.Test;
import org.junit.Assert;

import java.util.Queue;
import java.util.List;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.DonationItemDatabase;

public class getItemsByCategoryTest {

    @Test
    public void checkItemsByCategoryFilter(DonationItemDatabase d, Category cat) {
        List<DonationItem> donationItems = d.getDonations();
        Queue<DonationItem> result = d.getItemsByCategory(donationItems, cat);
        for (DonationItem item : result) {
            Assert.assertEquals(item.getCategory(), cat);
        }
    }
}
