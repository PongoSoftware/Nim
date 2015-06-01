package nim;

import utils.UtilsUI;

public class ControllerView {
	UtilsUI console = new UtilsUI();
	View view = new View();
	public void mainMenu(){
		int opcion = 0;
		while (opcion == 0) {
			opcion = UtilsUI.menu(view.mainMenu());
			
			switch (opcion)
			{
				default:  
					System.out.println(view.optionNotFound());
					opcion = 0;
					break;
				case 1:
					opcion = 0;
					break;
				case 2: 
					opcion = 0;
					break;
				case 99:
					opcion = 99;
					System.out.println(view.exit());	
			}
				
		}
	}
	
	public static void main(String Args[]){
		ControllerView cv = new ControllerView();
		cv.mainMenu();
	}
}
