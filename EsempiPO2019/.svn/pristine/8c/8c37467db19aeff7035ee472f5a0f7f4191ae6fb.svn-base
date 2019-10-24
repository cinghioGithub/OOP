package stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EsStream {

	public static void main(String[] args) {
		String[] parole = data.Lyrics.ALLA_FIERA_DELL_EST.split("[ '.\n]+");
		
//		for(String p : parole) {
//			System.out.println(p);
//		}
		
//		Arrays.sort(parole);
//		for(int i=0; i<3; ++i) {
//			System.out.println(parole[i]);
//		}
		
		// O MEGLIO (senza modificare l'array originale)
		List<String> words = Arrays.asList(parole);
		Collections.sort(words);
		for(int i=0; i<3; ++i) {
			System.out.println(words.get(i));
		}
		
		//words.stream()..
		// Utilizzando gli Stream
		
		Stream.of(parole).  // crea lo stream
		sorted().			// lo ordina
		limit(3).			// prende solo i primi 3
		forEach(System.out::println);  // li stampa
		
		
		// SENZA I DUPLICATI
		
		TreeSet<String> senzaDup = new TreeSet<>();
		for(String p : parole) {
			senzaDup.add(p);
		}
		Iterator<String> it = senzaDup.iterator();
		for(int i=0; i<3 && it.hasNext(); ++i) {
			System.out.println(it.next());
		}
		
//		for(int i=0; i<3; ++i) {
//			System.out.println(getSet(senzaDup,i));
//		}	
		
		// Oppure con gli stream

		// Stream.of("Alla","fiera","dell","est")
		//Stream.of(parole).  // crea lo stream
		Arrays.stream(parole).
		//Stream.empty().
		sorted().			// lo ordina
		distinct().
		limit(3).			// prende solo i primi 3
		forEach(System.out::println);  // li stampa

		Stream.generate( () -> Math.random() ).limit(10).forEach(System.out::println);
		
		int precedente=0;
		int ultimo=0;
		
//		Stream.generate( () -> { 
//			int n = precedente+ultimo;
//			if(n==0) n=1;
//			precedente = ultimo;
//			ultimo = n;
//			return n;
//		} )
//		Stream.generate( fibonacci() )
//		.limit(10)
//		//.forEach(System.out::println);
//		// EQUIVALE
//		.forEach( e -> System.out.println(e) );
		
		
		//Stream.generate( EsStream::fib )
		// EQUIVALE A
		Stream.generate( fibonacci2() )
		.limit(10)
		//.forEach(System.out::println);
		// EQUIVALE
		.forEach( e -> System.out.println(e) );

		consumaPrimiN(10, fibonacci2(), System.out::println);
		
		
		
		Stream.of(parole)
		.filter( p -> p.length()>3 )
		.distinct()
		.forEach(System.out::println);
	
		
		Stream.of(parole)
		.distinct()
		.sorted( (p1,p2) -> p1.length() - p2.length())
		.forEach(System.out::println);
		
		
		Stream.of(parole).map( p -> p.length())
		.distinct()
		.sorted()
		.forEach(System.out::println);
		
	}
	
	private static String getSet(Set<String> s, int i) {
		Iterator<String> it = s.iterator();
		String element = null;
		for(int j=0; j<3 && it.hasNext(); ++j) {
			element = it.next();
		}
		return element;
	}
	
	private static Supplier<Integer> fibonacci(){
		int[] prec = {0,1};
		return () -> { 
			int n = prec[0] + prec[1];
			prec[1] = prec[0];
			prec[0] = n;
			return n;
		} ;
	}
	
	private static Supplier<Integer> fibonacci2(){
		
		return new Supplier<Integer>() {
			int precedente = 1;
			int ultimo = 0;
			public Integer get() {
				int n = precedente+this.ultimo;
				precedente = ultimo;
				ultimo = n;
				return n;
			}
		};
	}
	
	
	
	static int precedente=1;
	static int ultimo=0;
	private static int fib(){
		int n = precedente+ultimo;
		precedente = ultimo;
		ultimo = n;
		return n;
	}
	
	private static void stampaPrimiDieci(Supplier<Integer> generatore) {
		for(int i=0; i<10; ++i) {
			int prossimo = generatore.get();
			System.out.println(prossimo);
		}
	}
	
	
	private static void consumaPrimiDieci(Supplier<Integer> generatore,
										  Consumer<Integer> consumatore) {
		for(int i=0; i<10; ++i) {
			int prossimo = generatore.get();
			consumatore.accept(prossimo);
		}
	}

	/**
	 * equivale a:
	 * 
	 * Stream.generate(generatore).limit(n).forEach(consumatore);
	 * 
	 * @param n
	 * @param generatore
	 * @param consumatore
	 */
	private static void consumaPrimiN(int n, Supplier<Integer> generatore,
			  Consumer<Integer> consumatore) {
			for(int i=0; i<n; ++i) {
				int prossimo = generatore.get();
				consumatore.accept(prossimo);
			}
	}
	


}
