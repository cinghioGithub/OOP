package it.polito.oop.milliways;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hall {
	
	private Integer ID;
	private Set<String> facilityes = new HashSet<>();
	
	public Hall(Integer id) {
		this.ID = id;
	}

	public int getId() {
		return this.ID;
	}

	public void addFacility(String facility) throws MilliwaysException {
		if(!this.facilityes.add(facility)) {
			throw new MilliwaysException();
		}
	}

	public List<String> getFacilities() {
        return this.facilityes.stream().sorted().collect(Collectors.toList());
	}
	
	int getNumFacilities(){
        return this.facilityes.size();
	}

	public boolean isSuitable(Party party) {
		return this.facilityes.containsAll(party.getRequirements());
	}

}
