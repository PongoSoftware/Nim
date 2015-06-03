package hanoi.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Botones extends JPanel implements ActionListener {

	private JButton btnPoner1, btnPoner2, btnPoner3, btnQuitar1, btnQuitar2, btnQuitar3;

	
	private boolean origenCambiado = false; 
	private boolean destinoCambiado = false;
	
	/**
	 * Create the panel.
	 */
	public Botones() {
		
		btnPoner1 = new JButton("Poner");
		btnPoner1.addActionListener(this);
		
		btnPoner2 = new JButton("Poner");
		btnPoner2.addActionListener(this);
		
		btnPoner3 = new JButton("Poner");
		btnPoner3.addActionListener(this);

		btnQuitar1 = new JButton("Quitar");
		btnQuitar1.addActionListener(this);

		btnQuitar2 = new JButton("Quitar");
		btnQuitar2.addActionListener(this);

		btnQuitar3 = new JButton("Quitar");
		btnQuitar3.addActionListener(this);

		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(btnPoner1);
		add(btnPoner2);
		add(btnPoner3);
		add(btnQuitar1);
		add(btnQuitar2);
		add(btnQuitar3);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnPoner1) {
			
			destinoCambiado = true;
			
		} else if (e.getSource()==btnPoner2) {
			
			destinoCambiado = true;
			
		} else if (e.getSource()==btnPoner3) {
			
			destinoCambiado = true;
			
		} else if (e.getSource()==btnQuitar1) {
	
			origenCambiado = true;
			
		} else if (e.getSource()==btnQuitar2) {
			
			origenCambiado = true;
			
		}else if (e.getSource()==btnQuitar3) {
	
			origenCambiado = true;
			
		} else {
			//...
		}
	}
}
