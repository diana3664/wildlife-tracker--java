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

    public int getId() {
        return id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger_name() {
        return ranger_name;
    }

    //return true if loacation and decription are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;

        Sighting sighting = (Sighting) o;

        if (animal_id != sighting.animal_id) return false;
        if (id != sighting.id) return false;
        if (!location.equals(sighting.location)) return false;
        return ranger_name.equals(sighting.ranger_name);
    }

    @Override
    public int hashCode() {
        int result = animal_id;
        result = 31 * result + location.hashCode();
        result = 31 * result + ranger_name.hashCode();
        result = 31 * result + id;
        return result;
    }

    //save object in database
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_id, location, ranger_name) VALUES (:animal_id, :location, :ranger_name);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location", this.location)
                    .addParameter("ranger_name", this.ranger_name)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }


    //get all from animals inthe site
    public static List<Sighting> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);
        }
    }

    //find by id
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
}