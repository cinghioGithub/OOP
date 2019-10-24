package transactions;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {
	
	private Map<String, Region> regions = new HashMap<>();
	
	private Map<String, List<String>> placePerRegions = new HashMap<>();
	
	private Map<String, Carrier> carriers = new HashMap<>();
	private Set<String> places = new HashSet<>();
	private Map<String, Request> requests = new HashMap<>();
	private Map<String, Offer> offers = new HashMap<>();
	private Map<String, Transaction> transactions = new HashMap<>();
	
//R1
	public List<String> addRegion(String regionName, String... placeNames) { 
//		this.placePerRegions.put(regionName, Stream.of(placeNames).distinct().collect(Collectors.toList()));
		
		List<String> l = new ArrayList<>();
		
		for(String str : placeNames) {				//controllo che i posti non siano gia esistenti
			if(!this.places.contains(str)) {
				l.add(str);
			}
		}
		
		this.placePerRegions.put(regionName, l);
		
		Region r = new Region(regionName, l.toArray(new String[l.size()]));
		
		this.regions.put(regionName, r);
		this.places.addAll(Stream.of(placeNames).collect(Collectors.toList()));
		
		return r.getPlaces().stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) {		
		List<String> l = new ArrayList<>();
		
		for(String str : regionNames) {
			if(this.regions.containsKey(str)) {
				l.add(str);
			}
		}
		
		Carrier c = new Carrier(carrierName, l);
		this.carriers.put(carrierName, c);
		
		return c.getRegions().stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		List<String> l = new ArrayList<String>();
		
		for(Carrier c : this.carriers.values()) {
			if(c.getRegions().contains(regionName)) {
				l.add(c.getName());
			}
		}
		
		return l.stream().sorted().collect(Collectors.toList());
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) throws TMException {
		if(this.requests.containsKey(requestId)) {
			throw new TMException();
		}
		
		if(!this.places.contains(placeName)) {
			throw new TMException();
		}
		
		Request r = new Request(requestId, placeName, productId);
		
		this.requests.put(requestId, r);
	}
	
	public void addOffer(String offerId, String placeName, String productId) throws TMException {
		if(this.offers.containsKey(offerId)) {
			throw new TMException();
		}
		
		if(!this.places.contains(placeName)) {
			throw new TMException();
		}
		
		Offer o = new Offer(offerId, placeName, productId);
		
		this.offers.put(offerId, o);
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) throws TMException {
		for(Transaction t : this.transactions.values()) {
			if(t.getOfferId().equals(offerId) || t.getRequestId().equals(requestId)) {
				throw new TMException();
			}
		}
		
		if(!this.offers.get(offerId).getProductId().equals(this.requests.get(requestId).getProduct())) {
			throw new TMException();
		}
		
//		if(!this.carriers.get(carrierName).getRegions().contains(this.requests.get(requestId).getPlace()) || !this.carriers.get(carrierName).getRegions().contains(this.offers.get(offerId).getPlaceName())) {
//			throw new TMException();
//		}
		
//		System.out.println(this.placePerRegions.get("reg1"));
//		System.out.println(this.placePerRegions.get("reg2"));
//		if(carrierName.equals("c4")) {
//			System.out.println(this.carriers.get(carrierName).getRegions());
//			System.out.println(this.placePerRegions.get("reg1"));
//			System.out.println(this.placePerRegions.get("reg7"));
//		}
		
		boolean check1 = false, check2 = false;
		for(String str : this.carriers.get(carrierName).getRegions()) {
			if(this.placePerRegions.get(str).contains(this.offers.get(offerId).getPlaceName())) {
				check1 = true;
			}
			
			if(this.placePerRegions.get(str).contains(this.requests.get(requestId).getPlace())) {
				check2 = true;
			}
		}
		
//		System.out.println(check1 + " " + check2);
		
		if(!check1 || !check2) {
			throw new TMException();
		}
		
		Transaction t = new Transaction(transactionId, carrierName, requestId, offerId);
		this.transactions.put(transactionId, t);
		this.requests.get(requestId).setTransaction(t);
		this.offers.get(offerId).setTransaction(t);
		this.carriers.get(carrierName).getTransactions().add(t);
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		this.transactions.get(transactionId).setScore(score);
		return this.transactions.get(transactionId).getScore() >= 1 && this.transactions.get(transactionId).getScore() <= 10;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		TreeMap<Long, List<String>> ris = new TreeMap<>((a,b) -> b.compareTo(a));
		Set<Long> set = new HashSet<>();
		Long c = new Long(0);
		List<String> list = new ArrayList<>();
		Map<Region, Long> map = new HashMap<>();
		
		for(Region r : this.regions.values()) {
			c = new Long(0);
			for(Transaction t : this.transactions.values()) {
				String reqId = t.getRequestId();
				String placeReq = this.requests.get(reqId).getPlace();
				if(this.placePerRegions.get(r.getName()).contains(placeReq)) {
					c++;
				}
			}
			set.add(c);
			map.put(r, c);
		}
		
//		for(Entry<Region, Long> e : map.entrySet()) {
//			System.out.println("[" + e.getKey().getName() + " -> " + e.getValue() + "]");
//		}
//		System.out.println(map);
//		System.out.println(set);
		
		for(Long l : set) {
			
//			System.out.println(l + ":");
			
			list.clear();
			for(Region r : this.regions.values()) {
				if(map.get(r) == l) {
					
//					System.out.println(r.getName()); 
					
					list.add(r.getName());
				}
			}
//			list = list.stream().sorted().collect(Collectors.toList());
			ris.put(l, list.stream().sorted().collect(Collectors.toList()));
		}
		
		
		return ris;
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		SortedMap<String, Integer> ris = new TreeMap<>();
		Integer i = new Integer(0);
		
		for(Carrier c : this.carriers.values()) {
			i = 0;
			for(Transaction t : c.getTransactions()) {
				if(t.getScore() >= minimumScore) {
					i += t.getScore();
				}
			}
			if(i!=0)
				ris.put(c.getName(), i);
		}
		
		return ris;
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		Set<String> products = new HashSet<>();
		SortedMap<String, Long> ris = new TreeMap<String, Long>();
		
		for(Transaction t : this.transactions.values()) {
			products.add(this.offers.get(t.getOfferId()).getProductId());
		}
		
		Long l =  new Long(0);
		for(String str : products) {
			l = new Long(0);
			for(Transaction t : this.transactions.values()) {
				if(this.offers.get(t.getOfferId()).getProductId().equals(str))
					l++;
			}
			if(l != 0)
				ris.put(str, l);
		}
		
		return ris;
	}
	
	
}

