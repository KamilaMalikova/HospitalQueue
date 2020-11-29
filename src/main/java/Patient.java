import org.json.JSONObject;

public class Patient implements Comparable<Patient>{
    private String id;
    private String name;
    private int age;
    private Location location;
    private int acceptedOffers;
    private int canceledOffers;
    private int averageReplyTime; // in seconds
    private double distanceToFacility;
    private double score;
    /**
     * This constructor initializes instance based on provided latitude and longitude
     **/
    public Patient(String id, String name, int age, Location location, int acceptedOffers, int canceledOffers, int averageReplyTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.location = location;
        this.acceptedOffers = acceptedOffers;
        this.canceledOffers = canceledOffers;
        this.averageReplyTime = averageReplyTime;
    }
    /**
     * This constructor initializes instance based on provided JSON
     *
     * Important
     * JSON object has to contain keys:
     *          id, name, age, location, acceptedOffers, canceledOffers, averageReplyTime
     **/
    public Patient(JSONObject object) {
        this.id = object.getString("id");
        this.name = object.getString("name");
        this.age = object.getInt("age");
        this.location = new Location(object.getJSONObject("location"));
        this.acceptedOffers = object.getInt("acceptedOffers");
        this.canceledOffers = object.getInt("canceledOffers");
        this.averageReplyTime = object.getInt("averageReplyTime");
    }
    /**
     * Sets the score of patient
     **/
    public void setScore(double score){
        this.score = score;
    }
    /**
     * Sets the distance to facility
     **/
    public void setDistanceToFacility(double distanceToFacility) {
        this.distanceToFacility = distanceToFacility;
    }
    /**
     * Returns the name of patient
     **/
    public String getName() {
        return name;
    }
    /**
     * Returns the distance to facility
     **/
    public double getDistanceToFacility() {
        return distanceToFacility;
    }
    /**
     * Returns the score of patient
     **/
    public double getScore(){
        return this.score;
    }
    /**
     * Returns the current location of patient
     **/
    public Location getLocation(){
        return this.location;
    }
    /**
     * Returns the id of patient
     **/
    public String getId() {
        return id;
    }
    /**
     * Returns the age of patient
     **/
    public int getAge() {
        return age;
    }
    /**
     * Returns the number of accepted offers of patient
     **/
    public int getAcceptedOffers() {
        return acceptedOffers;
    }
    /**
     * Returns the number of canceled offers of patient
     **/
    public int getCanceledOffers() {
        return canceledOffers;
    }
    /**
     * Returns the average reply time of patient
     **/
    public int getAverageReplyTime() {
        return averageReplyTime;
    }
    /**
     * Returns 1 if the score of patient is higher then the score of patient o
     * Returns 0 if the score of patient is equal to the  score of patient o
     * Returns -1 if the score of patient is smaller then the score of patient o
     **/
    @Override
    public int compareTo(Patient o) {
        if (this.score > o.score) return 1;
        else  if (this.score == o.score) return  0;
        else return -1;
    }
    /**
     * Returns string representation of the instance
     **/
    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", location=" + location +
                ", acceptedOffers=" + acceptedOffers +
                ", canceledOffers=" + canceledOffers +
                ", averageReplyTime=" + averageReplyTime +
                ", distanceToFacility=" + distanceToFacility +
                ", score=" + score +
                '}';
    }
}
