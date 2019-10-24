package delivery;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private String name;
	private String address;
	private String phoneNum;
	private String email;
	private List<Order> orders = new ArrayList<>();
	
	public List<Order> getOrders() {
		return this.orders;
	}

	private int Id;
	
	public Customer(String name, String address, String phone, String email) {
		this.name = name;
		this.address = address;
		this.phoneNum = phone;
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	@Override
	public String toString() {
		return this.name + ", " + this.address + ", " + this.phoneNum + ", " + this.email;
	}
}
