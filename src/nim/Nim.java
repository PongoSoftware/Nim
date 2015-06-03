package nim;

import java.util.Stack;

import utils.UtilsUI;

public class Nim {
	
	int numMatch;
	Stack matchStack;
	public Nim(int numMatch){
		this.numMatch = numMatch;
		matchStack = new Stack();
		for(int i = 0; i < 23; i++){
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
		Nim nim = new Nim(23);
		int player = 1;
		boolean gameOver = false;
		while(!gameOver){
			
			int num = PlayerChoice(1,3,"Jugador "+player);
			
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
	}
	
	public static int PlayerChoice(int min, int max,String player){
		
		int playerOpt = UtilsUI.getConsoleInt(player+" How many sticks will you retrieve? (From 1 to 3)");
		while(playerOpt > max || playerOpt < min){
			
			System.out.println("Your chois is a non accepted amount of sticks, please try again.");
			
			playerOpt = UtilsUI.getConsoleInt(player+" How many sticks will you retrieve? (From 1 to 3)");
			
		}
		
		return playerOpt;
		
	}

}
