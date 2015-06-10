package hanoi.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel ventana, botonesDerechos;
	public MiFrame(String title){
		super(title);
		
		setVisible(true);
		setSize(900,400);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		ventana = new PanelDibujo(this);
		ventana.setSize(850,350);

		JPanel botonesSuperiores = new BotonesSuperiores(); 
		getContentPane().add(botonesSuperiores, BorderLayout.NORTH);
		JPanel botonesInferiores= new BotonesInferiores(); 
		getContentPane().add(botonesInferiores, BorderLayout.SOUTH);
		botonesDerechos= new BotonesDerechos(); 
		getContentPane().add(botonesDerechos, BorderLayout.EAST);
		
	}
	
	public PanelDibujo getVentana(){
		return  (PanelDibujo) ventana;
	}

	public void setNumMovimientos(int numMovimientos) {
		((BotonesDerechos) botonesDerechos).setNumMovimientos(numMovimientos);		
	}

	public int getNumDiscos() {
		return ((BotonesDerechos) botonesDerechos).getNumDiscos();
	}

	public void setNumDiscos(int numDiscos) {
		((BotonesDerechos) botonesDerechos).setNumDiscos(numDiscos);
		
	}

	public void setMaxMovimientos(int minMovimientos) {
		((BotonesDerechos) botonesDerechos).setMinMovimientos(minMovimientos);
		
	}
	

}
