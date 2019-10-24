package it.polito.oop.base;

public class EsempiStringhe {

	public static void main(String[] args) {
		
		String s = "hello";
		// di fatto implica quanto segue:
		char[] chars = {'h','e','l','l','o'};
		s = new String(chars);
		
		
		int l = s.length();
		
		String t = s.toUpperCase();  // Le String sono IMMUTABILI!!!
		
		System.out.println(s);
		System.out.println(t);
		
		String elenco = "";
		for( char ch : chars ) {
			elenco += "-" + ch;
		}
		System.out.println(elenco);
		
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<100000; ++i) {
			sb.append(",").append(i);
		}
		System.out.println(sb.length());

		
		String risultato="";
		for(int i=0; i<100000; ++i) {
			risultato+=","+i;
		}
		System.out.println(risultato.length());
		

	}

}
