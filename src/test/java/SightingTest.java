import org.junit.*;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //instance of class sighting test
    @Test
    public void Animal_instantiateCorrectly_true(){
        Animal testAnimal = new Animal("Deer");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        assertEquals(true, testSighting  instanceof Sighting);
    }
}
