package hanoi;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

public class Torres {
	private static int mensaje, mensajeAnterior;
	Stack leftTower;
	Stack centerTower;
	Stack rigthTower;
	public Torres(int higth, PanelDibujo ventana){
		leftTower = new Stack(ventana,1);
		centerTower = new Stack(ventana,2);
		rigthTower = new Stack(ventana,3);
		
		for (int i = higth; i > 0; i--){
			leftTower.push(i);
			//centerTower.push(i);
			//rigthTower.push(i);
		}
		ventana.setTorre1(leftTower);
		ventana.setTorre2(centerTower);
		ventana.setTorre3(rigthTower);
		
	}
	
	private int extraer(int inicio){
		int disco = 0;
		switch(inicio){
		case 1:
			disco = leftTower.pop();
			break;
		case 2:
			disco = centerTower.pop();
			break;
		case 3:
			disco = rigthTower.pop();
			break;
		}
		return disco;
	}
	
	public void poner(int inicio, int destino, int disco){
		switch(destino){
		case 1:
			leftTower.push(disco);
			break;
		case 2:
			centerTower.push(disco);
			break;
		case 3:
			rigthTower.push(disco);
			break;
		}		
	}
	
	public int get(int torre){
		int valor = Integer.MAX_VALUE;
		switch(torre){
		case 1:
			valor = leftTower.get();
			break;
		case 2:
			valor = centerTower.get();
			break;
		case 3:
			valor = rigthTower.get();
			break;
		}	
		return valor;
	}
		
	/**
	 * Ejecuta el movimiento entre las pilas
	 * @param inicio
	 * @param destino
	 */
	public void mover(int inicio, int destino){
		//Comprobar muy bien que no se ponga en un disco en una torre que no corresponda (por ejemplo, en la torre 4)
		if (inicio > 3 || inicio < 1 || destino > 3 || inicio < 1) {
			System.out.println("error");
		} else { //torres válidas
			//Comprueba que no se pone el disco encima de un disco más pequeño
			//Además hay que tener cuidado con algunas cosas:
				//Que ocurre cuando el disco que estás cogiendo es inexistente
				//Que ocurre cuando la torre en la que vas a ponerla está vacía
			if (get(inicio) > get(destino) || ((get(destino) == Integer.MAX_VALUE ) && (get(inicio) != Integer.MAX_VALUE ))) {
				int disco = 0;
				disco = extraer(inicio);
				poner(inicio,destino,disco);
				System.out.println("_______");
			}
		}		
	}

	public void show(){
		leftTower.recorrer();
		centerTower.recorrer();
		rigthTower.recorrer();
	}
	
	public static PanelDibujo crearVentana(){
		MiFrame frame = new MiFrame("Torres Hanoi");
		PanelDibujo ventana = frame.getVentana();		
		return ventana;
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
		//Opciones de quitar y poner
		if (mensajePoner()){
			if (mensajeAnteriorQuitar()){
				
				int origen = mensajeAnterior;
				int destino = deMensajeANumPila(mensaje);
				System.out.println(mensaje+"_"+destino);
				System.out.println("movimiento.."+origen+"-"+destino);
				mover(origen,destino);
				mensajeAnterior = 0;
				mensaje = 0;
			}
			//Otras ociones
		} else if (mensaje == 7){
		} else if (mensaje == 8) {
		}
	}
	
	/**
	 * Recibe mensajes del ratón desde Swing
	 * 
	 * 1 Click
	 * 2 Muevo
	 * 3 Arrastro
	 * 4 Libero
	 * 5 Ratón sale pantalla
	 * @param e
	 * @param x
	 * @param y
	 */
	public void recibirRaton(int e, int x, int y) {
		if (e == 1 ) {
//			System.out.println("click");
			leftTower.comprobarClick(x,y);
			centerTower.comprobarClick(x, y);
			rigthTower.comprobarClick(x, y);
		} else if (e == 2) {
			leftTower.comprobarPresion(x,y);
			centerTower.comprobarPresion(x, y);
			rigthTower.comprobarPresion(x, y);
		} else if (e == 3){
			boolean yaHaMovido;
			yaHaMovido = leftTower.arrastra(x,y);
			if (!yaHaMovido) {
				yaHaMovido = centerTower.arrastra(x, y);
			}
			if (!yaHaMovido) {
				rigthTower.arrastra(x, y);
			}
		} else if (e == 4 || e == 5){
			leftTower.liberar(x,y);
			centerTower.liberar(x, y);
			rigthTower.liberar(x, y);
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
	
	private int deMensajeANumPila(int mensaje){
		int pila = 0;
		if (mensaje == 4) {
			pila = 1;
		} else if (mensaje == 5) {
			pila = 2;
		} else if (mensaje == 6) {
			pila = 3;
		}
		
		return pila;
	}
	
	public static void main(String Args[]){
		PanelDibujo ventana = crearVentana();
		Torres torres = new Torres(9,ventana);
		ventana.setTorresHanoi(torres);
		ventana.repaint();
		while (true){
			try {
				torres.show();
				ventana.repaint();
				//COn consola:
				//int origen = UtilsUI.getConsoleInt("De que torre quieres sacarlo:");
				//int destino = UtilsUI.getConsoleInt("En que torre quieres ponerlo:");
				//torres.mover(origen, destino);	
				
				//Con GUI:
				torres.procesarBotones();
				Thread.sleep(205); //28fps
			} catch (Exception e){
				//
			}
		}
	}
}