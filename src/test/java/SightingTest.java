import org.junit.*;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //instance of class sighting test
    @Test
    public void Sighting_instantiateCorrectly_true(){
        Animal testAnimal = new Animal("Fox");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        assertEquals(true, testSighting  instanceof Sighting);
    }

      //get name
    @Test
    public void Sighting_returnsNameO_RangerName(){
        Animal testAnimal = new Animal("Fox");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        assertEquals("James Wait", testSighting.getRanger_name());
    }

    //return true if loacation and decription are the same
    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        Animal testAnimal = new Animal("Fox");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        Sighting anotherSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        assertTrue(testSighting.equals(anotherSighting));
    }

    //save object in database
    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Animal testAnimal = new Animal("Fox");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        testSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
    }

    //get all animals in site
    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Animal testAnimal = new Animal("Fox");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        testSighting.save();
        Animal secondTestAnimal = new Animal("Kangaru");
        secondTestAnimal.save();
        Sighting secondSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }


    //find by id
    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Animal testAnimal = new Animal("Deer");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "Zone B", "James Wait");
        testSighting.save();
        Animal secondTestAnimal = new Animal("Badger");
        secondTestAnimal.save();
        Sighting secondTestSighting = new Sighting(testAnimal.getId(), "Zone c", "James Wait");
        secondTestSighting.save();
        assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
    }
}
