package hanoi.gui;

import hanoi.Stack;
import hanoi.Stack.Nodo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class PanelDibujo extends JPanel {

	private Stack torre1;
	private Stack torre2;
	private Stack torre3;

	/**
	 * Create the panel.
	 * @param miFrame 
	 */
	public PanelDibujo(MiFrame miFrame) {
		miFrame.getContentPane().add(this, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 
		 if(torre1 != null){
			 torre1.recorrerGrafico(g);
		 }
		 if(torre2 != null){
			 torre2.recorrerGrafico(g);
		 }
		 if(torre3 != null){
			 torre3.recorrerGrafico(g);
		 }
	}	
	
	public void setTorre1(Stack torre){
		torre1 = torre;
	}
	
	public void setTorre2(Stack torre){
		torre2 = torre;		
	}
	
	public void setTorre3(Stack torre){
		torre3 = torre;		
	}
	
	public void repaint(){
		super.repaint();
	}
}
