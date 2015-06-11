package hanoi;

import javax.swing.JTextField;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

public class Torres { 
	StackHanoi leftTower;
	StackHanoi centerTower;
	StackHanoi rightTower;
	
	private StackHanoi takeStack; // ----------- Pila de movimientos realizados
	private StackHanoi placeStack;
	
	private StackHanoi retakeStack;
	private StackHanoi replaceStack;
	
	
	private static int contMovimientos = 0;
	private int minMovimientos;
	
	//el constructor tiene que crear el juego con una serie de normas determinadas
	public Torres(int higth){
		minMovimientos = (int) (Math.pow(2,higth) - 1);
		contMovimientos = 0;
		
		leftTower = new StackHanoi(1);
		centerTower = new StackHanoi(2);
		rightTower = new StackHanoi(3);
		
		takeStack = new StackHanoi();
		placeStack = new StackHanoi();
		
		retakeStack = new StackHanoi();
		replaceStack = new StackHanoi();
		
		for (int i = higth; i > 0; i--){
			leftTower.push(i);
//			centerTower.push(i);
//			rigthTower.push(i);
		}
	}
	public Torres(int higth, PanelDibujo ventana){
		this(higth);
		ventana.setTorre1(leftTower);
		ventana.setTorre2(centerTower);
		ventana.setTorre3(rightTower);
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
			disco = rightTower.pop();
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
			rightTower.push(disco);
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
			valor = rightTower.get();
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
//				System.out.println();
//				System.out.println("Se han realizado " + contMovimientos + " movimientos.");				
			}
		}	
//		System.out.println(movimientoHecho);
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
//						System.out.println();
//						System.out.println("Se han realizado " + contMovimientos + " movimientos.");				
//						System.out.println("_______");
			}
		}
				
		
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
		rightTower.recorrer();
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
	}
	protected int deMensajeANumPila(int mensaje){
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
	
	public static int getContMovimientos(){
		return contMovimientos;
	}
	
	public int getLastRow(int torre){
		int lastRow = Integer.MAX_VALUE;
		switch(torre){
		case 1:
			lastRow = leftTower.getLastRow();
			break;
		case 2:
			lastRow = centerTower.getLastRow();
			break;
		case 3:
			lastRow = rightTower.getLastRow();
			break;
		}
		
		return lastRow;
	}
	
	public int getMinMovimientos(){
		return minMovimientos;
	}
	
	public boolean esFinJuego() {
		if((this.getLastRow(1) == 0 || this.getLastRow(1) == Integer.MAX_VALUE ) && 
			(this.getLastRow(2) == 0 || this.getLastRow(2) == Integer.MAX_VALUE ) ){
			return true;
		} else {
			return false;
		}
	}	
	
	public StackHanoi getLeftTower(){
		return leftTower;
	}
	public StackHanoi getCenterTower(){
		return centerTower;
	}
	public StackHanoi getRightTower(){
		return rightTower;
	}
}
