package esempi.nested;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSTack {
	Stack s;
	
	@Before
	public void setup() {
		s = new Stack();
	}

	@Test
	public void testSingoloPop() {
//		Stack s = new Stack();
		
		s.push(3);
		
		int valore = s.pop();
		
		assertEquals( 3 , valore );
	}
	
	@Test
	public void testTriploPushSingoloPop() {
//		Stack s = new Stack();
		
		s.push(3);
		s.push(4);
		s.push(5);
		
		int valore = s.pop();
		
		assertEquals( 5 , valore );
	}


}
