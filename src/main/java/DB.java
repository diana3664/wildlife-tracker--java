import org.sql2o.*;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "123");
//public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-3-89-0-52.compute-1.amazonaws.com:5432/d4qoai36ep2dl6", "sladnpscpppdno", "07bc934aa8de75a3d532bce7a1e4e8603df0b8c20d84c43943a3b7fd8e6a1028");


}