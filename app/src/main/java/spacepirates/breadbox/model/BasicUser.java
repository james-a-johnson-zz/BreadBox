package spacepirates.breadbox.model;

/**
 * This class simply implements the abstract class User and allows for an instantiable superclass
 * for all different user types
 */
public class BasicUser extends User {

    /**
     * Constructor for the BasicUser class
     * This requires both a username and valid user type in order to be executed properly
     *
     * @param user      The username of the new user
     * @param t         The type of user. See UserType for all possibilities of users in the system
     */
    public BasicUser(String user, UserType t) {
        super(user, t);
    }
}
