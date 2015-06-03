package hanoi;

import hanoi.gui.PanelDibujo;

import java.awt.Color;
import java.awt.Graphics;

public class Stack {
	private Nodo raiz;
	private int lastRow = 0;
	private int numTorre;
	private PanelDibujo ventana;
	
	public Stack(PanelDibujo ventana, int i){
		raiz = null;
		this.numTorre = i;
		this.ventana = ventana;
	}
	
	public class Nodo {
		int dato;
		int piso;
		Nodo sig;
		boolean inicializado = false;
		public int torre;
		
		public boolean hasNext(){
			if (!inicializado){
				return true;
			} else {
				if (sig == null){
					return false;
				} else {
					return true;
				}
			}			
		}
		public Nodo next(){
			return sig;
		}
		public void paint(Graphics g) {
			int multiplicador = 24;
			int xi = 100;
			int yi = 280;
			int width = dato * multiplicador;
			int heigth = (int) (multiplicador * 0.8);
			int x = xi + (numTorre-1) * multiplicador*10 + (100 - width) / 2;
			int y = yi - (piso * multiplicador);
			
			dameColor(g);
			g.fillRect(x,y,width, heigth);		
		}
		
		public void dameColor(Graphics g){
			Color color;
			
				color = new Color(dato*10,dato*10,dato*10);
			g.setColor(color);
				
		}
		
	}
	
	
	
	public void push(int d){
		Nodo nuevo;
		nuevo = new Nodo();
		nuevo.dato = d;
		if (raiz==null){
			nuevo.sig = null;
			raiz = nuevo;	
			System.out.print("pus");
		} else {
			nuevo.sig = raiz;
			raiz = nuevo;
		}
		
		lastRow++;
		nuevo.piso = lastRow;
		nuevo.torre = numTorre;
		
		nuevo.inicializado = true;
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
	
	public Nodo next(Nodo elemento){
		if (!elemento.inicializado){
			elemento.sig = raiz;
		}
		
		return elemento.sig;
	}
	
	public void recorrer(){
		try{
			Nodo elemento = new Nodo();
			while(elemento.hasNext()){
				elemento = next(elemento);
				System.out.println("TORRE "+numTorre+" Pinta el elemento "+elemento.dato+"_numero de pisos de la torre"+lastRow);
			}
		} catch (Exception e) {
			System.out.println("No hay elementos en la torre "+numTorre);
		}
	}
	
	public void recorrerGrafico(Graphics g){
		try{
			Nodo elemento = new Nodo();
			while(elemento.hasNext()){
				elemento = next(elemento);
				elemento.paint(g);
			}
		} catch (Exception e) {}
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
}
