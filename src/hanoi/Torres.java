package hanoi;

import javax.swing.JTextField;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

public class Torres {
	private static int mensaje, mensajeAnterior;
	Stack leftTower;
	Stack centerTower;
	Stack rigthTower;


	
	private Stack takeStack; // ----------- Pila de movimientos realizados
	private Stack placeStack;
	
	private Stack retakeStack;
	private Stack replaceStack;
	private static boolean nuevoJuego = false;
	
	private static int contMovimientos = 0;
	private static int numDiscos;
	
	//el constructor tiene que crear el juego con una serie de normas determinadas
	public Torres(int higth, PanelDibujo ventana){
		leftTower = new Stack(ventana,1);
		centerTower = new Stack(ventana,2);
		rigthTower = new Stack(ventana,3);
		
		takeStack = new Stack();
		placeStack = new Stack();
		
		retakeStack = new Stack();
		replaceStack = new Stack();
		
		for (int i = higth; i > 0; i--){
			leftTower.push(i);
//			centerTower.push(i);
//			rigthTower.push(i);
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
	public boolean mover(int inicio, int destino){
		boolean movimientoHecho = false;
		//Comprobar muy bien que no se ponga en un disco en una torre que no corresponda (por ejemplo, en la torre 4)
		if (inicio > 3 || inicio < 1 || destino > 3 || destino < 1) {
			System.out.println("error");
		} else { //torres válidas
			//Comprueba que no se pone el disco encima de un disco más pequeño
			//Además hay que tener cuidado con algunas cosas:
				//Que ocurre cuando el disco que estás cogiendo es inexistente
				//Que ocurre cuando la torre en la que vas a ponerla está vacía
			if (inicio == destino) { //cuando se hace un movimiento sobre si mismo (por ejemplo desde GUI).
				int disco = extraer(inicio);
				poner(inicio,destino,disco);
			} else if (get(inicio) < get(destino) || ((get(destino) == Integer.MAX_VALUE ) && (get(inicio) != Integer.MAX_VALUE ))) {
				contMovimientos++;
				takeStack.push(inicio);
				placeStack.push(destino);	
				borrarRehacer();
				int disco = 0;
				disco = extraer(inicio);
				poner(inicio,destino,disco);
				movimientoHecho = true;
				System.out.println();
				System.out.println("Se han realizado " + contMovimientos + " movimientos.");				
			}
		}	
		System.out.println(movimientoHecho);
		return movimientoHecho;
	}
	
	public void mover (int inicio, int destino, boolean roll){ // Este es el movimiento propio de los Deshacer y Rehacer
		
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

		if(placeStack.get() != Integer.MAX_VALUE && takeStack.get() != Integer.MAX_VALUE){
			
			int inicio = placeStack.pop();
			int destino = takeStack.pop();
			
			replaceStack.push(destino);
			retakeStack.push(inicio);
			
			mover(inicio, destino, true);
		
			contMovimientos--;
			
		}
		
		mensaje = 0;
		
	}
	
	public void rehacer(){
		
		if(replaceStack.get() != Integer.MAX_VALUE && retakeStack.get() != Integer.MAX_VALUE){
		
			int inicio = replaceStack.pop();
			int destino = retakeStack.pop();
			
			placeStack.push(destino);
			takeStack.push(inicio);
			
			mover(inicio, destino, true);
			
			contMovimientos++;
			
		}
		
		mensaje = 0;
		
	}
	
	public void borrarRehacer(){
		
		while(replaceStack.get() != Integer.MAX_VALUE){
			
			replaceStack.pop();
			
		}
		
		while(retakeStack.get() != Integer.MAX_VALUE){
			
			retakeStack.pop();
			
		}
		
	}

	public void show(){
		leftTower.recorrer();
		centerTower.recorrer();
		rigthTower.recorrer();
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
				mover(origen,destino);
				mensajeAnterior = 0;
				mensaje = 0;
			}
		} else if (mensaje == 7){
			deshacer();
		} else if (mensaje == 8) {
			rehacer();
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
		
		//Se comprueba que evento es
		if (e == 1 ) { //Evento click
			leftTower.comprobarClick(x,y);
			centerTower.comprobarClick(x, y);
			rigthTower.comprobarClick(x, y);
		} else if (e == 2) { //Evento presionar
			leftTower.comprobarPresion(x,y);
			centerTower.comprobarPresion(x, y);
			rigthTower.comprobarPresion(x, y);
		} else if (e == 3){ //Evento arrastrar
			boolean yaHaMovido;
			yaHaMovido = leftTower.arrastra(x,y);
			if (!yaHaMovido) {
				yaHaMovido = centerTower.arrastra(x, y);
			}
			if (!yaHaMovido) {
				rigthTower.arrastra(x, y);
			}
		} else if (e == 4 || e == 5){ //Evento liberar y sacar el cursor del panel
			int origen, destino;
			origen = destino = 0;
			if (leftTower.liberar(x,y)) {
				origen = 1;
			} else if (	centerTower.liberar(x, y)){
				origen = 2;
			} else if (rigthTower.liberar(x, y)){ //importante no poner else para que se ejecute el código
				origen = 3;
			}
			if (240 >= x) {
				destino = 1;
			} else if (x > 240 && x < 460){
				destino = 2;
			} else {
				destino = 3;
			}
			System.out.println(origen+"_"+destino);
			if (origen != 0 && destino != 0) {
				if(!mover(origen,destino)){
					mover(origen,origen);
				}
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
	
	public static int getNumMovimientos(){
		return contMovimientos;
	}
	
	public static void setNuevoJuego(boolean b){
		nuevoJuego = b;
	}
	
	public boolean getNuevoJuego(){
		return nuevoJuego;
	}
	
	public static void main(String Args[]){
		nuevoJuego = true;
		MiFrame frame = new MiFrame("Torres Hanoi");
		PanelDibujo ventana = frame.getVentana();	
		numDiscos = 5;
		while (nuevoJuego){			
			Torres torres = new Torres(numDiscos,ventana);
			ventana.setTorresHanoi(torres);
			frame.setNumDiscos(numDiscos);
			frame.paintAll(frame.getGraphics());
			do{
				nuevoJuego = false;
				try {
					torres.show();
					ventana.repaint();
					//COn consola:
					//int origen = UtilsUI.getConsoleInt("De que torre quieres sacarlo:");
					//int destino = UtilsUI.getConsoleInt("En que torre quieres ponerlo:");
					//torres.mover(origen, destino);	
					
					//Con GUI:
					torres.procesarBotones();
					frame.setNumMovimientos(torres.getNumMovimientos());
//					nuevoJuego = torres.getNuevoJuego();
					if (nuevoJuego){
//						numDiscos = frame.getNumDiscos();
						System.out.println("__");
					}
					Thread.sleep(205); //28fps
				} catch (Exception e){
					//
				}
			}while (!nuevoJuego);
		}
	}

	public static void setNumDiscos(int num) {
		numDiscos = num;
	}

	public static int getNumDiscos() {
		return numDiscos;
	}
}
