package polibook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Utente implements Comparable {
	
	// Attributi (sono privati --> incapsulamento / Encapsulation )
	// Information Hiding
	private final String nickname;
	private String nome;
	private String cognome;
	private String email;
	
	// Associazioni
	//private Messaggio messaggio;
//	private Messaggio[] messaggi;
//	private int prossimoIndice = 0;
	private List<Messaggio> messaggi = new ArrayList<>();
	
	//private Utente[] amici = new Utente[MILLE_MILA];
	private List<Utente> amici = new ArrayList<>();
	
	
	//#define MAX_NUM_MESSAGGI CR7
	static final int MAX_NUM_MESSAGGI = 100;
	static final int MILLE_MILA = 4;  // gatti
	
	
	// Costruttore (CTOR)
	public Utente(final String nickname, String nome, String cognome, String email){
		this.nickname = nickname;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		
		// this : l'oggetto corrente che si sta inizializzando / costruendo
		
		//messaggi = new Messaggio[MAX_NUM_MESSAGGI];
	}
	
//	public void setNickname(String newNickname) {
//		this.nickname = newNickname;
//	}
	
	// Metodi
	public void mostra() {
		System.out.println(this.nome + " " + this.cognome + 
							" (" + this.nickname + ")");
	}
	
	// getter
	public String getNickname() {
		return this.nickname;
	}
	
	// setter
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public void scriviMesaggio(String testo) {
		//int prossimoIndice = 0;  // NO!!! è locale
		//1. creare un messaggio con il testo indicato
		
		//Messaggio msg = new Messaggio(testo,0,this);
//		Messaggio msg = new Messaggio(testo,0,this,Messaggio.TIPO_SEMPLICE);
		// con parametro int è possibile anche commettere errori
//		Messaggio msg = new Messaggio(testo,0,this,42);
		// ma usando un enum posso solo usare valori corretti:
		//Messaggio msg = new Messaggio(testo,0,this,Messaggio.Tipo.SEMPLICE);
		// meglio usare la sottoclasse esatta
		
		long ts = System.nanoTime();
		Messaggio msg = new MessaggioSemplice(testo,ts,this);
		
		//2. devo collegare il messaggio secondo l'associazione "scrive"
		
		//this.messaggio = msg;
//		this.messaggi[ prossimoIndice ] = msg;
//		
//		prossimoIndice++;
		
		messaggi.add(msg);
	}
	
	
	
	public void stampaMessaggi() {
		
//		for(int i=0; i<messaggi.length; ++i) {
//			Messaggio m = messaggi[i];
//			if(m==null) break;
//			//System.out.println(m.getTesto());
//			//System.out.println(m.getTimestamp());
//			
//			// Oppure usando il principio di delega
//			
//			System.out.println( m.versioneStringa() );
//		}
		
		int n = messaggi.size();
		for(int i=0; i<n; ++i) {
			Messaggio m = messaggi.get(i);
			System.out.println( m.versioneStringa() );
		}
		
		for(Messaggio m : messaggi) {
			System.out.println( m.versioneStringa() );
		}
		// TRADOTTO AUTOMATICAMENTE IN
		Iterator<Messaggio> it = messaggi.iterator();
		while(it.hasNext()) {
			Messaggio m = it.next();
			System.out.println( m.versioneStringa() );
		}
		
		messaggi.forEach( m -> System.out.println( m.versioneStringa() ) );
		
		// SE e' dewfinito toString()
		
		messaggi.forEach( m -> System.out.println( m ) );
		// OPPURE
		messaggi.forEach( System.out::println );
		
	}
	
	/**
	 * Definisce una relazione di amicizia con un altro
	 * utente. La relazione è bidirezionale.
	 * Ovvero l'amicizia stabilita e' reciproca.
	 * 
	 * @param amico l'altro utente con cui stringere amicizia
	 */
	public void nuovoAmico(Utente amico) {
		
//		for(int i=0; i<amici.length; ++i) {
//			if(amici[i] == null) {
//				amici[i] = amico;
//				break;
//			}
//		}
		
		this.nuovoAmicoNonRicorsivo(amico);
		amico.nuovoAmicoNonRicorsivo(this);
	}
	
	private void nuovoAmicoNonRicorsivo(Utente amico) {
		
//		for(int i=0; i<amici.length; ++i) {
//			if(amici[i] == null) {
//				amici[i] = amico;
//				break;
//			}
//		}
		amici.add(amico);
	}

	/**
	 * restituisce tutti i messaggi di tutti gli amici.
	 * 
	 * <b>Attenzione</b>: e' un metodo potenzialmente dispendioso
	 * 
	 * @return
	 */
	public String[] messaggiAmici() {
		return null;
		
	}
	
	public String[] messaggiAmici(int max) {
		return null;
		
	}
	
	@Override
	public String toString() {
		return this.nickname + " : " + this.nome + " " + this.cognome;
	}

	@Override
	public boolean equals(Object u) {
		//if(u == null) return false;
		if( u instanceof Utente) {
			Utente altro = (Utente)u;
			return altro.nickname.equals( this.nickname );
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return nickname.hashCode();
	}

	@Override
	public int compareTo(Object o) {
		
		//return this.nickname.compareTo( ((Utente)o).nickname );
		Utente altro = (Utente)o;
		
		int cfrCognomi = this.cognome.compareTo(altro.cognome);
		if( cfrCognomi != 0 ) return cfrCognomi;
		
		int cfrNomi = this.nome.compareTo(altro.nome);
		if( cfrNomi != 0 ) return cfrNomi;
		
		return this.nickname.compareTo(altro.nickname);
		
	}

	public Collection<Utente> getAmici() {
		return amici;
	}

	public Collection<Messaggio> getMessaggi() {
		return messaggi;
	}

	public List<Messaggio> getMessagiAmici() {
		ArrayList<Messaggio> tutti = new ArrayList<>();
		
		for(Utente amico : amici) {
			Collection<Messaggio> messaggiAmico = amico.getMessaggi();
			tutti.addAll(messaggiAmico);
		}
		
		Collections.sort(tutti);
		
		return tutti;
	}
}
