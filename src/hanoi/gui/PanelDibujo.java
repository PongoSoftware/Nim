package hanoi.gui;

import hanoi.Stack;
import hanoi.Stack.Nodo;
import hanoi.Torres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class PanelDibujo extends JPanel  implements MouseListener, MouseMotionListener{

	private Stack torre1;
	private Stack torre2;
	private Stack torre3;
	private Torres torresHanoi;

	/**
	 * Create the panel.
	 * @param miFrame 
	 */
	public PanelDibujo(MiFrame miFrame) {
		miFrame.getContentPane().add(this, BorderLayout.CENTER);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void setTorresHanoi(Torres torres){
		torresHanoi = torres;
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

	public void mouseClicked(MouseEvent e) {
		torresHanoi.recibirRaton(1,e.getX(),e.getY());
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

//		System.out.println("entered");
	}

	public void mouseExited(MouseEvent e) {
		torresHanoi.recibirRaton(5,e.getX(),e.getY());
	}

	public void mousePressed(MouseEvent e) {
		torresHanoi.recibirRaton(2,e.getX(),e.getY());
//		if(disco.comprobarPosicion(e.getX(),e.getY())){
//			disco.estaPresionado = true;
//		}
//		System.out.println(e.getX()+"_"+e.getY());
//		variable = "Presionado";
	}

	public void mouseReleased(MouseEvent e) {
		torresHanoi.recibirRaton(4, 0, 0);
		// TODO Auto-generated method stub
//		if (disco.estaPresionado) {
//			disco.estaPresionado = false;
//			System.out.println("libera");
//		}
//		variable = "libero";
	}

	public void mouseDragged(MouseEvent e) {
		torresHanoi.recibirRaton(3,e.getX(),e.getY());
		// TODO Auto-generated method stub
		/*if (disco.estaPresionado) {
			disco.setPosicion(e.getX(), e.getY());
		}*/
//		if (variable != null){
//		if (!variable.equals("click")){
//			variable = "arrastro";
//		}} else {
//			variable = "arrastro";
//		}
		
	}
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("move");
		
	}
	
}
