package groups;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GroupHandling {
	
	private Map<String, ProductType> priductType = new HashMap<>();
	private Map<String,  Fornitore> fornitori = new HashMap<>();
	private Map<String, Group> groups = new HashMap<>();
	private Map<String, Customer> customers = new HashMap<>();
	private List<Bid> bids = new ArrayList<>();
//R1	
	public void addProduct (String productTypeName, String... supplierNames) 
			throws GroupHandlingException {
		
		if(this.priductType.containsKey(productTypeName)) {
			throw new GroupHandlingException();
		}
		else {
			ProductType p = new ProductType(productTypeName);
			this.priductType.put(productTypeName, p);
			
			for(String str : supplierNames) {
				if(this.fornitori.containsKey(str)) {
					
					p.getFornitori().add(this.fornitori.get(str));
					this.fornitori.get(str).getProducts().add(p);
				}
				else {
					Fornitore f = new Fornitore(str);
					p.getFornitori().add(f);
					
					f.getProducts().add(p);
					
					this.fornitori.put(str, f);
				}
			}
		}
	}
	
	public List<String> getProductTypes (String supplierName) {
		Fornitore f = this.fornitori.get(supplierName);
		
		return f.getProducts().stream().map(ProductType::getType).sorted().collect(Collectors.toList());
	}
	
//R2	
	public void addGroup (String name, String productName, String... customerNames) 
			throws GroupHandlingException {
		if(this.groups.containsKey(name)) {
			throw new GroupHandlingException();
		}
		else {
			if(!this.priductType.containsKey(productName)) {
				throw new GroupHandlingException();
			}
			else {
				Group g = new Group(name, this.priductType.get(productName));
				this.groups.put(name, g);
				
				for(String s : customerNames) {
					if(this.customers.containsKey(s)) {
						this.customers.get(s).getGroups().add(g);
						g.getCustomers().add(this.customers.get(s));
					}
					else {
						Customer c = new Customer(s);
						this.customers.put(s, c);
						g.getCustomers().add(c);
						c.getGroups().add(g);
					}
				}
			}
		}
	}
	
	public List<String> getGroups (String customerName) {
        return this.customers.get(customerName).getGroups().stream().map(Group::getName).sorted().collect(Collectors.toList());
	}

//R3
	public void setSuppliers (String groupName, String... supplierNames)
			throws GroupHandlingException {
		
		for(String s : supplierNames) {
			if(!this.fornitori.containsKey(s)) {
				throw new GroupHandlingException();
			}
			else{
				Fornitore f = this.fornitori.get(s);
				Group g = this.groups.get(groupName);
				
//				System.out.println(this.groups.keySet());
				
				g.getProductType();
				ProductType t = this.priductType.get(g.getProductType().getType());
				
//				System.out.println(g.getProductType().getType());
//				System.out.println(f.getProducts().stream().map(ProductType::getType).collect(Collectors.toList()));
				
				if(!f.getProducts().contains(t)) {
					throw new GroupHandlingException();
				}
				else {
					this.groups.get(groupName).getFornitori().add(this.fornitori.get(s));
					this.fornitori.get(s).getGroups().add(this.groups.get(groupName));
				}
			}
		}
	}
	
	public void addBid (String groupName, String supplierName, int price)
			throws GroupHandlingException {
		
		if(!this.fornitori.get(supplierName).getGroups().contains(this.groups.get(groupName))) {
			throw new GroupHandlingException();
		}
		else {
			Bid b = new Bid(price, this.fornitori.get(supplierName));
			this.groups.get(groupName).getBids().add(b);
			this.groups.get(groupName).getVotes().put(b, 0);
			b.setGroups(this.groups.get(groupName));
			this.fornitori.get(supplierName).getB().put(this.groups.get(groupName), b);
			this.bids.add(b);
		}
	}
	
	public String getBids (String groupName) {
		Group g = this.groups.get(groupName);
		
		List<String> l = g.getBids().stream().sorted((a,b) -> {
			if(a.getPrice().compareTo(b.getPrice())==0) {
				return a.getF().getName().compareTo(b.getF().getName());
			}
			return a.getPrice().compareTo(b.getPrice());
		}).map(s -> String.format("%s:%d", s.getF().getName(), s.getPrice())).collect(Collectors.toList());
		
		String f = "";
		int i=0;
		for(String s : l) {
			if(i==0)
				f = s;
			else
				f = f + "," + s;
			i++;
		}
        return f;
	}
	
	
//R4	
	public void vote (String groupName, String customerName, String supplierName)
			throws GroupHandlingException {
		
		if(!this.groups.get(groupName).getCustomers().contains(this.customers.get(customerName))) {
			throw new GroupHandlingException();
		}
		else {
			Group g = this.groups.get(groupName);
			boolean i = false;
			for(Bid b : g.getBids()) {
				if(b.getF().getName().equals(supplierName))
					i = true;
			}
			if(!i) {
				throw new GroupHandlingException();
			}
			else {
				int c = 1 + g.getVotes().get(this.fornitori.get(supplierName).getB().get(g));
				g.getVotes().put(this.fornitori.get(supplierName).getB().get(g), c);
			}
		}
	}
	
	public String  getVotes (String groupName) {
		
		List<String> l = this.groups.get(groupName).getVotes().entrySet().stream().filter(t -> t.getValue()!=0).map(s -> String.format("%s:%d", s.getKey().getF().getName(), s.getValue())).sorted().collect(Collectors.toList());
		
		String f = "";
		int i=0;
		for(String s : l) {
			if(i==0)
				f = s;
			else
				f = f + "," + s;
			i++;
		}
		
        return f;
	}
	
	public String getWinningBid (String groupName) {
        return this.groups.get(groupName).getVotes().entrySet().stream().sorted((a,b) -> {
        	if(b.getValue().compareTo(a.getValue())==0) {
        		return a.getKey().getPrice().compareTo(b.getKey().getPrice());
        	}
        	return b.getValue().compareTo(a.getValue());
        }).map(s -> String.format("%s:%d", s.getKey().getF().getName(), s.getValue())).findFirst().orElse(null);
	}
	
//R5
	public SortedMap<String, Integer> maxPricePerProductType() { //serve toMap
		SortedMap<String, Integer> map = new TreeMap<>();
		
		for(ProductType p : this.priductType.values()) {
			int max = -1;
			for(Bid b : this.bids) {
				if(b.getGroups().getProductType().getType().equals(p.getType())) {
					if(b.getPrice() > max) {
						max = b.getPrice();
					}
				}
			}
			if(max != -1) {
				map.put(p.getType(), max);
			}
		}
        return map;
	}
	
	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
		Set<Integer> set = new HashSet<>();
		SortedMap<Integer, List<String>> map =  new TreeMap<>((a,b) -> b.compareTo(a));
		List<String> l = new ArrayList<>();
		
		for(Fornitore f : this.fornitori.values()) {
			if(f.getB().size() != 0)
			set.add(f.getB().size());
		}
		
		for(Integer i : set) {
			l.clear();
			for(Fornitore f : this.fornitori.values()) {
				if(f.getB().size() == i) {
					l.add(f.getName());
				}
			}
			map.put(i, l.stream().sorted().collect(Collectors.toList()));
		}
        return map;
	}
	
	public SortedMap<String, Long> numberOfCustomersPerProductType() {
		
		SortedMap<String, Long> map = new TreeMap<>();
		
		for(ProductType p : this.priductType.values()) {
			long i=0;
			for(Customer c : this.customers.values()) {
				for(Group g : c.getGroups()) {
					if(g.getProductType().getType().equals(p.getType()))
						i++;
				}
			}
			if(i!=0)
				map.put(p.getType(), i);
		}
		
        return map;
	}
	
}
