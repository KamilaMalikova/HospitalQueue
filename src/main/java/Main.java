import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            QueueController controller = new QueueController(0, 0);
            controller.getQueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
