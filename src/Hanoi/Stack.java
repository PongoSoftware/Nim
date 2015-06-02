package Hanoi;

public class Stack {
	private Nodo raiz;
	private int lastRow = 0;
	public Stack(){
		raiz = null;
	}
	
	class Nodo {
		int dato;
		Nodo sig;
		
		public boolean hasNext(){
			if (sig == null){
				return false;
			} else {
				return true;
			}
		}
		
		public void next(){
			try {
				Object clone = sig.clone();
				this = clone;
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
	public void push(int d){
		Nodo nuevo;
		nuevo = new Nodo();
		nuevo.dato = d;
		if (raiz==null){
			nuevo.sig = null;
			raiz = nuevo;	
		} else {
			nuevo.sig = raiz;
			raiz = nuevo;
		}
		lastRow++;
	}
	
	public int pop(){
		
		if (raiz!= null){
				int informacion = raiz.dato;
				raiz=raiz.sig;
				lastRow = lastRow--;
				return informacion;
		} else {
			return Integer.MAX_VALUE;
		}
		
	}
	
	public void recorrer(){
		Nodo elemento = raiz;
		while(elemento.hasNext()){
			elemento.next();
//			elemento.pintar();
			System.out.println("Pinta el elemento "+elemento.dato+"_"+lastRow);
		}
	}
	

	
	public void print(){
		Nodo aux = raiz;
				System.out.println("Contenido de la pila:");
		while (aux!=null){
			System.out.print(aux.dato+"->");
			aux=aux.sig;
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		Stack pila1= new Stack();
		pila1.push(40);
		pila1.push(16);
		pila1.push(25);
		pila1.print();
		System.out.println("Extraeremos de la pila:"+pila1.pop());
		pila1.print();
	}
}
