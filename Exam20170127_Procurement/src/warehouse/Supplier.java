package warehouse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Supplier {
	
	private String code;
	private String name;
	private Set<Product> products = new HashSet<>();
	private List<Order> orders = new ArrayList<>();

	public Supplier(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCodice(){
		// TODO: completare!
		return this.code;
	}

	public String getNome(){
		// TODO: completare!
		return this.name;
	}
	
	public void newSupply(Product product){
		// TODO: completare!
		this.products.add(product);
		product.getSuppliers().add(this);
	}
	
	public List<Order> getOrders(){
		return this.orders;
	}
	
	public List<Product> supplies(){
		// TODO: completare!
		return this.products.stream().sorted((a,b) -> {
			return a.getDescription().compareTo(b.getDescription());
		}).collect(Collectors.toList());
	}
}
