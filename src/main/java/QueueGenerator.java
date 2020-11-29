import java.util.*;

public class QueueGenerator {
    /**
     * List of all patients
     **/
    private Map<String, Patient> patients;
    /**
     * Weights used to calculate a score of a patient
     **/
    private static double age_weight = 1.;
    private static double distance_weight = 1.;
    private static double accepted_offers_weight = 3.;
    private static double canceled_offers_weight = 3.;
    private static double average_response_time_weight = 2.;
    /**
     * Max possible values of each criteria used to calculate a score of patient
     *
     * Can be changed using setters
     **/
    private int maxAge = 100;
    private double maxDistance = 500;
    private int maxAcceptedOffers = 100;
    private int maxCanceledOffers = 100;
    private int maxAverageTime = 4000;

    /**
     * Default constructor.
     **/
    public QueueGenerator() {
    }
    /**
     *The constructor initializes a instance with provided list of patients
     **/
    public QueueGenerator(Map<String, Patient> patients) {
        this.patients = patients;
    }
    /**
     * Sets patients to provided list of patients
     **/
    public void setPatients(Map<String, Patient> patients) {
        this.patients = patients;
    }

    /**
     * Generates queue of 10 patients.
     * Input: Facility location
     * Output: List of 10 patients with highest score
     * The calculation is made using demographic and behavior weights
     *
     * Demographic
     *     age (weighted 10%)
     *     distance to practice (weighted 10%)
     *
     * Behavior
     *     number of accepted offers (weighted 30%)
     *     number of cancelled offers (weighted 30%)
     *     reply time (how long it took for patients to reply) (weighted 20%)
     **/
    public List<Patient> makeQueue(Location facility_location){
        //Binary tree with max score on the top
        PriorityQueue<Patient> patientsQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (Patient patient: this.patients.values()) {
            double score = this.getScore(facility_location, patient);
            patient.setScore(score);
            patientsQueue.add(patient);
        }

        List<Patient> queue = new LinkedList<>();
        int min = (Math.min(patientsQueue.size(), 10));
        for (int i = 0; i < min; i++) {
            queue.add(patientsQueue.poll());
        }
        queue.stream()
                .forEach(patient1 -> System.out.println(patient1.toString()));
        return queue;
    }
    /**
     * Counts and returns a score of patient according to provided location
     **/
    public double getScore(Location facility_location, Patient patient) {
        double distance_to_facility = patient.getLocation().distanceTo(facility_location);
        patient.setDistanceToFacility(distance_to_facility);

        //counts demographic score of a patient
        double demographic = this.getAgeScore(patient.getAge())
                + this.getDistanceScore(patient.getDistanceToFacility());

        //counts behaviour score of a patient
        double behaviour = this.getAcceptedOffersScore(patient.getAcceptedOffers())
                + this.getCanceledOffersScore(patient.getCanceledOffers())
                + this.getAverageResponseTimeScore(patient.getAverageReplyTime());
        //if behaviour is 85 percent of max behaviour weight set it to max value
        if (behaviour > (getMaxBehaviourWeight() * 0.85)){
            behaviour = getMaxBehaviourWeight();
        }
        double score = demographic + behaviour;
        return score;
    }

    /**
     * Counts and returns max behaviour weight
     **/
    private double getMaxBehaviourWeight() {
        return this.accepted_offers_weight + this.canceled_offers_weight + this.average_response_time_weight;
    }

    /**
     * Counts and returns an age score
     **/
    public double getAgeScore(int age){
        return age*age_weight/this.maxAge;
    }
    /**
     * Counts and returns a distance score
     **/
    public double getDistanceScore(double distance){
        return distance_weight - distance * distance_weight / maxDistance;
    }
    /**
     * Counts and returns an accepted offers score
     **/
    public double getAcceptedOffersScore(double offers){
        return offers *accepted_offers_weight / maxAcceptedOffers;
    }
    /**
     * Counts and returns a canceled offers score
     **/
    public double getCanceledOffersScore(double offers){
        return canceled_offers_weight - offers * canceled_offers_weight / maxCanceledOffers;
    }
    /**
     * Counts and returns an average responce score
     **/
    public double getAverageResponseTimeScore(int seconds){
        return average_response_time_weight - seconds * average_response_time_weight / maxAverageTime;
    }
    /**
     * Counts and returns a max score
     **/
    public double getMaxScore(){
        return age_weight + distance_weight + accepted_offers_weight + canceled_offers_weight + average_response_time_weight;
    }
    /**
     * Returns a max age
     **/
    public int getMaxAge() {
        return maxAge;
    }
    /**
     * Sets a max age
     **/
    public void setMaxAge(int maxAge) {
        if (maxAge == 0) throw new IllegalArgumentException();
        this.maxAge = maxAge;
    }
    /**
     * Returns a max distance
     **/
    public double getMaxDistance() {
        return maxDistance;
    }
    /**
     * Sets a max distance
     **/
    public void setMaxDistance(double maxDistance) {
        if (maxDistance == 0) throw new IllegalArgumentException();
        this.maxDistance = maxDistance;
    }
    /**
     * Returns a max accepted offers
     **/
    public int getMaxAcceptedOffers() {
        return maxAcceptedOffers;
    }
    /**
     * Sets a max accepted offers
     **/
    public void setMaxAcceptedOffers(int maxAcceptedOffers) {
        if (maxAcceptedOffers == 0) throw new IllegalArgumentException();
        this.maxAcceptedOffers = maxAcceptedOffers;
    }
    /**
     * Returns a max canceled offers
     **/
    public int getMaxCanceledOffers() {
        return maxCanceledOffers;
    }
    /**
     * Sets a max canceled offers
     **/
    public void setMaxCanceledOffers(int maxCanceledOffers) {
        if (maxCanceledOffers == 0) throw new IllegalArgumentException();
        this.maxCanceledOffers = maxCanceledOffers;
    }
    /**
     * Returns a max average reply time
     **/
    public int getMaxAverageTime() {
        return maxAverageTime;
    }
    /**
     * Sets a max average reply time
     **/
    public void setMaxAverageTime(int maxAverageTime) {
        if (maxAverageTime == 0) throw new IllegalArgumentException();
        this.maxAverageTime = maxAverageTime;
    }

}
