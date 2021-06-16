import org.sql2o.*;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class Animal implements EndengeredDao{
    private String name;
    private int id;
    private Timestamp timeSeen;

    public Animal (String name){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    //getid
    public int getId() {
        return id;
    }

    //get time


    public Timestamp getTimeSeen() {
        return timeSeen;
    }

    //are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        return getName().equals(animal.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + id;
        return result;
    }

    //saving to databaseRule
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,timeSeen) VALUES (:name, now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }catch ( Exception e){}
    }

    // our save() returns all() when called apon
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    //find animal based on id find()
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }
//find animal sighting object within object
    public List<Sighting> getSightings() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }
}
