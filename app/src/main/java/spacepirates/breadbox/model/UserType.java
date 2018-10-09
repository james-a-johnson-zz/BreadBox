package spacepirates.breadbox.model;

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

    public String getRepr() {
        return repr;
    }
}
