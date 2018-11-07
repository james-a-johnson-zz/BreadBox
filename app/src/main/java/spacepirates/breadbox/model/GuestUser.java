package spacepirates.breadbox.model;

/**
 * Simple user that does not have the same capabilities as typical users in the application
 * Also does not have a username
 */
public class GuestUser extends User {

    /**
     * Constructor that simply assigns a sentinel value of "Guest" to the user's username
     * Also specifies the user type of GUEST
     */
    GuestUser() {
        super("Guest", UserType.GUEST);
    }

}
