package hanoi.gui;

import hanoi.HanoiMain;
import hanoi.Torres;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonesSuperiores extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnQuitar1, btnQuitar2, btnQuitar3;

	/**
	 * Create the panel.
	 */
	public BotonesSuperiores() {
		btnQuitar1 = new JButton("Quitar");
		btnQuitar1.addActionListener(this);

		btnQuitar2 = new JButton("Quitar");
		btnQuitar2.addActionListener(this);

		btnQuitar3 = new JButton("Quitar");
		btnQuitar3.addActionListener(this);

		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		add(btnQuitar1);
		add(btnQuitar2);
		add(btnQuitar3);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnQuitar1) {
			HanoiMain.recibirMensaje(1);
		} else if (e.getSource()==btnQuitar2) {
			HanoiMain.recibirMensaje(2);
		} else if (e.getSource()==btnQuitar3) {
			HanoiMain.recibirMensaje(3);
		} else {
		}
	}
}
