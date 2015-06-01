package nim;

import utils.UtilsUI;

public class View {
	public String mainMenu(){
		String texto = "Elige una opción: \n"
				+ "1) Nuevo juego \n"
				+ "2) Configurar\n"
				+ "q) Salir\n";
		return texto;			
	}
	
	public String optionNotFound(){
		return "No se ha encontrado la opción, vuelve a intentarlo";
	}
	
	public String exit(){
		return "¡Hasta luego!";
	}
	
	public int getNumPlayer(){
		return UtilsUI.getConsoleInt("Con cuantos jugadores quieres jugar [2]:",2);
	}
	
	public int getNumMatch(){
		return UtilsUI.getConsoleInt("¿Con cuantas cerillas? [23]:",23);
	}
	
	public String getNamePlayer(int i){
		return UtilsUI.getConsole("Nombre del jugador "+i+":");
	}
	
	public void gameNotConfig(){
		System.out.println("No has configurado el juego, configuralo primero");
	}
	
	public void winner(String name){
		System.out.println("Ha ganado "+name+" ¡enhorabuena!");
	}
}
