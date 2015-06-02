package Hanoi;

public class Torres {
	Stack leftTower;
	Stack centerTower;
	Stack rigthTower;
	public Torres(int higth){
		leftTower = new Stack();
		centerTower = new Stack();
		rigthTower = new Stack();
		
		for (int i = higth; i > 0; i--){
			leftTower.push(i);
		}
	}
	
	public void mover(int tower1, int tower2){
		int disco = 0;
		switch(tower1){
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
		
		switch(tower2){
		case 1:
			leftTower.push(disco);
			break;
		case 2:
			centerTower.push(disco);
		case 3:
			rigthTower.push(disco);
		}		
	}

	public void show(){
		leftTower.recorrer();
		centerTower.recorrer();
		rigthTower.recorrer();
	}
	
	public static void main(String Args[]){
		Torres torres = new Torres(9);
		while (true){
			torres.show();
			int origen = UtilsUI.getConsoleInt("De que torre quieres sacarlo:");
			int destino = UtilsUI.getConsoleInt("En que torre quieres ponerlo:");
			
			torres.mover(origen, destino);
			
			
		}
	}
}
