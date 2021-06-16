import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndengeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();


    //instance off
    @Test
    public void endangeredAnimal_instantiatesCorrectly_true() {
        Endengered testEndangeredAnimal = new Endengered("Fox", "Healthy", "Young");
        assertEquals(true, testEndangeredAnimal instanceof Endengered);
    }

//returns healthy
    @Test
       public void getHealth_returnsHealthy_true() {
        Endengered testEndangeredAnimal = new Endengered("Fox", "Healthy", "Young");
        assertEquals("Healthy", testEndangeredAnimal.getHealth());
     }

     //saves object to database ||assign id
     @Test
     public void save_assignsIdAndSavesObjectToDatabase() {
         Endengered testEndangeredAnimal = new Endengered("Fox", "Healthy", "Young");
         testEndangeredAnimal.save();
         Endengered savedEndangeredAnimal = Endengered.all().get(0);
         assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
     }



   }
