package groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Fornitore {

	private String Name;
	private Set<ProductType> products =  new HashSet<>();
	private List<Group> groups = new ArrayList<>();
	private Map<Group, Bid> b = new HashMap<>();
	
	public List<Group> getGroups() {
		return this.groups;
	}

	public Fornitore(String s) {
		this.Name = s;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public Set<ProductType> getProducts(){
		return this.products;
	}

	public Map<Group, Bid> getB() {
		return b;
	}

//	public void setB(Bid b) {
//		this.b = b;
//	}
	
	
}
