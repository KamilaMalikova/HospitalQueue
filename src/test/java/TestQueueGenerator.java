import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestQueueGenerator {
    QueueGenerator queueGenerator;
    Map<String, Patient> patients;
    Patient patient1;
    Patient patient11;
    @BeforeEach
    public void config(){
        patient1 = new Patient(new JSONObject(
                "{\"id\":\"541d25c9-9500-4265-8967-240f44ecf723\"," +
                "\"name\":\"Samir Pacocha\"," +
                "\"location\":{\"latitude\":\"0\",\"longitude\":\"0\"}," +
                "\"age\":100," +
                "\"acceptedOffers\":100," +
                "\"canceledOffers\":0," +
                "\"averageReplyTime\":0" +
                "}"));
        Patient patient2 = new Patient(new JSONObject(
                "{\"id\":\"41fd45bc-b166-444a-a69e-9d527b4aee48\"," +
                "\"name\":\"Bernard Mosciski\"," +
                "\"location\":{\"latitude\":\"-81.0341\",\"longitude\":\"144.9963\"}," +
                "\"age\":21," +
                "\"acceptedOffers\":95," +
                "\"canceledOffers\":96," +
                "\"averageReplyTime\":1908" +
                "}"));
        Patient patient3 = new Patient(new JSONObject(
                "{\"id\":\"90592106-a0d9-4329-8159-af7ce4ba45ad\"," +
                        "\"name\":\"Theo Effertz\"," +
                        "\"location\":{\"latitude\":\"-35.5336\",\"longitude\":\"-25.2795\"}," +
                        "\"age\":67," +
                        "\"acceptedOffers\":69," +
                        "\"canceledOffers\":24," +
                        "\"averageReplyTime\":3452}"));

        Patient patient4 = new Patient(new JSONObject(
                "{\"id\":\"b483afb8-2ed7-4fd2-9cd6-c1fd7071f19f\"," +
                        "\"name\":\"Mathew Halvorson\"," +
                        "\"location\":{\"latitude\":\"-75.6334\",\"longitude\":\"-165.8910\"}," +
                        "\"age\":26," +
                        "\"acceptedOffers\":80," +
                        "\"canceledOffers\":22," +
                        "\"averageReplyTime\":2315}"));

        Patient patient5 = new Patient(new JSONObject(
                "{\"id\":\"1ba1b882-6516-4e54-a1ef-453bb3137d02\"," +
                        "\"name\":\"Mossie Larkin\"," +
                        "\"location\":{\"latitude\":\"77.5235\",\"longitude\":\"175.3549\"}," +
                        "\"age\":86," +
                        "\"acceptedOffers\":62," +
                        "\"canceledOffers\":95," +
                        "\"averageReplyTime\":3143}"));

        Patient patient6 = new Patient(new JSONObject(
                "{\"id\":\"ea97c92f-8605-4288-88b6-47da0984240d\"," +
                        "\"name\":\"Shad Mills\"," +
                        "\"location\":{\"latitude\":\"-70.3263\",\"longitude\":\"-135.2299\"}," +
                        "\"age\":24," +
                        "\"acceptedOffers\":80," +
                        "\"canceledOffers\":83," +
                        "\"averageReplyTime\":3579}"));
        Patient patient7 = new Patient(new JSONObject(
                "{\"id\":\"0f8b58a4-e9dd-456d-a8be-b92d67720661\"," +
                        "\"name\":\"Harrison Cummings\"," +
                        "\"location\":{\"latitude\":\"-69.7612\",\"longitude\":\"-37.3723\"}," +
                        "\"age\":38," +
                        "\"acceptedOffers\":58," +
                        "\"canceledOffers\":9," +
                        "\"averageReplyTime\":1329}"));

        Patient patient8 = new Patient(new JSONObject(
                "{\"id\":\"a21a7ea6-4c4e-4d00-b492-e43e2c71c8f7\"," +
                        "\"name\":\"Demond Rosenbaum\"," +
                        "\"location\":{\"latitude\":\"24.7416\",\"longitude\":\"49.3173\"}," +
                        "\"age\":23," +
                        "\"acceptedOffers\":19," +
                        "\"canceledOffers\":28," +
                        "\"averageReplyTime\":1427}"));

        Patient patient9 = new Patient(new JSONObject(
                "{\"id\":\"c4dc7b5c-0899-4500-b158-19f535bda9d6\"," +
                        "\"name\":\"Chester Yost II\"," +
                        "\"location\":{\"latitude\":\"-18.8286\",\"longitude\":\"112.3571\"}," +
                        "\"age\":82," +
                        "\"acceptedOffers\":75," +
                        "\"canceledOffers\":58," +
                        "\"averageReplyTime\":1929}"));

        Patient patient10 = new Patient(new JSONObject(
                "{\"id\":\"00ad0d71-193d-458c-8a61-ba1b233cd6df\"," +
                        "\"name\":\"Dina Orn\"," +
                        "\"location\":{\"latitude\":\"-2.7015\",\"longitude\":\"-73.7385\"}," +
                        "\"age\":50," +
                        "\"acceptedOffers\":8," +
                        "\"canceledOffers\":3," +
                        "\"averageReplyTime\":1847}"));
        patient11 = new Patient(new JSONObject(
                "{\"id\":\"a0f6e833-b043-48ee-828c-8ceb06ba159c\"," +
                        "\"name\":\"Leo Wisozk\"," +
                        "\"location\":{\"latitude\":\"400\",\"longitude\":\"300\"}," +
                        "\"age\":0," +
                        "\"acceptedOffers\":0," +
                        "\"canceledOffers\":100," +
                        "\"averageReplyTime\":4000}"));

        this.patients = new HashMap<>();
        this.patients.put(patient1.getId(), patient1);
        this.patients.put(patient2.getId(), patient2);
        this.patients.put(patient3.getId(), patient3);
        this.patients.put(patient4.getId(), patient4);
        this.patients.put(patient5.getId(), patient5);
        this.patients.put(patient6.getId(), patient6);
        this.patients.put(patient7.getId(), patient7);
        this.patients.put(patient8.getId(), patient8);
        this.patients.put(patient9.getId(), patient9);
        this.patients.put(patient10.getId(), patient10);
        this.patients.put(patient11.getId(), patient11);

        queueGenerator = new QueueGenerator(this.patients);
    }
    /**Test max value setters*/
    @Test
    public void testMaxValueSetters(){
        this.queueGenerator.setMaxAge(50);
        this.queueGenerator.setMaxDistance(100);
        this.queueGenerator.setMaxAcceptedOffers(100);
        this.queueGenerator.setMaxCanceledOffers(100);
        this.queueGenerator.setMaxAverageTime(1000);
        assertEquals(50, this.queueGenerator.getMaxAge());
        assertEquals(100, this.queueGenerator.getMaxDistance());
        assertEquals(100, this.queueGenerator.getMaxAcceptedOffers());
        assertEquals(100, this.queueGenerator.getMaxCanceledOffers());
        assertEquals(1000, this.queueGenerator.getMaxAverageTime());
    }

    @Test
    public void testMaxValueSetToZero(){
        assertThrows(IllegalArgumentException.class, ()-> this.queueGenerator.setMaxAge(0));
        assertThrows(IllegalArgumentException.class, ()-> this.queueGenerator.setMaxDistance(0));
        assertThrows(IllegalArgumentException.class, ()-> this.queueGenerator.setMaxAcceptedOffers(0));
        assertThrows(IllegalArgumentException.class, ()-> this.queueGenerator.setMaxCanceledOffers(0));
        assertThrows(IllegalArgumentException.class, ()-> this.queueGenerator.setMaxAverageTime(0));
    }

    @Test
    public void testGetAgeScore(){
        assertEquals(0.5, this.queueGenerator.getAgeScore(50));
    }

    @Test
    public void testGetAgeScoreWithDifferentMaxAge(){
        this.queueGenerator.setMaxAge(50);
        assertEquals(1, this.queueGenerator.getAgeScore(50));
    }
    @Test
    public void testGetDistanceScore(){
        assertEquals(0.5, this.queueGenerator.getDistanceScore(250));
    }
    @Test
    public void testGetDistanceScoreWithDifferentMaxScore(){
        this.queueGenerator.setMaxDistance(50);
        assertEquals(0, this.queueGenerator.getDistanceScore(50));
    }
    @Test
    public void testGetAcceptedOffersScore(){
        assertEquals(1.5, this.queueGenerator.getAcceptedOffersScore(50));
    }
    @Test
    public void testGetAcceptedOffersScoreWithDifferentMAxScore(){
        this.queueGenerator.setMaxAcceptedOffers(50);
        assertEquals(3, this.queueGenerator.getAcceptedOffersScore(50));
    }
    @Test
    public void testGetCanceledOffersScore(){
        assertEquals(1.5, this.queueGenerator.getCanceledOffersScore(50));
    }
    @Test
    public void testGetCanceledOffersScoreWithDifferentMaxScore(){
        this.queueGenerator.setMaxCanceledOffers(50);
        assertEquals(0, this.queueGenerator.getCanceledOffersScore(50));
    }
    @Test
    public void testGetAverageResponseScore(){
        assertEquals(1, this.queueGenerator.getAverageResponseTimeScore(2000));
    }
    @Test
    public void testGetAverageResponseScoreWithDifferentMaxScore(){
        this.queueGenerator.setMaxAverageTime(100);
        assertEquals(0, this.queueGenerator.getAverageResponseTimeScore(100));
    }
    @Test
    public void testGetMaxScore(){
        assertEquals(10, this.queueGenerator.getMaxScore());
    }
    @Test
    public void testGetScore(){
        assertEquals(10, this.queueGenerator.getScore(new Location(), this.patient1));
        this.queueGenerator.setMaxDistance(500);
        assertEquals(0, this.queueGenerator.getScore(new Location(), this.patient11));
    }

    @Test
    public void testMakeQueue(){
        Location location = new Location();
        List<Patient> patientList = this.queueGenerator.makeQueue(location);
        assertEquals(patient1, patientList.get(0));
        assertFalse(patientList.contains(patient11));
    }
}
