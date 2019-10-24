package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Warehouse {
	
	private Integer numOrder = 1;
	private Map<String, Product> products = new HashMap<>();
	private Map<String, Supplier> suppliers = new HashMap<>();
	private Map<String, Order> orders = new HashMap<>();
	
	public Product newProduct(String code, String description){
		// TODO: completare
		Product p = new Product(code, description);
		this.products.put(code, p);
		return p;
	}
	
	public Collection<Product> products(){
		// TODO: completare
		return this.products.values().stream().collect(Collectors.toList());
	}

	public Product findProduct(String code){
		// TODO: completare
		return this.products.get(code);
	}

	public Supplier newSupplier(String code, String name){
		// TODO: completare
		Supplier s = new Supplier(code, name);
		this.suppliers.put(code, s);
		return s;
	}
	
	public Supplier findSupplier(String code){
		// TODO: completare
		return this.suppliers.get(code);
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		// TODO: completare
		Order o = null;
		if(!supp.supplies().contains(prod)) {
			throw new InvalidSupplier();
		}else {
			String s = "ORD" + this.numOrder;
			this.numOrder++;
			o = new Order(s, prod, supp, quantity);
			this.orders.put(s, o);
			prod.getOrders().add(o);
			supp.getOrders().add(o);
		}
		
		return o;
	}

	public Order findOrder(String code){
		// TODO: completare
		return this.orders.get(code);
	}
	
	public List<Order> pendingOrders(){
		// TODO: completare
		return this.orders.values().stream().filter(s -> s.delivered() == false).sorted((a,b) -> {
			return a.getProduct().getCode().compareTo(b.getProduct().getCode());
		}).collect(Collectors.toList());
	}

	public Map<String,List<Order>> ordersByProduct(){
		Map<String,List<Order>> map = new HashMap<>();
		Set<String> set = this.products.values().stream().map(Product::getCode).collect(HashSet::new, HashSet::add, HashSet::addAll);//new HashSet<>();
		List<Order> l = new ArrayList<>();
		
//		System.out.println(this.orders.values());
		
		for(String str : set) {
			l = new ArrayList<>();
			for(Order o : this.orders.values()) {
				
//				System.out.println(o.getProduct().getCode());
//				System.out.println(str);
				
				if(o.getProduct().getCode().equals(str)) {
					l.add(o);
				}
			}
			map.put(str, l);
		}
		return map;
	}
	
	public Map<String,Long> orderNBySupplier(){
		Map<String,Long> map = new TreeMap<>();
//		Set<String> set = this.suppliers.values().stream().map(Supplier::getNome).collect(HashSet::new, HashSet::add, HashSet::addAll);
	    
		for(Supplier s : this.suppliers.values()) {
			Long c = new Long(0);
			for(Order o : s.getOrders()) {
				if(o.delivered()) {
					c++;
				}
			}
			map.put(s.getNome(), c);
		}
		return map;
	}
	
	public List<String> countDeliveredByProduct(){
//		List<String> l = new ArrayList<>();
	    return this.products.values().stream().
	    		map(s -> String.format("%s - %d" , s.getCode(), s.getOrders().stream().
	    				filter(t -> t.delivered() == true).count())).sorted((a,b) -> {
	    					String a1[] = a.split(" ");
	    					String b1[] = b.split(" ");
	    					
	    					return b1[2].compareTo(a1[2]);
	    				}).collect(Collectors.toList());
	}
}
