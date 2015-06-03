package hanoi.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MiFrame extends JFrame {

	JPanel ventana;
	public MiFrame(String title){
		super(title);
		
		setVisible(true);
		setSize(800,380);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		ventana = new PanelDibujo(this);

		JPanel botones = new Botones(); 
		getContentPane().add(botones, BorderLayout.NORTH);
		
	}
	
	public PanelDibujo getVentana(){
		return  (PanelDibujo) ventana;
	}
	

}
