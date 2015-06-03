package hanoi;

import hanoi.gui.MiFrame;
import hanoi.gui.PanelDibujo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Torres {
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
			disco = centerTower.pop();
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
	
	public void mover(int inicio, int destino){
		//Comprobar muy bien que no se ponga en un disco que no corresponda
		if (inicio > 3 || inicio < 1 || destino > 3 || inicio < 1) {
			System.out.println("error");
		} else {
			int disco = 0;
			disco = extraer(inicio);
			poner(inicio,destino,disco);
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
			torres.show();
			ventana.repaint();
			int origen = UtilsUI.getConsoleInt("De que torre quieres sacarlo:");
			int destino = UtilsUI.getConsoleInt("En que torre quieres ponerlo:");
			torres.mover(origen, destino);	
			
		}
	}
}
