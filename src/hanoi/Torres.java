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
	
	public void mover(int inicio, int destino){
		//Comprobar muy bien que no se ponga en un disco que no corresponda
		if (inicio > 3 || inicio < 1 || destino > 3 || inicio < 1) {
			System.out.println("error");
		} else {
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
				Thread.sleep(200);
			} catch (Exception e){
				//
			}
		}
	}

	public static void recibirMensaje(int mensajeRecibido) {
		mensajeAnterior = mensaje;
		mensaje = mensajeRecibido;
	}
	
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
		} else {
			switch(mensaje){
			case 7:
				break;
			case 8:
				break;
			}
		}
	}
	
	private boolean mensajePoner() {
		if (mensaje < 4 || mensaje > 6){
			return false;
		} else {
			return true;
		}
	}

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
}
