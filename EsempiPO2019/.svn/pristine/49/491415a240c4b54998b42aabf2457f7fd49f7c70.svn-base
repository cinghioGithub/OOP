package ereditarieta;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

public class EsSort {
	
	static class Pippo {
		int i;
	}

	public static void main(String[] args) {
		
		String[] as = { "z","d","k","r","a" };
		
		sort(as);
		
		for(String s : as) {
			System.out.print(s+" ");
		}
		
//		Pippo[] arp = { new Pippo(), new Pippo(), new Pippo() };
//		//sort(arp);
//		sortAry(arp);
//		Arrays.sort(arp);
		
		String frase = "Many classes or algorithm has a stable"+
						" core and several behavioural variations " + 
						"The operation performed may vary";
		String[] parole = frase.split(" ");
		
		Arrays.sort(parole);
		
		for(String p : parole) System.out.println(p);
		
		Arrays.sort(parole, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				String s1 = (String)o1;
				String s2 = (String)o2;
				return s1.length() - s2.length();
			}
		});
		
		for(String p : parole) System.out.println(p);

		Arrays.sort(parole, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				String s1 = (String)o1;
				String s2 = (String)o2;
				return s2.length() - s1.length();
			}
		});
		
		// OPPURE usando la lamdba function
		Arrays.sort(parole, (Object o1, Object o2) -> {
			String s1 = (String)o1;
			String s2 = (String)o2;
			return s2.length() - s1.length();
		});

		for(String p : parole) System.out.println(p);
		
		Comparator.
			naturalOrder().  // static method of Comparator
			reversed();		 // default method of Comparator

		
		// Con i generics
		
		Arrays.sort(parole, (String s1, String s2) -> {
			return s2.length() - s1.length();
		});

		Arrays.sort(parole, ( s1,  s2) -> {
			return s2.length() - s1.length();
		});

		Arrays.sort(parole, ( s1,  s2) -> s2.length() - s1.length() );

		Arrays.sort(parole, confrontaInBaseA( s -> s.length() ));
		
		Arrays.sort(parole, confrontaInBaseA( String::length )); // method reference
	}
	
	
	static <T> Comparator<T> confrontaInBaseA(ToIntFunction<T> estrattore){
		return (a,b) -> estrattore.applyAsInt(a) - estrattore.applyAsInt(b);
	}
	
	interface Comparatore<T> {
		int compare(T a, T b);
	}
	
	static Comparator naturalOrder() {
		return (a,b) -> ((Comparable)a).compareTo(b);
	}
	
	static Comparator reversed(Comparator original) {
		// TODO: implement it
//		return new Comparator() {
//			public int compare(Object a, Object b) {
//				return - original.compare(a, b);
//			}
//		}
//		;
		// OPPURE (con lambda)
		return ( a,  b) -> - original.compare(a, b);
		
		// EQUIVALE (circa)
		
//		Comparator cmp = new Comparator() {
//			public int compare(Object a, Object b) {
//				return - original.compare(a, b);
//			}
//			public void setOriginal(Comparator o) {
//				this.original = o;
//			}
//			Comparator original;
//		}
//		;
//		cmp.setOriginal(original);
//		return cmp;
	}

	public static void sort(Comparable v[]) {
		for (int i = 1; i < v.length; ++i)
			for (int j = 0; j < v.length - i; ++j) {
				if ( v[j].compareTo(v[j+1]) > 0) {
					Comparable o = v[j];
					v[j] = v[j + 1];
					v[j + 1] = o;
				}
			}
	}

	public static void sortAry(Object v[]) {
		for (int i = 1; i < v.length; ++i)
			for (int j = 0; j < v.length - i; ++j) {
				Comparable elemento = (Comparable) v[j];
				if ( elemento.compareTo(v[j+1]) > 0) {
					Object o = v[j];
					v[j] = v[j + 1];
					v[j + 1] = o;
				}
			}
	}

	public static void sortAry(Object v[], Comparator cmp) {
		for (int i = 1; i < v.length; ++i)
			for (int j = 0; j < v.length - i; ++j) {
				if ( cmp.compare( v[j] , v[j+1]) > 0) {
					Object o = v[j];
					v[j] = v[j + 1];
					v[j + 1] = o;
				}
			}
	}
}
