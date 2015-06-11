package hanoi.gui;

import hanoi.HanoiMain;

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
	private JButton btnAtras, btnAdelante, btnNuevo, btnAutoresolver;
	private JTextField textNum, textContador;
	private JLabel labelMinMovimientos;
	private int numMovimientos;
	private int numDiscos;
	private int minMovimientos;

	/**
	 * Create the panel.
	 */
	public BotonesDerechos() {
		
		numMovimientos = HanoiMain.getNumDiscos();

		setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.GREEN);
		
		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		JPanel panelSur = new JPanel();
		add(panelNorte,BorderLayout.NORTH);
		add(panelCentro, BorderLayout.CENTER);
		add(panelSur,BorderLayout.SOUTH);
		
		labelMinMovimientos = new JLabel();
		panelSur.add(labelMinMovimientos);
		labelMinMovimientos.setText("de un mínimo de "+minMovimientos);
		
		btnAdelante = new JButton("Adelante");		
		btnAdelante.addActionListener(this);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		
		panelNorte.add(btnAtras);
		panelNorte.add(btnAdelante);
		
			panelCentro.setLayout(new BorderLayout(0, 0));
		
			JPanel filaSuperior = new JPanel();
			panelCentro.add(filaSuperior, BorderLayout.NORTH);
			
			textNum = new JTextField();
			filaSuperior.add(textNum);
			textNum.setText(""+numDiscos);
			textNum.setColumns(2);
			
			btnNuevo = new JButton("Nuevo");		
			filaSuperior.add(btnNuevo);
			btnNuevo.addActionListener(this);
			
			JPanel filaCentro = new JPanel();
			panelCentro.add(filaCentro, BorderLayout.CENTER);
			
			btnAutoresolver = new JButton("Iniciar/Parar Autoresolver");
			filaCentro.add(btnAutoresolver);
			btnAutoresolver.addActionListener(this);
			
			JPanel filaInferior = new JPanel();
			panelCentro.add(filaInferior,BorderLayout.SOUTH);
			
			JLabel labelMovimientos = new JLabel();
			filaInferior.add(labelMovimientos);
			labelMovimientos.setText("Número de movimientos: ");
			
			textContador = new JTextField();
			filaInferior.add(textContador);
			textContador.setText(""+numMovimientos);
			textContador.setColumns(3);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAtras) {
			HanoiMain.recibirMensaje(7);
		} else if (e.getSource()==btnAdelante) {
			HanoiMain.recibirMensaje(8);
		} else if (e.getSource()==btnNuevo){
			setNumDiscos(Integer.parseInt(textNum.getText()));
			HanoiMain.setNumDiscos(numDiscos);
			HanoiMain.setNuevoJuego(true);
			System.out.println("__");
		} else if (e.getSource()==btnAutoresolver) {
			HanoiMain.switchAutorresolver();
		} else {
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

	public void setMinMovimientos(int minMovimientos) {
		this.minMovimientos = minMovimientos;
		labelMinMovimientos.setText("de un mínimo de "+minMovimientos);
		
	}
}