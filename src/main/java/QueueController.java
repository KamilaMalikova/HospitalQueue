import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QueueController {
    QueueGenerator generator;
    Location location;

    public QueueController(double longitude, double latitude) throws IOException {
        this.generator = new QueueGenerator();
        this.location = new Location(longitude, latitude);
        JSONArray jsonArray = new JSONArray(readFromFile("patients.json"));
        List<Patient> patients = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Patient tempPatient = new Patient(jsonArray.getJSONObject(i));
            //check max_distance
            if (tempPatient.getLocation().distanceTo(this.location) > this.generator.getMAX_DISTANCE()) this.generator.setMAX_DISTANCE(tempPatient.getLocation().distanceTo(this.location));

            //check max_accepted_offers
            if (tempPatient.getAcceptedOffers() > this.generator.getMAX_ACCEPTED_OFFERS()) this.generator.setMAX_ACCEPTED_OFFERS(tempPatient.getAcceptedOffers());

            //check max_canceled_offers
            if (tempPatient.getCanceledOffers() > this.generator.getMAX_CANCELED_OFFERS()) this.generator.setMAX_CANCELED_OFFERS(tempPatient.getCanceledOffers());

            // check average reply time
            if (tempPatient.getAverageReplyTime() > this.generator.getMAX_AVERAGE_TIME()) this.generator.setMAX_AVERAGE_TIME(tempPatient.getAverageReplyTime());

            patients.add(tempPatient);
        }
        this.generator.setPatients(patients);
    }

    public String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    public void getQueue(){

        this.generator.makeQueue(this.location);
    }
}
