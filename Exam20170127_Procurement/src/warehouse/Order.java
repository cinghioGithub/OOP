package warehouse;

public class Order {
	
	private String code;
	private Product product;
	private Supplier supplier;
	private Integer quantity;
	private boolean delivered = false;
	
	public Order(String code, Product product, Supplier supplier, Integer quantity) {
		this.code = code;
		this.product = product;
		this.supplier = supplier;
		this.quantity = quantity;
	}

	public String getCode(){
		// TODO: Completare!
		return this.code;
	}
	
	public boolean delivered(){
		// TODO: Completare!
		return this.delivered;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setDelivered() throws MultipleDelivery {
		// TODO: Completare!
		if(this.delivered)
			throw new MultipleDelivery();
		this.product.setQuantity(this.product.getQuantity() + this.quantity);
		this.delivered = true;
	}
	
	public String toString(){
		// TODO: Completare!
		return "Order " + this.code + " for " + this.quantity + " of " + this.product.getCode()
		+ " : " + this.product.getDescription() + " from " + this.supplier.getNome();
	}
}
