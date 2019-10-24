package it.polito.oop.base;

public class Counter {
	
	private int value;
	
	public Counter reset(){
		value=0; return this;
	}
	
	public Counter increment(int by){
		this.value+=by; return this;
	}
	
	public Counter decrement(int by){
		this.value-=by; return this;
	}
	
	public Counter print(){
		System.out.println(value);
		return this;
	}
	
	public Counter andThen() {
		return this;
	}
	public Counter then() {
		return this;
	}
	public Counter afterThat() {
		return this;
	}
	public Counter andAlso() {
		return this;
	}
	//----------------------------------------------
	// Esempio di uso
	//
	public static void main(String[] args) {
		Counter cnt = new Counter();
		
		cnt.reset().andThen().print()
		   .then().increment(10).
		   afterThat().print().then()
		   .decrement(7).andAlso().print();
		
		// piu' compatto e leggibile di
		
		cnt.reset();
		cnt.print();
		cnt.increment(10);
		cnt.print();
		cnt.decrement(7);
		cnt.print();
	}
	
}
