package spacepirates.breadbox.model;


import java.util.List;

//TODO other user classes must extend user
public abstract class User {

    /**
     * Will throw a LocationDatabaseNotInitializedException if the database is not initialized.
     */
    public List<Location> searchLocation() throws Exception{
        Model model = Model.getInstance();
        return model.getLocations();
    }

    public List<DonationItem> searchItem() {
        return null;
    }
}
