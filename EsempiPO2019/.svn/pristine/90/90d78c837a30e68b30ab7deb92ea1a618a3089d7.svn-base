package ereditarieta;

import java.util.Iterator;




public class EsIterable {

	public static void main(String[] args) {
		
		Lettere esempio = new Lettere("Hello world!");
		
		stampaTutti(esempio);
	}
	
	public static void stampaTutti(Iterable contenitore) {
		
		Iterator it = contenitore.iterator();
		
		while(it.hasNext()) {
			Object elemento = it.next();
			System.out.println(elemento);
		}
		
		// EQUIVALE
		
		for(Object elemento : contenitore) {
			System.out.println(elemento);
		}
	}
	
	
	public static class Lettere implements Iterable {
		
		private char[] lettere;
		public Lettere(String s) {
			lettere = s.toCharArray();
		}
		public Iterator iterator() {
			//return new IteratoreDiLettere();
			
			// OPPURE, usando Anonymous Inner Class
			
			return new Iterator() {
				private int posizione = 0;
				@Override
				public boolean hasNext() {
					return posizione < lettere.length;
				}

				@Override
				public Object next() {
					return lettere[posizione++];
				}
			};
			
		}
		
		private class IteratoreDiLettere implements Iterator {
			private int posizione = 0;
			@Override
			public boolean hasNext() {
				return posizione < lettere.length;
			}

			@Override
			public Object next() {
				return lettere[posizione++];
				// OVVERO
				// return new Character(lettere[posizione++]);
			}
		
		}
	}
	

}
