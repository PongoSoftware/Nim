package hanoi.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonesDerechos extends JPanel implements ActionListener {

	private JButton btnAtras, btnVolver;

	/**
	 * Create the panel.
	 */
	public BotonesDerechos() {
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		add(btnAtras);
		add(btnVolver);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAtras) {
			//...
		} else if (e.getSource()==btnVolver) {
	//...
		} 
	}
}
