import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import it.polito.oop.tables.*;

public final class TestApp {

    @Test
    public void test()  {
        Restaurant resto = new Restaurant();
//R1                      
                   //Table: 1  2  3  4  5
        resto.defineTables( 4, 4, 2, 2, 6); //seats
        
        assertEquals(18,resto.getSeats());
        assertEquals(6,resto.tableSize(5));
        assertEquals(2,resto.tableSize(4));
        
        try {
            resto.tableSize(8);
            fail("Expected exception");
        }catch(IllegalArgumentException e) {
            // OK
        }
        
        resto.setOpeningTime("19:30");
        resto.setClosingTime("23:30");
        
// R2
        
        int p1 = resto.newParty("Genny", 4, "3334445566");
        int p2 = resto.newParty("Remo", 2, "3337778899");
        int p3 = resto.newParty("Ugo", 5, "3331112233");
        
        assertTrue(p1!=p2);
        assertEquals(3,resto.countParties());
        
// R3
        
        resto.takeTable(1, p1, "19:34");
        resto.takeTable(4, p2, "20:10");
        resto.takeTable(5, p3, "19:40");
        
        assertEquals("20:34",resto.estimateAvailable(1));
        
// R4
        List<TableOption> options = resto.estimateWaiting(6);
      
        assertNotNull(options);
        assertEquals(1,options.size()); // only one table fits 6 people
        assertEquals(5,options.get(0).getTable());      // table 5 will be ... 
        assertEquals("20:45",options.get(0).getTime()); // ... available at 20:45
        assertEquals(1.00,options.get(0).getOccupation(),0.01); // with a 1.0 occupation

        int p4 = resto.newParty("Ivo", 6, "3336665544");
        resto.bookTable(5, p4, "20:45");
        assertEquals("21:55",resto.estimateAvailable(5));
        
// R5
        resto.leaveTable(1, "20:25");
        resto.leaveTable(4, "21:15");
        
        assertEquals(6,resto.countServedCustomers());
        assertEquals(2,resto.countServedParties());
        assertEquals(-15,resto.estimationError(4));
    }
}
