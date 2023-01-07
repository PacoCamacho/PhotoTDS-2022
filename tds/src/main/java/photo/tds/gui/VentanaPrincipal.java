package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import photo.tds.controlador.Controlador;
import photo.tds.dominio.RepositorioUsuarios;

public class VentanaPrincipal {

	private JFrame frmVentanaPrincipal;
	
	public VentanaPrincipal() {
		initialize();
	}


	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
		frmVentanaPrincipal.setVisible(true);
	}
	
	public void initialize() {
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.setTitle("AppVideo- Ventana Principal");
		frmVentanaPrincipal.setSize(500, 600);
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = (JPanel) frmVentanaPrincipal.getContentPane();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblBienvenidoAPhototds = new JLabel("Bienvenido a PhotoTDS, " + Controlador.INSTANCE.getUsuarioActual().getNombre());
		lblBienvenidoAPhototds.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblBienvenidoAPhototds);

		
	}

}