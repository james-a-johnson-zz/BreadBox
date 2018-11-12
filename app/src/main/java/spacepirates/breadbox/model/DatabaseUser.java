package spacepirates.breadbox.model;

/**
 * User that is similar to BasicUser but also can have a location to interact with that location's
 * Items and database
 */
public class DatabaseUser {
    private String username;
    private UserType type;
    private String locus;

    /**
     * Constructor for a user that utilizes the databases and is not provided a location
     * @param username      Self explanatory
     * @param type          User type
     */
    public DatabaseUser(String username, UserType type) {
        this.username = username;
        this.type = type;
        locus = "NULL";
    }

    /**
     * Same as above but has a specific location tied to them
     * @param username      Self explanatory
     * @param type          User type
     * @param locus         Location to specify which database to interact with
     */
    public DatabaseUser(String username, UserType type, String locus) {
        this(username, type);
        this.locus = locus;
    }

    /**
     * Getter for the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username     new data to set username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return type     Type of user
     */
    public UserType getType() {
        return type;
    }

    /**
     * @param type Set the user type to a new type
     */
    public void setType(UserType type) {
        this.type = type;
    }

//    public String getLocus() {
//        return locus;
//    }
//
//    public void setLocus(String locus) {
//        this.locus = locus;
//    }
}
