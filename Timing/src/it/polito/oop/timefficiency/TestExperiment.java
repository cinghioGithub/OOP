package it.polito.oop.timefficiency;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

public class TestExperiment {

	@Test
	public void test() {
		
		Consumer<Integer> strConcat = n -> {
			String res="";
			for(int i=0; i<n; i++) {
				res+=i;
			}
		};
		
		Consumer<Integer> strBuffer = n -> {
			StringBuffer b = new StringBuffer();
			for(int i=0; i<n; i++) {
				b.append(i);
			}
			String res=b.toString();
		};
		
		Experiment<Integer> exp = new Experiment<>();
		
		exp.addAlgorithm("String.Concat", strConcat);
		exp.addAlgorithm("StringBuffer", strBuffer);
		
		exp.setDataGenerator( () -> 1000 );
		
		double ts = exp.perform("String.Concat");
		double tsc = exp.perform("StringBuffer");
		
		System.out.println(ts);
		System.out.println(tsc);
		
		assertTrue("OK", ts>tsc);
		
		int n = exp.run();
		
		assertEquals(30,n);
		
		Map<String, List<Double>> misure = exp.getMeasures();
		
		assertNotNull(misure);
		assertEquals(2, misure.keySet().size());
		assertTrue(misure.containsKey("StringBuffer"));
		
		List<Double> listasb = misure.get("StringBuffer");
		
		assertEquals(30, listasb.size());
		
	}

}
