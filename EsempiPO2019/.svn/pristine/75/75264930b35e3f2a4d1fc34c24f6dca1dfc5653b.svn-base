package polibook.app;

import java.util.Arrays;

import polibook.Utente;

public class AppPoliBook {

	public static void main(String[] args) {
		
		Utente mark = new Utente("mark","Zucc","Mark",
				"mark@polito.it");
		
		Utente jane = new Utente("jane","Doe","Jane","jane@polito.it");
		
		mark.nuovoAmico(jane);
		//jane.nuovoAmico(mark);
		
		String s="hello2";
		
		System.out.println(mark);
		
		// EQUIVALE A
		
		String ss = mark.toString();
		System.out.println(ss);
		
		Utente mark2 = new Utente("mark","Zucc","Mark",
				"mark@polito.it");
		

		boolean stessoOggetto =  mark == mark2;
		
		boolean uguali = mark.equals(mark2);
		
		mark.equals("hello");
		
		Utente[] utenti = {mark,jane};
		
		Arrays.sort( (Object[]) utenti); 
		/*
		 * Utente extends Object  ==> Utente[] extends Object[]
		 * 
		 * COVARIANZA
		 */
		
		for(Utente u : utenti) {
			System.out.println(u);
		}
		
		// Conseguenza della covarianza
		Object[] array = utenti;
		array[0] = "Fregato!!";  // il compilatore ma non la JVM
	}

}
