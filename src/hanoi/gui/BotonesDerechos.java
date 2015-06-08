package hanoi.gui;

import hanoi.Torres;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;

public class BotonesDerechos extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAtras, btnAdelante, btnNuevo;
	private JTextField textNum, textContador;
	private int numMovimientos;
	private int numDiscos;

	/**
	 * Create the panel.
	 */
	public BotonesDerechos() {
		
		numMovimientos = Torres.getNumDiscos();

		setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.GREEN);
		
		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		JPanel panelSur = new JPanel();
		add(panelNorte,BorderLayout.NORTH);
		add(panelCentro, BorderLayout.CENTER);
		add(panelSur,BorderLayout.SOUTH);
		
		btnAdelante = new JButton("Adelante");		
		btnAdelante.addActionListener(this);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		
		JLabel labelMovimientos = new JLabel();
		labelMovimientos.setText("Número de movimientos: ");
		
		textContador = new JTextField();
		textContador.setText(""+numMovimientos);
		textContador.setColumns(3);
		
		textNum = new JTextField();
		textNum.setText(""+numDiscos);
		textNum.setColumns(2);
		
		btnNuevo = new JButton("Nuevo");		
		btnNuevo.addActionListener(this);
		
		panelNorte.add(btnAtras);
		panelNorte.add(btnAdelante);	
		panelCentro.add(labelMovimientos);
		panelCentro.add(textContador);
		panelSur.add(textNum);
		panelSur.add(btnNuevo);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAtras) {
			Torres.recibirMensaje(7);
		} else if (e.getSource()==btnAdelante) {
			Torres.recibirMensaje(8);
		} else if (e.getSource()==btnNuevo){
			setNumDiscos(Integer.parseInt(textNum.getText()));
			Torres.setNumDiscos(numDiscos);
			Torres.setNuevoJuego(true);
		}
	}

	public void setNumMovimientos(int numMovimientos) {
		this.numMovimientos = numMovimientos;
		textContador.setText(""+numMovimientos);
	}

	public int getNumDiscos() {
		if (numDiscos > 9){
			numDiscos = 9;
		} else if (numDiscos < 1) {
			numDiscos = 1;
		}
		return numDiscos;
	}

	public void setNumDiscos(int num) {
		this.numDiscos = num;
		if (numDiscos > 9){
			numDiscos = 9;
		} else if (numDiscos < 1) {
			numDiscos = 1;
		}	
		textNum.setText(""+numDiscos);
	}
}
