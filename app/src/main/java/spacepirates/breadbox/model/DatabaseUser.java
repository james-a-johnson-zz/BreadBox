package spacepirates.breadbox.model;

public class DatabaseUser {
    public String username;
    public UserType type;
    public String locus;

    public DatabaseUser() {
    }

    public DatabaseUser(String username, UserType type) {
        this.username = username;
        this.type = type;
        locus = "NULL";
    }

    public DatabaseUser(String username, UserType type, String locus) {
        this(username, type);
        this.locus = locus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLocus() {
        return locus;
    }

    public void setLocus(String locus) {
        this.locus = locus;
    }
}
