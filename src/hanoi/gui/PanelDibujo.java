package hanoi.gui;

import hanoi.HanoiMain;
import hanoi.StackHanoi;
import hanoi.HanoiMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PanelDibujo extends JPanel  implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StackHanoi torre1;
	private StackHanoi torre2;
	private StackHanoi torre3;
	private HanoiMain controlador;

	/**
	 * Create the panel.
	 * @param miFrame 
	 */
	public PanelDibujo(MiFrame miFrame) {
		miFrame.getContentPane().add(this, BorderLayout.CENTER);
		addMouseListener(this);
		addMouseMotionListener(this);
		

//        setBackground(Color.GREEN);
	}
	
	public void setControllador(HanoiMain controlador){
		this.controlador = controlador;
	}
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Color color = new Color(109,220,92);
		 g.setColor(color);
		 //Lineas horizontal
		 g.fillRect(20,258,208,15);
		 g.fillRect(240,258,208,15);
		 g.fillRect(460,258,208,15);
		 //Varilla
		 g.fillRect(121,50,10,213);
		 g.fillRect(341,50,10,213);
		 g.fillRect(561,50,10,213);
//		 g.fil
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
	
	public void setTorre1(StackHanoi torre){
		torre1 = torre;
	}
	
	public void setTorre2(StackHanoi torre){
		torre2 = torre;		
	}
	
	public void setTorre3(StackHanoi torre){
		torre3 = torre;		
	}
	
	public void repaint(){
		super.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		controlador.recibirRaton(1,e.getX(),e.getY());
		
	}

	public void mouseExited(MouseEvent e) {
		controlador.recibirRaton(5,e.getX(),e.getY());
	}

	public void mousePressed(MouseEvent e) {
		controlador.recibirRaton(2,e.getX(),e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		controlador.recibirRaton(4, e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		controlador.recibirRaton(3,e.getX(),e.getY());		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
