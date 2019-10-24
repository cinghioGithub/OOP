package it.polito.oop.milliways;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Race {
	
	private String name;
	private Set<String> requirements = new HashSet<>();
	
	public Race(String name) {
		this.name = name;
	}
    
	public void addRequirement(String requirement) throws MilliwaysException {
		if(!this.requirements.add(requirement)) {
			throw new MilliwaysException();
		}
	}
	
	public List<String> getRequirements() {
        return this.requirements.stream().sorted((a,b) -> a.compareTo(b)).collect(Collectors.toList());
	}
	
	public String getName() {
        return this.name;
	}
}
