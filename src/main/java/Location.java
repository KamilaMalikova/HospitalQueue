import org.json.JSONException;
import org.json.JSONObject;

public class Location {

    private double longitude;
    private double latitude;
    /**
     * Default constructor.
     * This constructor initializes default instance with longitude and latitude equal to 0.0
     * */
    public Location() {
        this.longitude = 0.0;
        this.latitude = 0.0;
    }
    /**
     * This constructor initializes instance based on provided latitude and longitude
    **/
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    /**
     * This constructor initializes instance based on provided JSON
     *
     * Important
     * JSON object has to contain keys latitude and longitude
     **/
    public Location(JSONObject object) throws JSONException {
        this.latitude = object.getDouble("latitude");
        this.longitude = object.getDouble("longitude");
    }
    /**
     * Returns longitude
     **/
    public double getLongitude() {
        return longitude;
    }
    /**
     * Returns latitude
     **/
    public double getLatitude() {
        return latitude;
    }
    /**
     * Returns the distance to location using Pythagorean theorem
     **/
    public double distanceTo(Location location){
        double a = Math.pow(Math.abs(this.latitude - location.latitude), 2);
        double b = Math.pow(Math.abs(this.longitude - location.longitude), 2);
        return Math.sqrt(a+b);
    }
    /**
     * Returns string representation of the instance
    **/
    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
