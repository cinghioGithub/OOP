package groups;

import java.util.HashSet;
import java.util.Set;

public class ProductType {
	
	private String Type;
	private Set<Fornitore> fornitori = new HashSet<>();
	
	public ProductType(String t) {
		this.Type = t;
	}
	
	public String getType() {
		return this.Type;
	}
	
	public Set<Fornitore> getFornitori(){
		return this.fornitori;
	}
}
