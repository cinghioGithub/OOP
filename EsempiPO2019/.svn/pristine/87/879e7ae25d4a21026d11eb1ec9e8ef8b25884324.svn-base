package stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

public class EsStream2 {
	public static void main(String[] args) {
		String[] parole = data.Lyrics.ALL_ALONG_THE_WATCHTOWER.split("[ '.\n\",]+");
		
		
		long tBegin = System.nanoTime();
		Runnable maxInteger = () -> {
			Stream.of(parole).
			map(String::length).  // boxing int --> Integer
			reduce(0,Math::max);  // 2 X unboxing Integer-->int 
								//    + boxing int-->Integer
		};
		maxInteger.run();
		long tEnd = System.nanoTime();
		System.out.println("Integer: " + (tEnd - tBegin));
		
		tBegin = System.nanoTime();
		Runnable maxInt = () -> {
			Stream.of(parole).
			mapToInt(String::length).
			reduce(0,Math::max);
		};
		maxInt.run();
		tEnd = System.nanoTime();
		System.out.println("int:     " + (tEnd - tBegin));
		
		System.out.println("Integer: " + quantoTempo(maxInteger));
		System.out.println("    int: " + quantoTempo(maxInt));
		
		Runnable maxIntegerPar = () -> {
			Stream.of(parole).
			parallel().
			map(String::length).  // boxing int --> Integer
			reduce(0,Math::max);  // 2 X unboxing Integer-->int 
								//    + boxing int-->Integer
		};
		System.out.println("IntegerP:" + quantoTempo(maxIntegerPar));

		
		
		Runnable stream = () -> {
			List<String> paroleUniche = Stream.of(parole).
			filter( p -> p.length()>2).
			map( String::toLowerCase ).
			distinct().
			sorted().  // implicitamente: NaturalOrder
			collect( toList() );
			System.out.println(paroleUniche);
		};
		
		
		Runnable collections = () -> {
			TreeSet<String> tmp = new TreeSet<>();
			ArrayList<String> res = new ArrayList<>();
			for( String p : parole) {
				if (p.length()>2 ) {
					tmp.add( p.toLowerCase() );
				}
			}
			res.addAll(tmp);
			System.out.println(res);

		};
		
		System.out.println("Stream     : " + quantoTempo(stream));
		System.out.println("Collections: " + quantoTempo(collections));

		Runnable streamTree = () -> {
			Set<String> paroleUniche = Stream.of(parole).
			filter( p -> p.length()>2).
			map( String::toLowerCase ).
			collect( toCollection(TreeSet::new) );
			System.out.println(paroleUniche);
		};
		System.out.println("StreamTree: " + quantoTempo(streamTree));

		
		
		// Diverso ordinamento
		
		List<String> paroleUniche = Stream.of(parole).
		filter( p -> p.length()>2).
		map( String::toLowerCase ).
		distinct().
		sorted(Comparator.naturalOrder()).  // alfabetico crescente
		sorted(Comparator.reverseOrder()).  // alfabetico decrescente
		sorted(Comparator.comparing(String::length)).  // per lunghezza
		sorted(Comparator.comparing(String::length).reversed()). // l decr
				// FUNZIONALMENTE EQUIVALENTE A
		sorted( (a, b) -> -( Integer.valueOf(a.length()).
									compareTo(Integer.valueOf(b.length())) ) ).
		  // Per evitare boxing:
		sorted(Comparator.comparingInt(String::length).reversed()).
		// FUNZIONALMENTE EQUIVALENTE A
		sorted( (a, b) -> -( a.length() - b.length()) ).
		collect( toList() );
		System.out.println(paroleUniche);
		
		
		
		Stream<String> primaParte = Stream.of(parole).
				filter( p -> p.length()>2).
				map( String::toLowerCase );
		
		Map<Integer, List<String>> parolePerLunghezza = 
		primaParte.
		distinct().
		collect( groupingBy( String::length ));
		
		System.out.println(parolePerLunghezza);
		
		Stream.of(parole).
		filter( p -> p.length()>2).
		map( String::toLowerCase ).
		distinct().
		//collect( groupingBy( String::length ))
		// EQUIVALE A
		collect( groupingBy( String::length,  // classifier
							 HashMap::new,	  // supplier
							 toList()		  // downstream
				));

		System.out.println(
				Stream.of(parole).
				filter( p -> p.length()>2).
				map( String::toLowerCase ).
				collect( groupingBy( String::length,  // classifier
									 HashMap::new,	  // supplier
									 counting()		  // downstream
						))
				);
		
		Map<Character,Long> frequenzeIniziali = 
		Stream.of(parole).
		filter( p -> p.length()>0 ).
		map( String::toUpperCase ).
		collect( groupingBy( s -> s.charAt(0),// classifier --> key : Character
				 			HashMap::new,	  // supplier
				 			counting()		  // downstream --> value : Long
				));
		
		System.out.println(frequenzeIniziali);
		
		frequenzeIniziali.entrySet().stream().
		// QUI!!!
		forEach(e -> System.out.println( e.getKey() + " : " + e.getValue()));
		
		// 1. stampare in ordine (decr) di frequenza
		// 2. stampare un istogramma ( con num di '*' == alla freq.)
		// 3. stampare un istrogramma ordinato (freq max in alto)
		
	}
	
	public static long quantoTempo(Runnable  operazione ) {
		long tBegin = System.nanoTime();
		
		operazione.run();
		
		long tEnd = System.nanoTime();
		
		return tEnd-tBegin;
	}
}
