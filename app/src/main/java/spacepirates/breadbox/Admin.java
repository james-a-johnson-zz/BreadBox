package spacepirates.breadbox;
import java.util.List;

public class Admin extends BasicUser {

    //Username and password are protected variables in superclass
    public Admin(String user, String pass) {
        username = user;
        password = pass;
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
}
