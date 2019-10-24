package polibook;

public abstract class Messaggio implements Comparable<Messaggio> {
	
	// Attributi
	private String testo;
	private long timestamp;
//	private int tipo;
//	
//	final static int TIPO_SEMPLICE = 1;
//	final static int TIPO_CITAZIONE = 2;
//	final static int TIPO_PUBBLICITA = 3;
//  private Tipo tipo;
	
	// OPPURE (meglio!)
	
//	enum Tipo { SEMPLICE, CITAZIONE, PUBBLICITA };
//	private Tipo tipo;
	
	// In ogni caso un campo tipo suggerisce di pensare a ereditarieta'
	
	
	// Associazioni
	private Utente autore;  // molteplicita' massima = 1
	
	public Messaggio(String testo, long ts, Utente autore) {
		this.testo = testo;
		this.timestamp = ts;
		this.autore = autore;
		// il fatto che per costruire un messaggio si debba
		// fornire anche l'autore, garantisce la molteplicità
		// minima = 1
	}
	
//	public Messaggio(String testo, int ts, Utente autore, Tipo tipo) {
//		this.testo = testo;
//		this.timestamp = ts;
//		this.autore = autore;
//		this.tipo = tipo;
//		
//	}

	
	
	public final String getTesto() {
		return this.testo;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public abstract String versioneStringa() ; //{
////		switch(tipo) {
////		case SEMPLICE: 
////			return this.testo + "\n[" + this.timestamp + "]";
////		case CITAZIONE:
////			return this.testo + "\n Ctazione di .... \n[" + this.timestamp + "]";
////		case PUBBLICITA:
////			return "Pubblicita':\n" + this.testo + "\n[" + this.timestamp + "]";
////		default: return "ERRORE: tipo sconosiuto";
////		}
//		
//		System.err.println("Ti sei dimenticato di ridefinire il metodo versionStringa();!!");
//
//		return this.getTesto() + 
//			   "\n[" + this.getTimestamp() + "]";
//	}

	
	@Override
	public int compareTo(Messaggio altro) {
		return (int)( - (this.timestamp - altro.timestamp) );
	}
	
	
	
	
	
}
