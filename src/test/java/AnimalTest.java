import org.junit.*;
import static org.junit.Assert.*;



public class AnimalTest {


    //rule created after creating a DATAbaseRule
    public DatabaseRule database = new DatabaseRule();


    //instance of class Animal test
    @Test
            public void Animal_instantiateCorrectly_true(){
        Animal testAnimal = new Animal ("Zebra");
        assertEquals(true, testAnimal instanceof Animal);
    }

    //returns name
    @Test
    public void Animal_returnsNameCorrectly_true(){
        Animal testAnimal = new Animal ("Zebra");
        assertEquals("Zebra", testAnimal.getName());
    }

    //if they are the same
    @Test
    public void Animal_areEqual_true(){
        Animal testAnimal = new Animal ("Zebra");
        Animal secondAnimal = new Animal ("Zebra");

        assertTrue(testAnimal.equals(secondAnimal));
    }

    //save animal in database save()
    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animal testAnimal = new Animal ("Zebra");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    // our save() returns all() when called apon
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = new Animal ("Zebra");
        testAnimal.save();
        Animal secondAnimal = new Animal ("Lion");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

}
