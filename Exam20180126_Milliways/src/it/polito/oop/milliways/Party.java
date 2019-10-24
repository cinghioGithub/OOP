package it.polito.oop.milliways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Party {
	
	private Map<Race, Integer> companions = new HashMap<>();
	private Hall hall;

    public Hall getHall() {
		return this.hall;
	}

	public Map<Race, Integer> getCompanions() {
		return this.companions;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public void addCompanions(Race race, int num) {
    	if(this.companions.containsKey(race)) {
    		int a = this.companions.get(race);
//    		System.out.println(a);
    		int b = a + num;
//    		System.out.println(b);
    		this.companions.put(race, b);
    	}
    	else {
    		this.companions.put(race, num);
    	}
	}

	public int getNum() {
		int ris = 0;
		
//		System.out.println(this.companions);
		
		for(int a : this.companions.values()) {
			ris += a;
		}
		
        return ris;
	}

	public int getNum(Race race) {
	    return this.companions.get(race);
	}

	public List<String> getRequirements() {
        return this.companions.keySet().stream().flatMap(a -> a.getRequirements().stream()).distinct().sorted().collect(Collectors.toList());
	}

    public Map<String,Integer> getDescription(){
    	Map<String,Integer> ris = new HashMap<>();
    	
    	for(Entry<Race, Integer> e : this.companions.entrySet()) {
    		ris.put(e.getKey().getName(), e.getValue());
    	}
    	
        return ris;
    }

}
