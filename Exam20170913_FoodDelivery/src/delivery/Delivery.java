package delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Delivery {
	
	private int nextIdCustomer = 1;
	private int nextIdOrder = 1;
	
	private Map<Integer, Customer> customers = new HashMap<>();
	private Map<Integer, Order> orders = new HashMap<>();
	private Map<String, MenuItem> menuItems = new HashMap<>();
    
    public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
    
    /**
     * Creates a new customer entry and returns the corresponding unique ID.
     * 
     * The ID for the first customer must be 1.
     * 
     * 
     * @param name name of the customer
     * @param address customer address
     * @param phone customer phone number
     * @param email customer email
     * @return unique customer ID (positive integer)
     */
    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
    	for(Customer c : this.customers.values()) {
    		if(c.getEmail().equals(email)) {
    			throw new DeliveryException();
    		}
    	}
    	
    	Customer c = new Customer(name, address, phone, email);
    	this.customers.put(this.nextIdCustomer, c);
    	c.setId(this.nextIdCustomer);
    	
    	int a = this.nextIdCustomer;
    	this.nextIdCustomer++;
    	
        return a;
    }
    
    /**
     * Returns a string description of the customer in the form:
     * <pre>
     * "NAME, ADDRESS, PHONE, EMAIL"
     * </pre>
     * 
     * @param customerId
     * @return customer description string
     */
    public String customerInfo(int customerId){
        return this.customers.get(customerId).toString();
    }
    
    /**
     * Returns the list of customers, sorted by name
     * 
     */
    public List<String> listCustomers(){
        return this.customers.values().stream().map(Customer::toString).sorted().collect(Collectors.toList());
    }
    
    /**
     * Add a new item to the delivery service menu
     * 
     * @param description description of the item (e.g. "Pizza Margherita", "Bunet")
     * @param price price of the item (e.g. 5 EUR)
     * @param category category of the item (e.g. "Main dish", "Dessert")
     * @param prepTime estimate preparation time in minutes
     */
    public void newMenuItem(String description, double price, String category, int prepTime){
        
    	MenuItem m = new MenuItem(description, price, category, prepTime);
    	this.menuItems.put(description, m);
    }
    
    /**
     * Search for items matching the search string.
     * The items are sorted by category first and then by description.
     * 
     * The format of the items is:
     * <pre>
     * "[CATEGORY] DESCRIPTION : PRICE"
     * </pre>
     * 
     * @param search search string
     * @return list of matching items
     */
    public List<String> findItem(String search){
    	List<String> ris = new ArrayList<>();
    	if(search.equals("")) {
    		ris.addAll(this.menuItems.values().stream().map(MenuItem::toString).collect(Collectors.toList()));
    	}
    	else {
	    	String str = search.toLowerCase();
	    	
	    	for(MenuItem m : this.menuItems.values()) {
	    		String tmp = m.getDescription().toLowerCase();
	    		if(tmp.contains(str)) {
	    			ris.add(m.toString());
	    		}
	    	}
    	}
    	
        return ris.stream().sorted().collect(Collectors.toList());
    }
    
    /**
     * Creates a new order for the given customer.
     * Returns the unique id of the order, a progressive
     * integer number starting at 1.
     * 
     * @param customerId
     * @return order id
     */
    public int newOrder(int customerId){
    	int id = this.nextIdOrder;
    	this.nextIdOrder++;
    	
    	Order o = new Order(customerId, id);
    	this.orders.put(id,  o);
    	this.customers.get(customerId).getOrders().add(o);
    	
        return id;
    }
    
    /**
     * Add a new item to the order with the given quantity.
     * 
     * If the same item is already present in the order is adds the
     * given quantity to the current quantity.
     * 
     * The method returns the final total quantity of the item in the order. 
     * 
     * The item is found through the search string as in {@link #findItem}.
     * If none or more than one item is matched, then an exception is thrown.
     * 
     * @param orderId the id of the order
     * @param search the item search string
     * @param qty the quantity of the item to be added
     * @return the final quantity of the item in the order
     * @throws DeliveryException in case of non unique match or invalid order ID
     */
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
    	int d = 0;
    	if(!this.orders.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
    	String str = search.toLowerCase();
    	
//    	System.out.println(str);
    	
    	Order o = this.orders.get(orderId);
    	MenuItem obj = null;
    	int c = 0;
    	for(MenuItem m : this.menuItems.values()) {
    		String tmp = m.getDescription().toLowerCase();
    		
//    		System.out.println(tmp);
    		
    		if(tmp.contains(str)) {
    			c++;
    			obj = m;
    		}
    	}
    	
//    	System.out.println(c);
    	
    	if(c == 1) {
//    		MenuItem m = this.menuItems.get(search);
//    		Order o = this.orders.get(orderId);
    		
    		if(o.getItems().containsKey(obj)) {
    			d = o.getItems().get(obj) + qty;
    			o.getItems().put(obj, d);
    		}
    		else {
    			d = qty;
    			o.getItems().put(obj, d);
    		}
    	}
    	else {
    		throw new DeliveryException();
    	}
    	
        return d;
    }
    
    /**
     * Show the items of the order using the following format
     * <pre>
     * "DESCRIPTION, QUANTITY"
     * </pre>
     * 
     * @param orderId the order ID
     * @return the list of items in the order
     * @throws DeliveryException when the order ID in invalid
     */
    public List<String> showOrder(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
        return this.orders.get(orderId).getItems().entrySet().stream().map(e -> e.getKey().getDescription() + ", " + e.getValue()).collect(Collectors.toList());
    }
    
    /**
     * Retrieves the total amount of the order.
     * 
     * @param orderId the order ID
     * @return the total amount of the order
     * @throws DeliveryException when the order ID in invalid
     */
    public double totalOrder(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
    	double tot = 0;
    	Order o = this.orders.get(orderId);
		for(Entry<MenuItem, Integer> m : o.getItems().entrySet()) {
			tot += m.getKey().getPrice() * m.getValue();
		}
		
		o.setTot(tot);
		
        return tot;
    }
    
    /**
     * Retrieves the status of an order
     * 
     * @param orderId the order ID
     * @return the current status of the order
     * @throws DeliveryException when the id is invalid
     */
    public OrderStatus getStatus(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
        return this.orders.get(orderId).getStatus();
    }
    
    /**
     * Confirm the order. The status goes from {@code NEW} to {@code CONFIRMED}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>start-up delay (conventionally 5 min)
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code NEW}
     */
    public int confirm(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId) || this.orders.get(orderId).getStatus() != OrderStatus.NEW) {
    		throw new DeliveryException();
    	}
    	
    	this.orders.get(orderId).setStatus(OrderStatus.CONFIRMED);
    	
        return 5 + 15 + this.orders.get(orderId).getItems().keySet().stream().mapToInt(MenuItem::getPrepTime).max().getAsInt();
    }

    /**
     * Start the order preparation. The status goes from {@code CONFIRMED} to {@code PREPARATION}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code CONFIRMED}
     */
    public int start(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId) || this.orders.get(orderId).getStatus() != OrderStatus.CONFIRMED) {
    		throw new DeliveryException();
    	}
    	
    	this.orders.get(orderId).setStatus(OrderStatus.PREPARATION);
    	
        return 15 + this.orders.get(orderId).getItems().keySet().stream().mapToInt(MenuItem::getPrepTime).max().getAsInt();
    }

    /**
     * Begins the order delivery. The status goes from {@code PREPARATION} to {@code ON_DELIVERY}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code PREPARATION}
     */
    public int deliver(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId) || this.orders.get(orderId).getStatus() != OrderStatus.PREPARATION) {
    		throw new DeliveryException();
    	}
    	
    	this.orders.get(orderId).setStatus(OrderStatus.ON_DELIVERY);
    	
        return 15;
    }
    
    /**
     * Complete the delivery. The status goes from {@code ON_DELIVERY} to {@code DELIVERED}
     * 
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code ON_DELIVERY}
     */
    public void complete(int orderId) throws DeliveryException {
    	if(!this.orders.containsKey(orderId) || this.orders.get(orderId).getStatus() != OrderStatus.ON_DELIVERY) {
    		throw new DeliveryException();
    	}
    	
    	this.orders.get(orderId).setStatus(OrderStatus.DELIVERED);
    }
    
    /**
     * Retrieves the total amount for all orders of a customer.
     * 
     * @param customerId the customer ID
     * @return total amount
     */
    public double totalCustomer(int customerId){
    	double tot = 0;
    	
    	Customer c = this.customers.get(customerId);
    	
//    	System.out.println(c.getOrders().size());
    	
    	for(Order o : c.getOrders()) {
    		if(o.getStatus() != OrderStatus.NEW) {
	    		try {
	    			o.setTot(this.totalOrder(o.getId()));
	    		}
	    		catch(Exception e) {}
	    		
	//    		System.out.println(o.getTot());
	    		
	    		tot += o.getTot();
    		}
    	}
    	
        return tot;
    }
    
    /**
     * Computes the best customers by total amount of orders.
     *  
     * @return the classification
     */
    public SortedMap<Double,List<String>> bestCustomers(){
    	SortedMap<Double,List<String>> ris = new TreeMap<>((a,b) -> b.compareTo(a));
    	Set<Double> set = new HashSet<>();
    	
    	for(Entry<Integer, Customer> c : this.customers.entrySet()) {
    		set.add(this.totalCustomer(c.getKey()));
    	}
    	
    	List<String> l = new ArrayList<>();
    	for(Double d : set) {
    		l = new ArrayList<>();
    		
//    		System.out.println(d);
    		
    		for(Customer c : this.customers.values()) {
    			if(this.totalCustomer(c.getId()) == d) {
    				
//    				System.out.println(c);
    				
    				l.add(c.toString());
    			}
    		}
    		
//    		System.out.println(l);
    		
    		if(d != 0)
    			ris.put(d, l);
    		
//    		System.out.println(ris);
    	}
    	
//    	System.out.println(ris);
    	
        return ris;
    }
    
// NOT REQUIRED
//
//    /**
//     * Computes the best items by total amount of orders.
//     *  
//     * @return the classification
//     */
//    public List<String> bestItems(){
//        return null;
//    }
//    
//    /**
//     * Computes the most popular items by total quantity ordered.
//     *  
//     * @return the classification
//     */
//    public List<String> popularItems(){
//        return null;
//    }

}
