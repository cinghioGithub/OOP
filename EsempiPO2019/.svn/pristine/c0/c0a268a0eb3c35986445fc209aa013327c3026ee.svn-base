package ereditarieta;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TestFibonacci {

	@Test
	public void testPrimoNumero() {
		
		Fibonacci f = new Fibonacci();
		
		Iterator it = f.iterator();
		
		assertTrue(it.hasNext());
		
		Integer primo = (Integer) it.next();
		
		assertEquals(1, primo.intValue());
		
		Integer secondo = (Integer) it.next();
		
		assertEquals(1, secondo.intValue());
		
	}
	
	@Test
	public void testPrimi4Numeri() {
		
		Fibonacci f = new Fibonacci();
		
		Iterator it = f.iterator();
		
		int[] attesi = {1,1,2,3};
		
		for(int i=0; i<attesi.length; ++i) {
			Integer numero = (Integer) it.next();
			assertEquals(attesi[i],numero.intValue());
		}
		
	}

}
