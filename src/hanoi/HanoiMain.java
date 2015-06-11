package hanoi;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

public class HanoiMain {
	
	private static int mensaje, mensajeAnterior;
	private static boolean nuevoJuego;
	private static boolean autoresolver = false;
	private boolean bloquearMovimientos;
	
	private static int numDiscos;
	Torres torres;
	
	HanoiMain(){
		nuevoJuego = true;
		MiFrame frame = new MiFrame("Torres Hanoi");
		PanelDibujo ventana = frame.getVentana();	
		numDiscos = 5;
		int refrescoNormal = 36;  //36 ~= 28fps
		int refrescoAutoresolver = 1000;
		int refresco = refrescoNormal;
		while (nuevoJuego){	
			//Inicializamos nuevos juegos;
			torres = new Torres(numDiscos,ventana);
			HanoiAIJose ai = new HanoiAIJose(torres);
			ventana.setControllador(this);
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
						setBloquearMovimientos(true);
					} else {
						if (autoresolver){
							setBloquearMovimientos(true);
							refresco = refrescoAutoresolver;
							ai.paso();
						} else {
							setBloquearMovimientos(false);
							refresco = refrescoNormal;
						}
					}
					procesarBotones();
					frame.setNumMovimientos(torres.getContMovimientos());
					Thread.sleep(refresco);
				} catch (Exception e){
					//
				}
			}while (!nuevoJuego);
		}
	}
	
	/** 
	 * Recibe un mensaje desde la interfaz gráfica
	 * @param mensajeRecibido
	 */
	public static void recibirMensaje(int mensajeRecibido) {
		
		mensajeAnterior = mensaje; //mensaje anteriormente recibido
		mensaje = mensajeRecibido; //mensaje actual obtenido
	}
	
	/**
	 * Procesa los mensajes recibidos
	 * 
	 * Lista de códigos:
	 * 1 Quitar de torre 1
	 * 2 Quitar de torre 2
	 * 3 Quitar de torre 3
	 * 4 Poner en torre 1
	 * 5 Poner en torre 2
	 * 6 Poner en torre 3
	 * 7 Pulsar el botón atrás
	 * 8 Pulsar el botón adelante
	 */
	public void procesarBotones(){
		if(!bloquearMovimientos) {
			//Opciones de quitar y poner
			if (mensajePoner()){
				if (mensajeAnteriorQuitar()){
					
					int origen = mensajeAnterior;
					int destino = torres.deMensajeANumPila(mensaje);
					torres.mover(origen,destino);
					mensajeAnterior = 0;
					mensaje = 0;
				}
			} else if (mensaje == 7){
				torres.deshacer();
				mensaje = 0;
			} else if (mensaje == 8) {
				torres.rehacer();
				mensaje = 0;
			}
		}
	}

	/**
	 * Comprueba si se ha recibido un mensaje de poner
	 * 
	 * @return
	 */
	private boolean mensajePoner() {
		if (mensaje < 4 || mensaje > 6){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Comprueba si el mensaje anterior era de quitar un disco
	 * 
	 * @return
	 */
	private boolean mensajeAnteriorQuitar() {
		if (mensajeAnterior < 1 || mensajeAnterior > 3){
			return false;
		} else {
			return true;
		}
	}
	

	
	/**
	 * Recibe mensajes del ratón desde Swing
	 * 
	 * 1 Click
	 * 2 Presion
	 * 3 Arrastro
	 * 4 Libero
	 * 5 Ratón sale pantalla
	 * @param e
	 * @param x
	 * @param y
	 */
	public void recibirRaton(int e, int x, int y) {
		if(!bloquearMovimientos){
			//Se comprueba que evento es
			if (e == 1 ) { //Evento click
				torres.getLeftTower().comprobarClick(x,y);
				torres.getCenterTower().comprobarClick(x, y);
				torres.getRightTower().comprobarClick(x, y);
			} else if (e == 2) { //Evento presionar
				torres.getLeftTower().comprobarPresion(x,y);
				torres.getCenterTower().comprobarPresion(x, y);
				torres.getRightTower().comprobarPresion(x, y);
			} else if (e == 3){ //Evento arrastrar
				boolean yaHaMovido;
				yaHaMovido = torres.getLeftTower().arrastra(x,y);
				if (!yaHaMovido) {
					yaHaMovido = torres.getCenterTower().arrastra(x, y);
				}
				if (!yaHaMovido) {
					torres.getRightTower().arrastra(x, y);
				}
			} else if (e == 4 || e == 5){ //Evento liberar y sacar el cursor del panel
				int origen, destino;
				origen = destino = 0;
				if (torres.getLeftTower().liberar(x,y)) {
					origen = 1;
				} else if (	torres.getCenterTower().liberar(x, y)){
					origen = 2;
				} else if (torres.getRightTower().liberar(x, y)){ //importante no poner else para que se ejecute el código
					origen = 3;
				}
				if (240 >= x) {
					destino = 1;
				} else if (x > 240 && x < 460){
					destino = 2;
				} else {
					destino = 3;
				}
	//			System.out.println(origen+"_"+destino);
				if (origen != 0 && destino != 0) {
					if(!torres.mover(origen,destino)){
						torres.mover(origen,origen);
					}
				}
			}
		}
		
	}
	
	public static void switchAutorresolver() {
		if(autoresolver){
			autoresolver = false;
		} else {
			autoresolver = true;
		}
		
	}

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
	
	public void setBloquearMovimientos(boolean b){
		bloquearMovimientos = b;
	}

	
	public static void main(String Args[]){
		HanoiMain main = new HanoiMain();
	}
}
