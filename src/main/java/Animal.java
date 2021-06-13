import org.sql2o.Connection;

public class Animal {
    private String name;
    private int id;

    public Animal (String name){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
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
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .executeUpdate();
        }
    }
}
