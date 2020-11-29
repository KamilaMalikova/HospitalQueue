import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**Class QueueController is used to configure QueueGenerator
 * Methods used to configure are tested in TestQueueGenerator
 * The only functional method readFromFile is tested here
 **/

public class TestQueueController {
    QueueController controller;

    @BeforeEach
    public void config(){
        try {
            controller = new QueueController(0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testReadFromFile(){
        try {
            assertEquals("[{\"id\":\"541d25c9-9500-4265-8967-240f44ecf723\",\"name\":\"Samir Pacocha\",\"location\":{\"latitude\":\"46.7110\",\"longitude\":\"-63.1150\"},\"age\":46,\"acceptedOffers\":49,\"canceledOffers\":92,\"averageReplyTime\":2598}]", this.controller.readFromFile("test.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
