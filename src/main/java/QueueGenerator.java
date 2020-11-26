import java.util.*;

public class QueueGenerator {

    private List<Patient> patients;

    // define weights
    private static double age_weight = 1.;
    private static double distance_weight = 1.;
    private static double accepted_offers_weight = 3.;
    private static double canceled_offers_weight = 3.;
    private static double average_response_time_weight = 2.;

    // define max possible values
    private int MAX_AGE = 100;
    private double MAX_DISTANCE = 200;
    private int MAX_ACCEPTED_OFFERS = 100;
    private int MAX_CANCELED_OFFERS = 100;
    private int MAX_AVERAGE_TIME = 4000;

    public QueueGenerator() {
    }

    public QueueGenerator(List<Patient> patients) {
        this.patients = patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }


    /*    Demographic

    age (weighted 10%)
    distance to practice (weighted 10%)
    Behavior

    number of accepted offers (weighted 30%)
    number of cancelled offers (weighted 30%)
    reply time (how long it took for patients to reply) (weighted 20%)*/
    // Determines a patient score based on provided data

    public void makeQueue(Location facility_location){
        PriorityQueue<Patient> patientsQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (Patient patient: this.patients) {

            double distance_to_facility = patient.getLocation().distanceTo(facility_location);
            patient.setDistanceToFacility(distance_to_facility);
            //new Patient("541d25c9-9500-4265-8967-240f44ecf723", "Samir Pacocha", 46, new Location(46.7110, -63.1150), 49, 92, 2598)

            //count score of patient
            double score = this.getAgeScore(patient.getAge())
                    + this.getAcceptedOffersScore(patient.getAcceptedOffers())
                    + this.getCanceledOffersScore(patient.getCanceledOffers())
                    + this.getAverageResponseTimeScore(patient.getAverageReplyTime());

            // if distance behavior is small start scoring from 9
            if (this.getDistanceScore(distance_to_facility) > (distance_weight *0.95)){
                score = score / this.getMaxScore() + 9;
            }
            patient.setScore(score);
            patientsQueue.add(patient);
//            System.out.println(score);
//            System.out.println(distance_to_facility);
        }

        List<Patient> queue = new LinkedList<>();
        for (int i = 0; i < (Math.min(patientsQueue.size(), 10)); i++) {
            queue.add(patientsQueue.poll());
        }

        queue.stream()
                .forEach(patient1 -> System.out.println(patient1.toString()));
    }

    public double getAgeScore(int age){
        return age*age_weight/this.MAX_AGE;
    }

    public double getDistanceScore(double distance){
        return distance_weight - distance * distance_weight / MAX_DISTANCE;
    }

    public double getAcceptedOffersScore(double offers){
        return offers *accepted_offers_weight / MAX_ACCEPTED_OFFERS;
    }

    public double getCanceledOffersScore(double offers){
        return canceled_offers_weight - offers * canceled_offers_weight / MAX_CANCELED_OFFERS;
    }

    public double getAverageResponseTimeScore(int seconds){
        return average_response_time_weight - seconds * average_response_time_weight / MAX_AVERAGE_TIME;
    }

    public double getMaxScore(){
        return age_weight + distance_weight + accepted_offers_weight + canceled_offers_weight + average_response_time_weight;
    }


    public int getMAX_AGE() {
        return MAX_AGE;
    }

    public void setMAX_AGE(int MAX_AGE) {
        this.MAX_AGE = MAX_AGE;
    }

    public double getMAX_DISTANCE() {
        return MAX_DISTANCE;
    }

    public void setMAX_DISTANCE(double MAX_DISTANCE) {
        this.MAX_DISTANCE = MAX_DISTANCE;
    }

    public int getMAX_ACCEPTED_OFFERS() {
        return MAX_ACCEPTED_OFFERS;
    }

    public void setMAX_ACCEPTED_OFFERS(int MAX_ACCEPTED_OFFERS) {
        this.MAX_ACCEPTED_OFFERS = MAX_ACCEPTED_OFFERS;
    }

    public int getMAX_CANCELED_OFFERS() {
        return MAX_CANCELED_OFFERS;
    }

    public void setMAX_CANCELED_OFFERS(int MAX_CANCELED_OFFERS) {
        this.MAX_CANCELED_OFFERS = MAX_CANCELED_OFFERS;
    }

    public int getMAX_AVERAGE_TIME() {
        return MAX_AVERAGE_TIME;
    }

    public void setMAX_AVERAGE_TIME(int MAX_AVERAGE_TIME) {
        this.MAX_AVERAGE_TIME = MAX_AVERAGE_TIME;
    }
}
