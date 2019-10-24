package polibook;

public class EsempioPB {

	public static void main(String[] args) {
		
		int i;  // dichiarazione (senza inizializzazione)
		i = 0;
		
		int j= i+1;
		
		Utente mark;  // dichiarazione (senza iniz.)
		
		//mark = new Utente();  // crea un nuovo oggetto Utente
							  // metti il riferimento in mark
		
		// NO!!!
//		mark.nickname = "mark";
//		mark.cognome = "Zucc";
//		mark.nome = "Mark";
//		mark.email = "mark@polito.it";
		
		// Utilizzando il ctor
		mark = new Utente("mark","Zucc","Mark",
							"mark@polito.it");
		
		mark . mostra();
		
		System.out.println("Creato oggetto: " + 
							mark.getNickname());
		
		Utente jane;
		jane = null;
		jane = new Utente("jane","Doe","Jane","jane@polito.it");
		jane.mostra();
		
		Utente giovanna = jane;
		// copia riferimento e NON l'oggetto
		
		jane.setNome("Giova");
		
		// due riferimenti punto allo stesso oggetto
		// ALIASING
		giovanna.mostra();
		
		mark = jane;
		
		// l'oggetto prima puntato da mark è divenato
		// irraggiungibile
		
		// garbage collection / raccolta della spazzatura
		// 
		
		mark.scriviMesaggio("3 x CR7");
		
		mark.stampaMessaggi();
		
		mark.scriviMesaggio("Ma stamattina lezione alle 8.30....");
		
		mark.stampaMessaggi();
	}

}
