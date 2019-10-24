package esempi.nested;

public class EsStack {

	public static void main(String[] args) {
		
		Stack s = new Stack();
		
		s.push(2);
		s.push(3);
		
		System.out.println(s.pop());  // 3
		System.out.println(s.pop());  // 2
		
		double x = 1.0/3.0;
		
		System.out.println(String.valueOf(x));
		System.out.println(String.format("%.2f",x));
		
		String valore = "var=5";
		
		String nome = valore.substring(0,3); // da indice 0 a indice 2
		System.out.println("Nome: " + nome);
		
		String valStr = valore.substring(4); // da indice 4 fino alla fine
		System.out.println("Valore: " + valStr);
		
		int val = Integer.parseInt(valStr);
		
		
		valore = "variabile=55";
		int pos = valore.indexOf("=");
		
		 nome = valore.substring(0,pos); // da indice 0 a indice pos-1
		System.out.println("Nome: " + nome);
		
		 valStr = valore.substring(pos+1); // da indice pos+1 fino alla fine
		System.out.println("Valore: " + valStr);
		
		
		String[] elementi = valore.split("=");
		System.out.println("Nome: " + elementi[0]);
		System.out.println("Valore: " + elementi[1]);
		
		

	}

}
