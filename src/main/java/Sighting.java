import org.sql2o.*;
import java.util.List;


public class Sighting {
    private int animal_id;
    private String location;
    private String ranger_name;
    private int id;

    public Sighting(int animal_id, String location, String ranger_name) {
        this.animal_id = animal_id;
        this.location = location;
        this.ranger_name = ranger_name;
        this.id = id;
    }

    public String getName() {
        return ranger_name;
    }
}
