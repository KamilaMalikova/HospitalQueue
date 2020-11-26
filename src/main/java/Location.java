import org.json.JSONObject;

public class Location {
    private double longitude;
    private double latitude;

    public Location() {
        this.longitude = 0.0;
        this.latitude = 0.0;
    }

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(JSONObject object) {
        this.longitude = object.getDouble("longitude");
        this.latitude = object.getDouble("latitude");
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double distanceTo(Location location){
        double a = Math.pow(Math.abs(this.latitude - location.latitude), 2);
        double b = Math.pow(Math.abs(this.longitude - location.longitude), 2);
        return Math.sqrt(a+b);
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
