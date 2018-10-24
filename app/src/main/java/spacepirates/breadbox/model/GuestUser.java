package spacepirates.breadbox.model;

public class GuestUser extends User {

    GuestUser() {
        super("Guest", UserType.GUEST);
    }

}
