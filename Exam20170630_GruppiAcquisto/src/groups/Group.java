package groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
	
	private String Name;
	private ProductType productType;
	private List<Customer> customers = new ArrayList<>();
	private List<Fornitore> fornitori = new ArrayList<>();
	private List<Bid> bids = new ArrayList<>();
	private Map<Bid, Integer> votes = new HashMap<>();

	public Map<Bid, Integer> getVotes() {
		return votes;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public List<Fornitore> getFornitori() {
		return this.fornitori;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public Group(String name, ProductType productType) {
		this.Name = name;
		this.productType = productType;
	}
	
	public String getName() {
		return this.Name;
	}

	public ProductType getProductType() {
		return this.productType;
	}
}
