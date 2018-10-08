package spacepirates.breadbox;
import java.util.List;

abstract class BasicUser {

    protected String username;
    protected String password;

    abstract List<?> searchLocation();
    abstract List<?> searchItem();
    abstract String comment();
}
