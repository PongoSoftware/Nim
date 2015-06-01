package utils;

public class Stack {
	private Nodo raiz;
	public Stack(){
		raiz = null;
	}
	
	class Nodo {
		int dato;
		Nodo sig;
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
	}
	
	public int pop(){
		if (raiz!= null){
				int informacion = raiz.dato;
				raiz=raiz.sig;
				return informacion;
		} else {
			return Integer.MAX_VALUE;
			
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
