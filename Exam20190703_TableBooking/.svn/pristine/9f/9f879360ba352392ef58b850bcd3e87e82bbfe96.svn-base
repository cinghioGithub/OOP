package it.polito.oop.tables;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table{

	private int codT;
	private int posti;
	private boolean busy = false;
	private String busyTime;
	private Party occupated;
	private String leaveTime;
	private List<Prenotazione> prenotazioni = new LinkedList<>();
	private List<Party> lasciato = new ArrayList<>();

	public Table(int c, int p) {
		this.codT = c;
		this.posti = p;
	}

	public int getCodT() {
		return this.codT;
	}

	public int getPosti() {
		return this.posti;
	}
	
	public boolean isBusy() {
		return this.busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public String getBusyTime() {
		return this.busyTime;
	}

	public void setBusyTime(String busyTime) {
		this.busyTime = busyTime;
	}

	public Party getOccupated() {
		return this.occupated;
	}

	public void setOccupated(Party occupated) {
		this.occupated = occupated;
	}

	public String getLeaveTime() {
		return this.leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	
	public List<Prenotazione> getPrenotazioni(){
		return this.prenotazioni;
	}

	public List<Party> getLasciato() {
		return lasciato;
	}
	
	
//	public String getTime() {
//		
//		if(!this.isBusy())
//    		return "";
//		
//		String orario[] = this.getBusyTime().split(":");
//    	int ore = Integer.parseInt(orario[0]);
//    	int minuti = Integer.parseInt(orario[1]);
//    	
//    	int stima = 40 + 5*this.getOccupated().getNumPeople();
//    	
//    	int stimaOre = ore + stima/60;
//    	int stimaMinuti = minuti + stima%60;
//    	
//    	if(stimaMinuti >= 60) {
////    		int delta = stimaMinuti - 60;
//    		stimaMinuti = stimaMinuti - 60;
//    		stimaOre++;
//    	}
//    	
//        return stimaOre + ":" + stimaMinuti;
//	}
//	
//	public int getTable() {
//		return this.getCodT();
//	}
//	
//	public double getOccupation() {
//		return (double) (this.occupated.getNumPeople()/this.posti);
//	}
//	
//	public String toString() {
//		return null;
//	}
}
