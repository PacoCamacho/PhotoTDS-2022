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
import photo.tds.dominio.Usuario;
import pulsador.Luz;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class VentanaPrincipal {
	
	private static Usuario usuario;

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
		
		JPanel panelNorte = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JLabel PhotoAppl = new JLabel("PhotoApp");
		PhotoAppl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(PhotoAppl);
		
		JPanel SubPanelNorte = new JPanel();
		panelNorte.add(SubPanelNorte);
		
		Luz luz = new Luz();
		SubPanelNorte.add(luz);
		
		JButton SubirFoto = new JButton("");
		SubirFoto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/photo/tds/imagenes/subir-imagen.png")));
		SubPanelNorte.add(SubirFoto);
		
		SubirFoto.addActionListener(e -> {
			VentanaPublicacion nuevaFoto = new VentanaPublicacion(usuario);
			nuevaFoto.mostrarVentana(frmVentanaPrincipal);
		});
		
		JLabel Perfil = new JLabel("Perfil");
		Perfil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(Perfil);
		
		JPanel panelCentral = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panelCentral, BorderLayout.SOUTH);

		
	}

}