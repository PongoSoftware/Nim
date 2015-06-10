package hanoi;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

public class HanoiMain {

	private static boolean nuevoJuego;
	private static boolean autoresolver = false;
	private static int numDiscos;

	public static void setNuevoJuego(boolean b){
		nuevoJuego = b;
	}
	
	public static void setAutoresolver(boolean b) {
		autoresolver = b;		
	}
	

	
	public static void setNumDiscos(int num) {
		numDiscos = num;
	}

	public static int getNumDiscos() {
		return numDiscos;
	}
	
	public static void main(String Args[]){
		nuevoJuego = true;
		MiFrame frame = new MiFrame("Torres Hanoi");
		PanelDibujo ventana = frame.getVentana();	
		numDiscos = 5;
		int refrescoNormal = 36;  //36 ~= 28fps
		int refrescoAutoresolver = 1000;
		int refresco = refrescoNormal;
		while (nuevoJuego){	
			//Inicializamos nuevos juegos;
			Torres torres = new Torres(numDiscos,ventana);
			HanoiAIJose ai = new HanoiAIJose(torres);
			ventana.setTorresHanoi(torres);
			frame.setNumDiscos(numDiscos);
			frame.setMinMovimientos(torres.getMinMovimientos());
			frame.paintAll(frame.getGraphics());
			do{
				nuevoJuego = false;
				try {
					//Con GUI:
					ventana.repaint();
//					System.out.println(autoresolver);
					if(torres.esFinJuego()){
						
					} else {
						if (autoresolver){
							torres.setBloquearMovimientos(true);
							refresco = refrescoAutoresolver;
							ai.paso();
						} else {
							torres.setBloquearMovimientos(false);
							refresco = refrescoNormal;
						}
					}
					torres.procesarBotones();
					frame.setNumMovimientos(torres.getNumMovimientos());
					Thread.sleep(refresco);
				} catch (Exception e){
					//
				}
			}while (!nuevoJuego);
		}
	}

	public static void switchAutorresolver() {
		if(autoresolver){
			autoresolver = false;
		} else {
			autoresolver = true;
		}
		
	}
}
