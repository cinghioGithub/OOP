package ereditarieta;

import java.util.Iterator;

public class Fibonacci implements Iterable {

	@Override
	public Iterator iterator() {
		return new Iterator() {
			int[] numeri = {1,0};
			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Object next() {
				int prossimo;
				prossimo = numeri[0] + numeri[1];
				numeri[0] = numeri[1];
				numeri[1] = prossimo;
				return new Integer(prossimo);
			}
			
		};
	}

}
