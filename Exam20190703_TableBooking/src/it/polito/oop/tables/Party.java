package it.polito.oop.tables;

import java.util.HashMap;
import java.util.Map;

public class Party {
	
	private String name;
	private int numPeople;
	private String phoneNumber;
	private int partyId;
	private int seat = -1;
	private Table table;
	private Map<Integer, String> oraStimata = new HashMap<>();
	private Map<Integer, String> oralasciato = new HashMap<>();
	
	public Party(String name, int numPeople, String phoneNumber, int p) {
		super();
		this.name = name;
		this.numPeople = numPeople;
		this.phoneNumber = phoneNumber;
		this.partyId = p;
	}

	public String getName() {
		return this.name;
	}

	public int getNumPeople() {
		return this.numPeople;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public int getPartyId() {
		return this.partyId;
	}

	public int isSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Map<Integer, String> getOraStimata() {
		return oraStimata;
	}
	
	public Map<Integer, String> getOraLasciato() {
		return oralasciato;
	}
	
}
