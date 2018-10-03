public class Location {

    private String name;
    private String type;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;

    public Location(String name, String type, String longitude, String latitude, String address, String phoneNumber) {
        this.name = name;
        this.type = type;
        this.longitude = new Double(longitude);
        this.latitude = new Double(latitude);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //Only use with correctly formatted csv
    public Location(String[] arr) {
        Location(arr[1], arr[8], arr[3], arr[2], arr[4], arr[9]);
    }
}