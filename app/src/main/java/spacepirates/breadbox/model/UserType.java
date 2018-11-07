package spacepirates.breadbox.model;

/**
 * Specifies possible types of users
 * These users will have different permissions and abilities when dealing with the system
 */
public enum UserType {
    GUEST("Guest"),
    BASIC("Basic"),
    LOCATION_EMPLOYEE("Location Employee"),
    MANAGER("Manager"),
    ADMINISTRATOR("Administrator");

    private final String repr;
    UserType(String repr) {
        this.repr = repr;
    }

    @Override
    public String toString() {
        return repr;
    }

    /**
     * @return Representation of the user type
     */
    public String getRepr() {
        return repr;
    }
}
