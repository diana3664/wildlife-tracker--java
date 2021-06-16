//import org.junit.rules.ExternalResource;
//import org.sql2o.*;
//
//public class DatabaseRule extends ExternalResource {
//
//    @Override
//    protected void before() {
////        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "123");
//        DB.sql2o =  new Sql2o("jdbc:postgresql://ec2-3-89-0-52.compute-1.amazonaws.com:5432/d4qoai36ep2dl6", "sladnpscpppdno", "07bc934aa8de75a3d532bce7a1e4e8603df0b8c20d84c43943a3b7fd8e6a1028");
//
//    }
//
//    @Override
//    protected void after() {
//        try(Connection con = DB.sql2o.open()) {
//            String deleteAnimalsQuery = "DELETE FROM animals *;";
//            String deleteEndangeredAnimalsQuery = "DELETE FROM endangered_animals *;";
//            String deleteSightingsQuery = "DELETE FROM sightings *;";
//            con.createQuery(deleteAnimalsQuery).executeUpdate();
//            con.createQuery(deleteEndangeredAnimalsQuery).executeUpdate();
//            con.createQuery(deleteSightingsQuery).executeUpdate();
//        }
//    }
//
//
//}
