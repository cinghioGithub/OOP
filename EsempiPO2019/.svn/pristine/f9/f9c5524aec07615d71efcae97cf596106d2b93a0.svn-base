package polibook;

public class MessaggioSemplice extends Messaggio {

	public MessaggioSemplice(String testo, 
						long ts, 
						Utente autore) {
		super(testo,ts,autore);
	}
	
	@Override
	public String versioneStringa() {
		return this.getTesto() + 
				"\n[" + this.getTimestamp() + "]";
	}

	@Override
	public String toString() {
		return this.getTesto() + 
				"\n[" + this.getTimestamp() + "]";
	}

}
