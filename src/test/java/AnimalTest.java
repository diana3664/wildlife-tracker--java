import org.junit.*;
import static org.junit.Assert.*;



public class AnimalTest {

    @Test
            public void Animal_instantiateCorrectly_true(){
        Animal testAnimal = new Animal ("Zebra");
        assertEquals(true, testAnimal instanceof Animal);
    }

}
