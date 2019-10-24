package groups;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private String Name;
	private Set<Group> groups = new HashSet<>();
	
	public Customer(String s) {
		this.Name = s;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public Set<Group> getGroups(){
		return this.groups;
	}
}
