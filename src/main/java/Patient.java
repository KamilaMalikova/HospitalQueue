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

    public Patient(String id, String name, int age, Location location, int acceptedOffers, int canceledOffers, int averageReplyTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.location = location;
        this.acceptedOffers = acceptedOffers;
        this.canceledOffers = canceledOffers;
        this.averageReplyTime = averageReplyTime;
    }

    public Patient(JSONObject object) {
        this.id = object.getString("id");
        this.name = object.getString("name");
        this.age = object.getInt("age");
        this.location = new Location(object.getJSONObject("location"));
        this.acceptedOffers = object.getInt("acceptedOffers");
        this.canceledOffers = object.getInt("canceledOffers");
        this.averageReplyTime = object.getInt("averageReplyTime");
    }

    public void setScore(double score){
        this.score = score;
    }

    public void setDistanceToFacility(double distanceToFacility) {
        this.distanceToFacility = distanceToFacility;
    }

    public double getScore(){
        return this.score;
    }

    public Location getLocation(){
        return this.location;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getAcceptedOffers() {
        return acceptedOffers;
    }

    public int getCanceledOffers() {
        return canceledOffers;
    }

    public int getAverageReplyTime() {
        return averageReplyTime;
    }


    @Override
    public int compareTo(Patient o) {
        if (this.score > o.score) return 1;
        else  if (this.score == o.score) return  0;
        else return -1;
    }


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
