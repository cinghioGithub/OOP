package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Restaurant {
	
	private Map<String, Race> races = new HashMap<>();
	private Map<Integer, Hall> halls = new HashMap<>();
	private List<Party> partyes = new ArrayList<>();
	private List<Hall> order = new ArrayList<>();

    public Restaurant() {
	}
	
	public Race defineRace(String name) throws MilliwaysException{
		Race r = new Race(name);
		
		if(this.races.get(name) != null) {
			throw new MilliwaysException();
		}
		
		this.races.put(name, r);
		
	    return r;
	}
	
	public Party createParty() {
		Party p = new Party();
		
		this.partyes.add(p);
		
	    return p;
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
		if(this.halls.containsKey(id)) {
			throw new MilliwaysException();
		}
		
		Hall h = new Hall(id);
		this.order.add(h);
		this.halls.put(id, h);
		
	    return h;
	}

	public List<Hall> getHallList() {
		return this.order;
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		if(!hall.isSuitable(party)) {
			throw new MilliwaysException();
		}
		
		party.setHall(hall);
		
        return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		for(Hall h : this.halls.values()) {
			if(h.isSuitable(party)) {
				party.setHall(h);
				return h;
			}
		}
		
		throw new MilliwaysException();
	}

	public Map<Race, Integer> statComposition() {
		Map<Race, Integer> ris = new HashMap<>();
		int c = 0;
		
		for(Race r : this.races.values()) {
			c = 0;
			for(Party p : this.partyes) {
				if(p.getHall() != null)
					for(Entry<Race, Integer> e : p.getCompanions().entrySet()) {
						if(e.getKey().getName().equals(r.getName())) {
							c += e.getValue();
						}
					}
			}
			if(c != 0)
				ris.put(r, c);
		}
		
        return ris;
    }

	public List<String> statFacility() {
		Map<String, Integer> tmp = new HashMap<>();
		Set<String> set = new HashSet<>();
		
		for(Entry<Integer, Hall> h : this.halls.entrySet()) {
			for(String str : h.getValue().getFacilities()) {
				set.add(str);
			}
		}
		
		int c = 0;
		for(String str : set) {
			c = 0;
			for(Entry<Integer, Hall> h : this.halls.entrySet()) {
				for(String s : h.getValue().getFacilities()) {
					if(s.equals(str)) {
						c++;
					}
				}
			}
			tmp.put(str, c);
		}
		
        return tmp.entrySet().stream().sorted((a,b) -> {
        	if(a.getValue() == b.getValue()) {
        		return a.getKey().compareTo(b.getKey());
        	}
        	
        	return b.getValue() - a.getValue();
        }).map(e -> e.getKey()).collect(Collectors.toList());
	}
	
	public Map<Integer,List<Integer>> statHalls() {
		
		Set<Integer> set = new HashSet<>();
		Map<Integer,List<Integer>> map = new TreeMap<>();
		
		for(Entry<Integer, Hall> h : this.halls.entrySet()) {
			set.add(h.getValue().getFacilities().size());
		}
		
		
		for(Integer i : set) {
			List<Integer> list = new ArrayList<>();
			for(Entry<Integer, Hall> h : this.halls.entrySet()) {
				if(h.getValue().getFacilities().size() ==  i) {
					list.add(h.getValue().getId());
				}
			}
			list.sort((a,b) -> a.compareTo(b));
			map.put(i, list);
		}
		
        return map;
	}

}
