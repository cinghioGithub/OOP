package polibook;

public class MsgPubblicita extends Messaggio {
	
	private String inserzionista;
	
	public MsgPubblicita(String testo, 
						int ts, 
						Utente autore, 
						String inserzionista) {
		super(testo,ts,autore);
		this.inserzionista = inserzionista;
	}
	
	@Override
	public String versioneStringa() {
		return "Pubblicita' offerta da :" + inserzionista + 
				"\n" + this.getTesto() + 
				"\n[" + this.getTimestamp() + "]";
		
//		return "Pubblicita' offerta da :" + inserzionista +
//				super.versioneStringa();
	}

}
