import org.sql2o.*;
import java.util.List;


public class Endengered {
    public String name;
    public int id;
    private String health;
    private String age;

    //constants
    public static final String HEALTH_1 = "Healthy";
    public static final String HEALTH_2 = "Okay";
    public static final String HEALTH_3 = "Ill";


    public Endengered(String name, String health, String age) {
        this.name = name;
        this.id = id;
        this.health = health;
        this.age = age;
        this.health = HEALTH_1;
        this.health = HEALTH_2;
        this.health = HEALTH_3;
    }

    public String getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    //if they are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endengered)) return false;

        Endengered that = (Endengered) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!getHealth().equals(that.getHealth())) return false;
        return age.equals(that.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        result = 31 * result + getHealth().hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }

    //save
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }
//list of all
public static List<Endengered> all() {
    try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM endangered_animals;";
        return con.createQuery(sql)
                .executeAndFetch(Endengered.class);
    }
}

//find with id
public static Endengered find(int id) {
    try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM endangered_animals WHERE id=:id;";
        Endengered endangeredanimal = con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Endengered.class);
        return endangeredanimal;
    }
}

//updating the health
public void updateHealth(String health) {
    try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE endangered_animals SET health=:health WHERE id=:id;";
        con.createQuery(sql)
                .addParameter("id", id)
                .addParameter("health", health)
                .executeUpdate();
    }
}

//update health
    public void updateAge(String age) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE endangered_animals SET age=:age WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    //updateSighting

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
