package hanoi.gui;

import hanoi.Stack;
import hanoi.Torres;

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
		

//        setBackground(Color.GREEN);
	}
	
	public void setTorresHanoi(Torres torres){
		torresHanoi = torres;
	}
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 g.setColor(Color.YELLOW);
		 g.fillRect(20,258,208,15);
		 g.fillRect(240,258,208,15);
		 g.fillRect(460,258,208,15);
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

	public void mouseExited(MouseEvent e) {
		torresHanoi.recibirRaton(5,e.getX(),e.getY());
	}

	public void mousePressed(MouseEvent e) {
		torresHanoi.recibirRaton(2,e.getX(),e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		torresHanoi.recibirRaton(4, 0, 0);
	}

	public void mouseDragged(MouseEvent e) {
		torresHanoi.recibirRaton(3,e.getX(),e.getY());		
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
