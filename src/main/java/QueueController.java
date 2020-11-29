import org.json.JSONArray;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueueController {
    QueueGenerator generator;
    Location location;

    /**
     * Initializes constructor used to configure QueueGenerator
     * Identifies and sets max values
     * Reads list of patients from json file and puts into QueueGenerator patients map
     *
     * This class is used to configure a QueueGenerator and can easily be replaced by another
     **/
    public QueueController(double longitude, double latitude) throws IOException {
        this.generator = new QueueGenerator();
        this.location = new Location(longitude, latitude);
        JSONArray jsonArray = new JSONArray(readFromFile("patients.json"));
        Map<String, Patient> patientMap = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Patient tempPatient = new Patient(jsonArray.getJSONObject(i));
            //check max_distance
            if (tempPatient.getLocation().distanceTo(this.location) > this.generator.getMaxDistance()) this.generator.setMaxDistance(tempPatient.getLocation().distanceTo(this.location));

            //check max_accepted_offers
            if (tempPatient.getAcceptedOffers() > this.generator.getMaxAcceptedOffers()) this.generator.setMaxAcceptedOffers(tempPatient.getAcceptedOffers());

            //check max_canceled_offers
            if (tempPatient.getCanceledOffers() > this.generator.getMaxCanceledOffers()) this.generator.setMaxCanceledOffers(tempPatient.getCanceledOffers());

            // check average reply time
            if (tempPatient.getAverageReplyTime() > this.generator.getMaxAverageTime()) this.generator.setMaxAverageTime(tempPatient.getAverageReplyTime());

            patientMap.put(tempPatient.getId(), tempPatient);
        }
        this.generator.setPatients(patientMap);

    }
    /**
     * Reads data from file and returns it as a string
     **/
    public String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }
    /***/
    public List<Patient> getQueue(){
        return this.generator.makeQueue(this.location);
    }
}
