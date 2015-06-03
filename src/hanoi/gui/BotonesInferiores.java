package hanoi.gui;

import hanoi.Torres;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonesInferiores extends JPanel implements ActionListener {

	private JButton btnPoner1, btnPoner2, btnPoner3;

	/**
	 * Create the panel.
	 */
	public BotonesInferiores() {
		
		btnPoner1 = new JButton("Poner");
		btnPoner1.addActionListener(this);
		
		btnPoner2 = new JButton("Poner");
		btnPoner2.addActionListener(this);
		
		btnPoner3 = new JButton("Poner");
		btnPoner3.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(btnPoner1);
		add(btnPoner2);
		add(btnPoner3);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnPoner1) {
			Torres.recibirMensaje(4);
		} else if (e.getSource()==btnPoner2) {
			Torres.recibirMensaje(5);
		} else if (e.getSource()==btnPoner3) {
			Torres.recibirMensaje(6);
		} else {
		}
	}
}
