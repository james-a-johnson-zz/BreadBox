package spacepirates.breadbox;

import java.util.ArrayList;
import java.util.Queue;

import org.junit.Test;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.DonationItemDatabase;

import static org.junit.Assert.*;

public class LoganTest {
    @Test
    public void getItemsByCategory_isCorrect() {
        ArrayList<DonationItem> d = new ArrayList<>();
        DonationItem item = new DonationItem();
        item.setName("Shirt");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("Shorts");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("TV");
        item.setCategory(Category.ELECTRONICS);
        d.add(item);
        item = new DonationItem();
        item.setName("Whisk");
        item.setCategory(Category.KITCHENWARE);
        DonationItemDatabase dd = new DonationItemDatabase(d);
        Queue<DonationItem> filt = dd.getItemsByCategory(d, Category.APPAREL);
        assertEquals(filt.poll().getName(), "Shirt");
        assertEquals(filt.poll().getName(), "Shorts");
        assertTrue(filt.isEmpty());
    }

    @Test
    public void getItemsByCategory_isIncorrect() {
        ArrayList<DonationItem> d = new ArrayList<>();
        DonationItem item = new DonationItem();
        item.setName("Shirt");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("Shorts");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("TV");
        item.setCategory(Category.ELECTRONICS);
        d.add(item);
        item = new DonationItem();
        item.setName("Whisk");
        item.setCategory(Category.KITCHENWARE);
        DonationItemDatabase dd = new DonationItemDatabase(d);
        Queue<DonationItem> filt = dd.getItemsByCategory(dd.getDonations(), Category.ELECTRONICS);
        assertNotEquals(filt.poll().getName(), "Shirt");
    }

    @Test
    public void getItemsByCategory_empty() {
        ArrayList<DonationItem> d = new ArrayList<>();
        DonationItemDatabase dd = new DonationItemDatabase(d);
        Queue<DonationItem> filt = dd.getItemsByCategory(dd.getDonations(), Category.APPAREL);
        assertTrue(filt.isEmpty());
    }

    @Test
    public void getItemsByCategory_nullCat() {
        ArrayList<DonationItem> d = new ArrayList<>();
        DonationItem item = new DonationItem();
        item.setName("Shirt");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("Shorts");
        item.setCategory(Category.APPAREL);
        d.add(item);
        item = new DonationItem();
        item.setName("TV");
        item.setCategory(Category.ELECTRONICS);
        d.add(item);
        item = new DonationItem();
        item.setName("Whisk");
        item.setCategory(Category.KITCHENWARE);
        DonationItemDatabase dd = new DonationItemDatabase(d);
        Queue<DonationItem> filt = dd.getItemsByCategory(dd.getDonations(), null);
        assertTrue(filt.isEmpty());
    }

    @Test
    public void getItemsByCategory_nullList() {
        try {
            ArrayList<DonationItem> d = null;
            DonationItemDatabase dd = new DonationItemDatabase(d);
            Queue<DonationItem> filt = dd.getItemsByCategory(dd.getDonations(), Category.APPAREL);
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
}
