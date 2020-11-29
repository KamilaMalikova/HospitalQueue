import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLocation {
    @Test
    public void testDefaultConstructor(){
        Location location = new Location();
        assertEquals(0.0, location.getLatitude());
        assertEquals(0.0, location.getLongitude());
    }

    @Test
    public void testConstructorWithDoubleParams(){
        Location location = new Location(2, 3);
        assertEquals(2, location.getLongitude());
        assertEquals(3, location.getLatitude());
    }

    @Test
    public void testConstructorWithJSONParams(){
        JSONObject object = new JSONObject("{\"latitude\":\"10.5\",\"longitude\":\"5.3\"}");
        Location location = new Location(object);
        assertEquals(10.5, location.getLatitude());
        assertEquals(5.3, location.getLongitude());
    }
    @Test
    public void testConstructorWithIncorrectJSON(){
        JSONObject object = new JSONObject("{\"latit\":\"10.5\",\"longit\":\"5.3\"}");
        assertThrows(JSONException.class, ()-> new Location(object), "JSONObject[\"latitude\"] not found");
    }

    @Test
    public void testConstructorWithIncorrectJSONLongitude(){
        JSONObject object = new JSONObject("{\"latitude\":\"10.5\",\"longit\":\"5.3\"}");
        assertThrows(JSONException.class, ()-> new Location(object), "JSONObject[\"longitude\"] not found");
    }

    @Test
    public void testConstructorWithJSONIncorrectValue(){
        JSONObject object = new JSONObject("{\"latitude\":\"Hello\",\"longit\":\"5.3\"}");
        assertThrows(JSONException.class, ()-> new Location(object), "JSONObject[\"latitude\"] is not a double");
    }
    @Test
    public void testDistanceTo(){
        Location location = new Location(3, 4);
        assertEquals(5, location.distanceTo(new Location(0, 0)));
    }
}
