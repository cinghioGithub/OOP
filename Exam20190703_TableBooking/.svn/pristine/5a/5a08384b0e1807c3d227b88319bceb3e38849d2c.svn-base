package it.polito.oop.tables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a restaurant with a set of tables, it allows parties of client
 * to sit and leave tables.
 *
 */
public class Restaurant {
	
	private Map<Integer, Table> tables = new HashMap<>();
	private Map<Integer, Party> parties = new HashMap<>();
	private int numTables = 1;
	private int numParties = 1;
	private String openingTime;
	private String closingTime;
	
    public void defineTables(int... seats) {
    	for(int i : seats) {
    		Table t = new Table(numTables, i);
    		this.tables.put(this.numTables, t);
    		this.numTables++;
    	}
    	
//    	System.out.println(this.tables.values().stream().map(Table::getPosti).collect(Collectors.toList()));
    }
    
    public int getSeats() {
        return this.tables.values().stream().mapToInt(Table::getPosti).sum();
    }

    public int tableSize(int tableId) throws IllegalArgumentException {
    	if(!this.tables.containsKey(tableId)) {
    		throw new IllegalArgumentException();
    	}
    	
//    	System.out.println(this.tables.get(tableId));
    	
        return this.tables.get(tableId).getPosti();
    }
    
    public String getOpeningTime() {
        return this.openingTime;
    }
    
    public void setOpeningTime(String time) {
    	this.openingTime = time;
    }
    
    public String getClosingTime() {
        return this.closingTime;
    }
    
    public void setClosingTime(String time) {
    	this.closingTime = time;
    }

    public int newParty(String name, int count, String mobile) {
    	Party p = new Party(name, count, mobile, this.numParties);
    	this.parties.put(this.numParties, p);
    	this.numParties++;
        return this.numParties-1;
    }
    
    public String getPartyName(int partyId) {
    	if(!this.parties.containsKey(partyId)) {
    		throw new IllegalArgumentException();
    	}
    	
        return this.parties.get(partyId).getName();
    }
    
    public int getPartySize(int partyId) {
    	if(!this.parties.containsKey(partyId)) {
    		throw new IllegalArgumentException();
    	}
    	
        return this.parties.get(partyId).getNumPeople();
    }
    
    public String getPartyPhone(int partyId) {
    	if(!this.parties.containsKey(partyId)) {
    		throw new IllegalArgumentException();
    	}
    	
        return this.parties.get(partyId).getPhoneNumber();
    }
    
    public int countParties() {
        return this.parties.size();
    }
    
    public void takeTable(int table, int partyId,String time) {
    	Table t = this.tables.get(table);
    	this.parties.get(partyId).setSeat(1);
    	this.parties.get(partyId).setTable(t);
    	t.setBusy(true);
    	t.setBusyTime(time);
    	t.setOccupated(this.parties.get(partyId));
    	this.estimateAvailable(table);
    }
    
    public int leaveTable(int table, String time) {
    	Table t = this.tables.get(table);
    	this.parties.get(t.getOccupated().getPartyId()).setSeat(0);
    	this.parties.get(t.getOccupated().getPartyId()).getOraLasciato().put(t.getCodT(),  time);
    	t.setBusy(false);
    	t.getLasciato().add(t.getOccupated());
    	t.setLeaveTime(time);
        return t.getOccupated().getPartyId();
    }
    
    public String estimateAvailable(int table) {
    	Table t = this.tables.get(table);
    	String fine = "";
    	if(t.getPrenotazioni().size() == 0) {
	    	if(!t.isBusy())
	    		return this.openingTime;
	    	
	    	String orario[] = t.getBusyTime().split(":");
	    	int ore = Integer.parseInt(orario[0]);
	    	int minuti = Integer.parseInt(orario[1]);
	    	
	    	int stima = 40 + 5*t.getOccupated().getNumPeople();
	    	
	    	int stimaOre = ore + stima/60;
	    	int stimaMinuti = minuti + stima%60;
	    	
	    	if(stimaMinuti >= 60) {
//	    		int delta = stimaMinuti - 60;
	    		stimaMinuti = stimaMinuti - 60;
	    		stimaOre++;
	    	}
	    	fine = stimaOre + ":" + stimaMinuti;
    	}
    	else{
    		String prenotazioni = "00:00";
    		Prenotazione pTot = null;
    		for(Prenotazione p : t.getPrenotazioni()) {
    			if(p.getTime().compareTo(prenotazioni)>0) {
    				prenotazioni = p.getTime();
    				pTot = p;
    			}
    		}
    		
//    		System.out.println(prenotazioni);
    		
    		String orario[] = prenotazioni.split(":");
    		
    		int ore = Integer.parseInt(orario[0]);
	    	int minuti = Integer.parseInt(orario[1]);
	    	
	    	int stima = 40 + 5*pTot.getParty().getNumPeople();
	    	
	    	int stimaOre = ore + stima/60;
	    	int stimaMinuti = minuti + stima%60;
	    	
	    	if(stimaMinuti >= 60) {
//	    		int delta = stimaMinuti - 60;
	    		stimaMinuti = stimaMinuti - 60;
	    		stimaOre++;
	    	}
	    	fine = stimaOre + ":" + stimaMinuti;
    	}
    	
    	t.getOccupated().getOraStimata().put(t.getCodT(), fine);
    	
        return fine;
    }
    
    public List<TableOption> estimateWaiting(int partySize){
    	List<TableOption> l = new ArrayList<>();
//    	String time = "";
    	Restaurant re = this;
    	
//    	System.out.println(this.tables);
    	
    	for(Table t : this.tables.values()) {
    		TableOption tb = new TableOption() {
    			
//    			public Double num;
//    			public String time;
    			
				@Override
				public String getTime() {
					// TODO Auto-generated method stub
//					this.time = re.estimateAvailable(t.getCodT());
					return re.estimateAvailable(t.getCodT());
				}

				@Override
				public int getTable() {
					// TODO Auto-generated method stub
					return t.getCodT();
				}

				@Override
				public double getOccupation() {
					// TODO Auto-generated method stub
					double a1 = (double)partySize;
					double a2 = (double)t.getPosti();
					double a3 = (a1/a2);
//					this.num = a3;
					return a3 ;
				}
    			
    		};
    		
//    		System.out.println(tb.getTime());
//    		System.out.println(tb.getOccupation());
//    		System.out.println(t.getPosti());
    		
    		if(tb.getTime().compareTo(this.closingTime) < 0) {
    			if(tb.getOccupation() <= 1.0) {
    				l.add(tb);
    			}
    		}
    	}
    	
//    	System.out.println(l);
        return l.stream().sorted((a,b) -> {
        	if(b.getOccupation() == a.getOccupation()) {
        		return a.getTime().compareTo(b.getTime());
        	}
        	return (int) (b.getOccupation() - a.getOccupation());
        }).collect(Collectors.toList());
    }
    
    public void bookTable(int table, int partyId, String time) {
    	this.tables.get(table).getPrenotazioni().add(new Prenotazione(this.parties.get(partyId), time));
    }
    
    public int countServedCustomers() {
//    	System.out.println(this.parties.size());
//    	for(Party p : this.parties.values()) {
//    		System.out.println(p.isSeat() + " "  + p.getPartyId());
//    	}
        return this.parties.values().stream().
        		filter(p -> p.isSeat() == 0 ).
        		mapToInt(Party::getNumPeople).sum();
    }
    
    public int countServedParties() {
        return this.parties.values().stream().filter(p -> p.isSeat() == 0).
        		mapToInt(p -> 1).sum();
    }

    public Map<Integer,Long> tableTurnover() {
    	Map<Integer,Long> map = new HashMap<>();
    	
    	for(Table t : this.tables.values()) {
    		long c = 0;
    		for(Party p : this.parties.values()) {
    			if(p.getTable()!= null && p.getTable().getCodT()==t.getCodT()) {
    				c++;
    			}
    		}
    		map.put(t.getCodT(), c);
    	}
    	
        return map;
    }
    
    public int estimationError(int table) {
    	Table t = this.tables.get(table);
    	int tot = 0;
    	
    	for(Party p : t.getLasciato()) {
    		int ora1 = Integer.parseInt(p.getOraLasciato().get(t.getCodT()).split(":")[0])*60 + Integer.parseInt(p.getOraLasciato().get(t.getCodT()).split(":")[1]);
    		int ora2 = Integer.parseInt(p.getOraStimata().get(t.getCodT()).split(":")[0])*60 + Integer.parseInt(p.getOraStimata().get(t.getCodT()).split(":")[1]);
    		
    		int a = ora2 - ora1;
    		tot += a;
    	}
        return tot/t.getLasciato().size();
    }
}
