package warehouse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Product {
	
	private String code;
	private String description;
//	private Integer quantity = new Integer(0);
	private int quantity;
	private Set<Supplier> suppliers = new HashSet<>();
	private List<Order> orders = new ArrayList<>();

	
	public Set<Supplier> getSuppliers() {
		return this.suppliers;
	}

	public Product(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode(){
		// TODO: completare!
		return this.code;
	}

	public String getDescription(){
		// TODO: completare!
		return this.description;
	}
	
	public List<Order> getOrders(){
		return this.orders;
	}
	
	public void setQuantity(int quantity){
		// TODO: completare!
		this.quantity = new Integer(quantity);
	}

	public void decreaseQuantity(){
		// TODO: completare!
		this.quantity--;
	}

	public int getQuantity(){
		// TODO: completare!
		return this.quantity;
	}

	public List<Supplier> suppliers(){
		// TODO: completare!
		return this.suppliers.stream().sorted((a,b) -> a.getNome().compareTo(b.getNome()))
				.collect(Collectors.toList());
	}

	public List<Order> pendingOrders(){
		// TODO: completare
		return this.orders.stream().filter(s -> s.delivered() == false).sorted((a,b) -> {
			return b.getQuantity().compareTo(a.getQuantity());
		}).collect(Collectors.toList());
	}
}
