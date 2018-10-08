package spacepirates.breadbox;
import java.util.List;

public class Admin extends BasicUser {

    //Username and password are protected variables in superclass
    public Admin(String user, String pass) {
        username = user;
        password = pass;
    }

    //Add and remove manager are booleans dependent on whether or not they execute properly
    public boolean addManager(Manager man) {
        return false;
    }

    public boolean removeManager(Manager man) {
        return false;
    }

    public boolean updatePassword(String newPassword) {
        return false;
    }

    public List<?> searchLocation() {
        return null;
    }

    public List<?> searchItem() {
        return null;
    }

    public String comment() {
        return "";
    }

    public boolean addBusiness() {
        return false;
    }

    public boolean removeBusiness() {
        return false;
    }
}
