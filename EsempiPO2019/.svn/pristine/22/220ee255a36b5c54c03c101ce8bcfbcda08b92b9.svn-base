package esempi.nested;

/**
 * Realizza uno stack di interi
 * 
 * @author mtk
 *
 */
public class Stack {
	
	private class Elemento{
		int valore;
		Elemento prossimo;
		
		Elemento(int v, Elemento p){
			valore = v;
			prossimo = p;
		}

		Elemento(int v){
			valore = v;
			prossimo = cima;
		}

		public int getValore() {
			return valore;
		}
		
		public Elemento getProssimo() {
			return prossimo;
		}
		
		public int rimuoviDalloStack() {
			cima = prossimo;
			return valore;
		}
	}
	/*
	 +----------------------+    +----------------------+
	 | valore (3) | prox *--+--->| valore (2) | prox *--+---> null
	 +----------------------+    +----------------------+
	 
	 */
	
	private Elemento cima=null; // top

	public void push(int nuovoValore) {
		
		//Elemento e = new Elemento(nuovoValore, cima);
		Elemento e = new Elemento(nuovoValore);
		
		cima = e;
		
	}
	
	public int pop() {
		
//		int risultato = cima.getValore();
//		
//		cima = cima.getProssimo();
//		
//		return risultato;
		
		return cima.rimuoviDalloStack();
	}
	
}
