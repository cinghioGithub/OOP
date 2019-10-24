package delivery;

import java.util.HashMap;
import java.util.Map;

import delivery.Delivery.OrderStatus;

public class Order {
	
	private Integer client;
	
	private Integer Id;
	
	private Map<MenuItem, Integer> items = new HashMap<>();
	private Delivery.OrderStatus stauts = OrderStatus.NEW;
	private double tot;
	
	public Double getTot() {
		return this.tot;
	}

	public void setTot(Double tot) {
		this.tot = tot;
	}

	public Order(Integer client, Integer Id) {
		this.client = client;
		this.Id = Id;
	}

	public Integer getClient() {
		return this.client;
	}

	public Integer getId() {
		return this.Id;
	}

	public Map<MenuItem, Integer> getItems() {
		return this.items;
	}
	
	public Delivery.OrderStatus getStatus(){
		return this.stauts;
	}
	
	public void setStatus(Delivery.OrderStatus status){
		this.stauts = status;
	}

}
