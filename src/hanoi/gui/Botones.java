package hanoi.gui;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Botones extends JPanel implements ActionListener {

	private JButton btnPoner1, btnPoner2, btnPoner3, btnQuitar1, btnQuitar2, btnQuitar3;

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
			//...
		} else if (e.getSource()==btnPoner2) {
	//...
		} else if (e.getSource()==btnPoner3) {
			//...
		} else {
		}
	}
}
