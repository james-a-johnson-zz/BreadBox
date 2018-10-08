package spacepirates.breadbox;
import java.util.List;

public class Admin extends BasicUser {

    //Username and password are protected variables in superclass
    public Admin(String user, String pass) {
        super(user, pass);
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

    public boolean addBusiness(Business bus) {
        return false;
    }

    public Business removeBusiness(Business bus) {
        return null;
    }
}
