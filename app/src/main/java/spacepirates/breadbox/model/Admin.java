package spacepirates.breadbox.model;

/**
 * Admin is a type of user that has more capabilities modifying the system than any other user
 * The admin manages users as well as locations in their respective databases
 */
public class Admin extends BasicUser {

    /**
     * Admin constructor that is called when the system knows it needs to create an admin
     *
     * @param user      The string that correspond's to the admin's username
     */
    public Admin(String user) {
        super(user, UserType.ADMINISTRATOR);
    }

//    public Admin(String user, UserType ut) {
//        super(user, ut);
//    }

//    //Add and remove manager are booleans dependent on whether or not they execute properly
//    public boolean addManager(Manager man) {
//        return false;
//    }
//
//    public Manager removeManager(Manager man) {
//        return null;
//    }
//
//    public boolean addLocation(Location locus) {
//        return false;
//    }
//
//    public Location removeLocation(Location locus) {
//        return null;
//    }
}
