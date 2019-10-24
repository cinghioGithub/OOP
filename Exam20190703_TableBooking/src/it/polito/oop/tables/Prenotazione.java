package it.polito.oop.tables;

public class Prenotazione {

	private Party party;
	private String time;
	
	public Prenotazione(Party party, String time) {

		this.party = party;
		this.time = time;
	}

	public Party getParty() {
		return party;
	}

	public String getTime() {
		return time;
	}
	
	
}
