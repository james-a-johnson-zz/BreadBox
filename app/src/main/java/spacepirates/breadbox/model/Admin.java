package spacepirates.breadbox.model;

public class Admin extends BasicUser {

    //Username and password are protected variables in superclass
    public Admin(String user, String pass) {
        super(user, UserType.ADMINISTRATOR, pass);
    }

    //Add and remove manager are booleans dependent on whether or not they execute properly
    public boolean addManager(Manager man) {
        return false;
    }

    public Manager removeManager(Manager man) {
        return null;
    }

    public boolean updatePassword(String newPassword) {
        return false;
    }

    public boolean addLocation(Location locus) {
        return false;
    }

    public Location removeLocation(Location locus) {
        return null;
    }
}
