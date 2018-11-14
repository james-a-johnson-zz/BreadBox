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
        DonationItem sweatshirt = new DonationItem
                .DonationItemBuilder("swearshirt")
                .price(10.0)
                .category(Category.APPAREL)
                .build();
        DonationItem tanktop = new DonationItem
                .DonationItemBuilder("tanktop")
                .price(5.0)
                .category(Category.APPAREL)
                .build();
        DonationItem iron = new DonationItem
                .DonationItemBuilder("iron")
                .price(12.0)
                .category(Category.ELECTRONICS)
                .build();
        DonationItem brownie_pan = new DonationItem
                .DonationItemBuilder("brownie pan")
                .price(6.0)
                .category(Category.KITCHENWARE)
                .build();
        DonationItem jeans = new DonationItem
                .DonationItemBuilder("jeans")
                .price(5.0)
                .category(Category.APPAREL)
                .build();

        List<DonationItem> itemList = new ArrayList<>();
        itemList.add(sweatshirt);
        itemList.add(tanktop);
        itemList.add(iron);
        itemList.add(brownie_pan);
        itemList.add(jeans);

        DonationItemDatabase donationItemDB = new DonationItemDatabase(itemList);
        //donationItemDB.addInventory(itemList);

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

        while(!testQ.isEmpty()){
            assertEquals(testQ.poll().getName(), trueQ.poll().getName());
        }

        //System.out.println(testQ.poll().getName());
        //assertEquals(testQ.poll(), trueQ.poll());


   }

}
