package groups;

import java.util.ArrayList;
import java.util.List;

public class Bid {
	
	private Integer price;
	private Group group ;
	private Fornitore f;
	

	public Bid(Integer price, Fornitore f) {
		this.price = price;
		this.f = f;
	}


	public Fornitore getF() {
		return f;
	}


	public Integer getPrice() {
		return price;
	}


	public Group getGroups() {
		return group;
	}
	
	public void setGroups(Group g) {
		group = g;
	}
	
}
