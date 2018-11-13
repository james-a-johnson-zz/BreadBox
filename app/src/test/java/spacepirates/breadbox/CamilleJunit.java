package spacepirates.breadbox;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.DonationItemDatabase;
import spacepirates.breadbox.model.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CamilleJunit {


    @Test
    public void getItemsByNameTest() {
        DonationItem sweatshirt = new DonationItem("sweatshirt", 10, Category.APPAREL);
        DonationItem tanktop = new DonationItem("tanktop", 5, Category.APPAREL);
        DonationItem iron = new DonationItem("iron", 12, Category.ELECTRONICS);
        DonationItem brownie_pan = new DonationItem("brownie pan", 6, Category.KITCHENWARE);
        DonationItem jeans = new DonationItem("jeans", 5, Category.APPAREL);

        List<DonationItem> itemList= new ArrayList<>();
        itemList.add(sweatshirt);
        itemList.add(tanktop);
        itemList.add(iron);
        itemList.add(brownie_pan);
        itemList.add(jeans);

        DonationItemDatabase donationItemDB = new DonationItemDatabase();
        donationItemDB.addInventory(itemList);

        Queue<DonationItem> trueQ =  donationItemDB.getItemsByName(donationItemDB.getDatabase(),
                "brownie pan");
        Collections.sort(itemList, new Comparator<DonationItem>(){
            @Override
            public int compare(DonationItem item1, DonationItem item2){
                return item1.getName().compareTo("brownie pan")
                        - item2.getName().compareTo("brownie pan");
             }
        });

        Queue<DonationItem> testQ = new PriorityQueue<>();
        testQ.addAll(itemList);

        assertEquals(testQ.poll(), trueQ.poll());


   }

}
