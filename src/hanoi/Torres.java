package hanoi;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Torres {
	private static int mensaje, mensajeAnterior;
	Stack leftTower;
	Stack centerTower;
	Stack rigthTower;
	
	private Stack takeStack; // ----------- Pila de movimientos realizados
	private Stack placeStack;
	
	private Stack retakeStack;
	private Stack replaceStack;
	
	private static int contMovimientos = 0;
	
	public Torres(int higth, PanelDibujo ventana){
		leftTower = new Stack(ventana,1);
		centerTower = new Stack(ventana,2);
		rigthTower = new Stack(ventana,3);
		
		takeStack = new Stack();
		placeStack = new Stack();
		
		for (int i = higth; i > 0; i--){
			leftTower.push(i);
			//centerTower.push(i);
			//rigthTower.push(i);
		}
		ventana.setTorre1(leftTower);
		ventana.setTorre2(centerTower);
		ventana.setTorre3(rigthTower);
		
	}
	
	private int extraer(int inicio){ // No es útli de momento, porque registra todos los movimientos, incluso los de 
									// rollback D:
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
		if (inicio > 3 || inicio < 1 || destino > 3 || destino < 1) {
			System.out.println("error");
		} else { //torres válidas
			//Comprueba que no se pone el disco encima de un disco más pequeño
			//Además hay que tener cuidado con algunas cosas:
				//Que ocurre cuando el disco que estás cogiendo es inexistente
				//Que ocurre cuando la torre en la que vas a ponerla está vacía
			if (get(inicio) < get(destino) || ((get(destino) == Integer.MAX_VALUE ) && (get(inicio) != Integer.MAX_VALUE ))) {
				takeStack.push(inicio);
				placeStack.push(destino);				
				int disco = 0;
				disco = extraer(inicio);
				poner(inicio,destino,disco);
				System.out.println();
				System.out.println("Se han realizado " + contMovimientos + " movimientos.");				
				System.out.println("_______");
			}
		}
		
	}
	
	public void mover (int inicio, int destino, boolean roll){
		
		//Comprobar muy bien que no se ponga en un disco en una torre que no corresponda (por ejemplo, en la torre 4)
				if (inicio > 3 || inicio < 1 || destino > 3 || destino < 1) {
					System.out.println("error");
				} else { //torres válidas
					//Comprueba que no se pone el disco encima de un disco más pequeño
					//Además hay que tener cuidado con algunas cosas:
						//Que ocurre cuando el disco que estás cogiendo es inexistente
						//Que ocurre cuando la torre en la que vas a ponerla está vacía
					if (get(inicio) < get(destino) || ((get(destino) == Integer.MAX_VALUE ) && (get(inicio) != Integer.MAX_VALUE ))) {				
						int disco = 0;
						disco = extraer(inicio);
						poner(inicio,destino,disco);
						System.out.println();
						System.out.println("Se han realizado " + contMovimientos + " movimientos.");				
						System.out.println("_______");
					}
				}
				
		
	}
	
	public void deshacer(){
		
		int inicio = placeStack.pop();
		int destino = takeStack.pop();
		
		replaceStack.push(inicio);
		retakeStack.push(destino);
		
		mover(inicio, destino, true);
		
		mensaje = 0;
		
	}
	
	public void rehacer(){
		
		int inicio = replaceStack.pop();
		int destino = retakeStack.pop();
		
		placeStack.push(inicio);
		takeStack.push(destino);
		
		mover(inicio, destino, true);
		
		mensaje = 0;
		
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
	 * 
	 */
	public void procesarBotones(){
		//Opciones de quitar y poner
		if (mensajePoner()){
			if (mensajeAnteriorQuitar()){
				
				contMovimientos++;
				int origen = mensajeAnterior;
				int destino = deMensajeANumPila(mensaje);
				System.out.println(mensaje+"_"+destino);
				System.out.println("movimiento.."+origen+"-"+destino);
				mover(origen,destino);
				mensajeAnterior = 0;
				mensaje = 0;
			}
			//Otras ociones
		} else {
			switch(mensaje){
			case 7:
				
				deshacer();
				
				break;
			case 8:
				break;
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
	
	
	public static int movimientosRealizados(){
		
		return contMovimientos;
		
	}
	
	public static void main(String Args[]){
		PanelDibujo ventana = crearVentana();
		Torres torres = new Torres(9,ventana);
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
				Thread.sleep(35);
			} catch (Exception e){
				//
			}
		}
	}
}
