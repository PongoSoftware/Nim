package hanoi;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

public class HanoiAIJose {

	int[] p = new int[3];
	int origen, destino, numTorres, intentosPasos;
	Torres torres;
	boolean error;
	public HanoiAIJose(Torres torres){
		this.torres = torres;		
		origen = 1;
		numTorres = 3;
		intentosPasos = 0;
		error = false;
	}
	
	public void paso(){
		destino = calcularDestino(torres.get(origen),origen);
		if (intentosPasos < numTorres) {
			if(!torres.mover(origen, destino)){
				intentosPasos++;
				origenAumentar();
				paso();
			} else {
				intentosPasos = 0;
			}
		} else {
			error = true;
		}
	}
	
	public int calcularDestino(int disco, int origen){
		int destino;
		if (esPar(disco)) {
			destino = origen+1;
		} else {
			destino = origen+2;
		}
		if (destino > numTorres){
			destino -= numTorres;
		}
		return destino;
	}
	
	public boolean esPar(int num){
		boolean b = false;
		if ((num % 2) == 0) {
			b = true;
		}

		return b;
	}
	
	public void origenAumentar(){
		origen++;
		if (origen > numTorres){
			origen -= numTorres;
		}
		System.out.println("Origen Aumentado"+origen);
	}
	/*
	public static void main(String[] args) {
//		conTexto();
		conGraficos();
	}
	
	public static void conGraficos(){
		//
		boolean nuevoJuego, repetirBucle;
		int numDiscos, contMovimientos;
		repetirBucle = true;
		//
		
		nuevoJuego = true;
		MiFrame frame = new MiFrame("Torres Hanoi");
		PanelDibujo ventana = frame.getVentana();	
		numDiscos = 5;
		while (nuevoJuego){	
			contMovimientos = 0;
			Torres torres = new Torres(numDiscos,ventana);
			HanoiAIJose ai = new HanoiAIJose(torres);
			ventana.setTorresHanoi(torres);
			frame.setNumDiscos(numDiscos);
			frame.paintAll(frame.getGraphics());
			do{
				nuevoJuego = false;
				try {
//					torres.show();
					ventana.repaint();
		
					//Con GUI:
					torres.procesarBotones();
					frame.setNumMovimientos(torres.getNumMovimientos());
					
					ai.paso();
					if((torres.getLastRow(1) == 0 || torres.getLastRow(1) == Integer.MAX_VALUE ) && 
						(torres.getLastRow(2) == 0 || torres.getLastRow(2) == Integer.MAX_VALUE )){
						repetirBucle = false;
					}
					
					Thread.sleep(1000); //36 ~= 28fps
				} catch (Exception e){
					//
				}
			}while (!nuevoJuego && repetirBucle);
			ventana.repaint();
		}
	} */
	
	public boolean error(){
		return error;
	}
/*
	public static void conTexto(){ //¡Funciona!
		Torres torres = new Torres(5);
		HanoiAIJose ai = new HanoiAIJose(torres);
		System.out.println("==1__________");
		boolean repetirBucle = true;
		while(repetirBucle){
			torres.show();		
			ai.paso();
			System.out.println(torres.getLastRow(1)+"_"+torres.getLastRow(2));
			if((torres.getLastRow(1) == 0 || torres.getLastRow(1) == Integer.MAX_VALUE ) && 
				(torres.getLastRow(2) == 0 || torres.getLastRow(2) == Integer.MAX_VALUE ) &&
				!ai.error()){
				repetirBucle = false;
			}
		}
		torres.show();
	} */
}
