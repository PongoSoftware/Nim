package nim;

import utils.UtilsUI;

public class Player {

	
	
	private static int numPlayers = 0;
	
	private String playerName;
	private String sysName; // Nombre que le asigna el sistema, por ejemplo, Jugador 1, Jugador 2, Piloto Matias...
	private int sticksRetrieved;
	
	
	public Player (String name){
		
		numPlayers++;
		
		playerName = name;
		sysName = "Player " + numPlayers;
		
	}
	
	public int PlayerChoice(){
		
		int playerOpt = UtilsUI.getConsoleInt("How many sticks will you retrieve? (From 1 to 3)");
		
		while(playerOpt > 3 || playerOpt < 1){
			
			System.out.println("Your chois is a non accepted amount of sticks, please try again.");
			
			playerOpt = UtilsUI.getConsoleInt("How many sticks will you retrieve? (From 1 to 3)");
			
		}
		
		return playerOpt;
		
	}
	
	
	public void setName(String name){
		
		if(name.isEmpty()){
			
			System.out.println("The name field is empty. Please, write your name");
			
		}else if (name.length() < 4 || name.length() > 14  ){
			
			System.out.println()
			
		}
		
	}
	
	
}
