package photo.tds.gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class VentanaError {

	private JFrame frame;
	private String mensajeError;


	/**
	 * Create the application.
	 */
	public VentanaError(String m) {
		this.mensajeError = m;
		initialize();
	}
	
	public void mostrarVentana(Component c) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(c);
	}
	
	public void esconderVentana() {
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("ERROR");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelNorte.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		JLabel labelMensajeError = new JLabel(this.mensajeError);
		panelCentral.add(labelMensajeError);
		
		JPanel panelSur = new JPanel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		panelSur.add(btnNewButton);
	}

}
