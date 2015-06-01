package nim;

import java.util.Stack;

import utils.UtilsUI;

public class Nim {
	
	int numMatch;
	Stack matchStack;
	public Nim(int numMatch){
		this.numMatch = numMatch;
		matchStack = new Stack();
		for(int i = 0; i < numMatch; i++){
			matchStack.push(i);
		}
	}
	
	public int popMatch(int num){
		int leftMatch = 0;
		for (int i = 0;i < num; i++){
			try {
				leftMatch = (Integer) matchStack.pop();
			} catch (Exception e){
				leftMatch = 0;
			}
		}
		return leftMatch;
	}
	
	public static void main(String Arg[]){
		boolean sigue = true;
		while(sigue){
			int numMatch = 28;
			Nim nim = new Nim(numMatch);
			int player = 1;
			boolean gameOver = false;
			System.out.println("Quedan "+numMatch+" cerillas");
			while(!gameOver){
				
				int num = PlayerChoice(3,5,"Jugador "+player);
				
				num = nim.popMatch(num);
				if (num <= 0){
					gameOver = true;
				} 
				System.out.println("Quedan "+num+" cerillas");
				player++;
				if (player > 2){
					player = 1;
				}
			}
			System.out.println("Ha ganado el jugador "+player);
			int opt = UtilsUI.menu("Pulse 1 para volver a jugar u otra tecla para terminar");
			if (opt != 1){
				sigue = false;
			}
		}
		
	}
	
	public static int PlayerChoice(int min, int max,String player){
		
		int playerOpt = UtilsUI.getConsoleInt(player+" How many sticks will you retrieve? (From "+min+" to "+max+")");
		while(playerOpt > max || playerOpt < min){
			
			System.out.println("Your chois is a non accepted amount of sticks, please try again.");
			
			playerOpt = UtilsUI.getConsoleInt(player+" How many sticks will you retrieve? (From "+min+" to "+max+")");
			
		}
		
		return playerOpt;
		
	}

}
