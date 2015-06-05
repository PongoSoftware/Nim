package hanoi.gui;

import hanoi.Torres;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonesDerechos extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAtras, btnAdelante;

	/**
	 * Create the panel.
	 */
	public BotonesDerechos() {
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);

		btnAdelante = new JButton("Adelante");
		btnAdelante.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		add(btnAtras);
		add(btnAdelante);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAtras) {
			Torres.recibirMensaje(7);
		} else if (e.getSource()==btnAdelante) {
			Torres.recibirMensaje(8);
		} 
	}
}
