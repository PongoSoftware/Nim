package hanoi;

import hanoi.gui.PanelDibujo;

import java.awt.Color;
import java.awt.Graphics;

public class Stack {
	private Nodo raiz;
	private int lastRow = 0;
	private int numTorre;
	private PanelDibujo ventana;
	private static int posex, posey;
	
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
		//Variables gráficas
		private static final int multiplicador = 22; //Multiplicador utilizado en el calculo de posiciones
		private static final int xinicial = 75;
		private static final int  yinicial = 280;
		private int x, y, width, height, xwidth, yheight;
		private Color color;
		
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
			g.setColor(color);
			g.fillRect(x,y,width, height);		
		}
		
		public void calcularPosiciones(){
			//calcular posiciones en pantalla			
			width = dato * multiplicador;
			height = (int) (multiplicador * 0.8);
			x = xinicial + (numTorre-1) * multiplicador*10 + (100 - width) / 2;
			y = yinicial - (piso * multiplicador);
			xwidth = x + width;
			yheight = y + height;
			color = new Color(dato*10,dato*10,dato*10);
		}
		
		public void setPosicion(int x, int y){
			this.x = x;
			this.y = y;
			this.xwidth = this.x + this.width;
			this.yheight = this.y + this.height;
		}

		public boolean comprobarPosicion(int x, int y){
			if (this.x <= x && this.width >= x){
				if (this.y <= y && this.height >= y){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}			
		}		
	}
	
	public void push(int d){
		if (d != 0) {
			Nodo nuevo;
			nuevo = new Nodo();
			nuevo.dato = d;
			if (raiz==null){
				nuevo.sig = null;
				raiz = nuevo;	
				//System.out.print("He hecho un push "+nuevo.dato);
			} else {
				nuevo.sig = raiz;
				raiz = nuevo;
			}
			
			lastRow++;
			nuevo.piso = lastRow;
			nuevo.torre = numTorre;
			nuevo.calcularPosiciones();
			
			nuevo.inicializado = true;
		}
	}
	
	public int pop(){
		if (raiz!= null){
				int informacion = raiz.dato;
				raiz=raiz.sig;
				lastRow--;
				return informacion;
		} else {
			//return Integer.MAX_VALUE;
			return 0;
		}		
	}
	
	public int get(){
		int informacion = Integer.MAX_VALUE;
		if (raiz!=null){
			informacion = raiz.dato;
		}
		return informacion;
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
				//System.out.println("TORRE "+numTorre+" Pinta el elemento "+elemento.dato+"_numero de pisos de la torre"+lastRow);
			}
		} catch (Exception e) {
			//System.out.println("No hay elementos en la torre "+numTorre);
		}
	}
	
	/*
	 * Recorre gráficamente la pila
	 * Se llama desde PanelDibujo
	 */
	public void recorrerGrafico(Graphics g){
		try{
			Nodo elemento = new Nodo();
			while(elemento.hasNext()){
				elemento = next(elemento);
				elemento.comprobarPosicion(posex, posey);
				elemento.calcularPosiciones();
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

	public void recibePosicion(int x, int y) {
		posex = x;
		posey = x;		
	}
}
