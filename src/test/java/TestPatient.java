import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPatient {
    Patient patient1;
    Patient patient2;
    @BeforeEach
    public void setParams(){
        JSONObject object1 = new JSONObject(
                "{\"id\":\"541d25c9-9500-4265-8967-240f44ecf723\"," +
                        "\"name\":\"Samir Pacocha\"," +
                        "\"location\":" +
                        "{\"latitude\":\"46.7110\"," +
                        "\"longitude\":\"-63.1150\"}," +
                        "\"age\":46," +
                        "\"acceptedOffers\":49," +
                        "\"canceledOffers\":92," +
                        "\"averageReplyTime\":2598}");

        this.patient1 = new Patient(object1);

        JSONObject object2 = new JSONObject(
                "{\"id\":\"541d25c9-9500-4265-8967-240f44ecf723\"," +
                        "\"name\":\"Samir Pacocha\"," +
                        "\"location\":" +
                        "{\"latitude\":\"46.7110\"," +
                        "\"longitude\":\"-63.1150\"}," +
                        "\"age\":46," +
                        "\"acceptedOffers\":49," +
                        "\"canceledOffers\":92," +
                        "\"averageReplyTime\":2598}");

        this.patient2 = new Patient(object2);
    }

    @Test
    public void testPatientJSONConstructor(){
        JSONObject object = new JSONObject(
                "{\"id\":\"541d25c9-9500-4265-8967-240f44ecf723\"," +
                        "\"name\":\"Samir Pacocha\"," +
                        "\"location\":" +
                                "{\"latitude\":\"46.7110\"," +
                                "\"longitude\":\"-63.1150\"}," +
                        "\"age\":46," +
                        "\"acceptedOffers\":49," +
                        "\"canceledOffers\":92," +
                        "\"averageReplyTime\":2598}");

        Patient patient = new Patient(object);
        assertEquals("541d25c9-9500-4265-8967-240f44ecf723", patient.getId());
        assertEquals("Samir Pacocha", patient.getName());
        assertEquals(46.7110, patient.getLocation().getLatitude());
        assertEquals(-63.1150, patient.getLocation().getLongitude());
        assertEquals(46, patient.getAge());
        assertEquals(49, patient.getAcceptedOffers());
        assertEquals(92, patient.getCanceledOffers());
        assertEquals(2598, patient.getAverageReplyTime());
    }

    @Test
    public void testPatientCompareEqual(){
        patient1.setScore(5);
        patient2.setScore(5);
        assertEquals(0, patient1.compareTo(patient2));
    }

    @Test
    public void testPatientCompareIsMore(){
        patient1.setScore(9);
        patient2.setScore(5);
        assertEquals(1, patient1.compareTo(patient2));
    }

    @Test
    public void testPatientCompareIsLess(){
        patient1.setScore(2);
        patient2.setScore(5);
        assertEquals(-1, patient1.compareTo(patient2));
    }
}
