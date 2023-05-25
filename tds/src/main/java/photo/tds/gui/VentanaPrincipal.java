package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import photo.tds.controlador.Controlador;
import photo.tds.dominio.RepositorioUsuarios;
import photo.tds.dominio.Usuario;
import photo.tds.dominio.Publicacion;
import pulsador.Luz;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class VentanaPrincipal {
	
	private static Usuario usuario;

	private JFrame frmVentanaPrincipal;
	private static JPanel panelCentral;
	private static List<Publicacion> listaPublicaciones;
	
	private final static int BORDE_x = 480;
	private final static int BORDE_Y = 720;

	
	
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
		
		panelCentral = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new CardLayout(0, 0));
		
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
		
		JLabel perfil = new JLabel("Perfil");
		perfil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(perfil);
		
		perfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarPanelPerfil();
				CardLayout cl = (CardLayout) panelCentral.getLayout();
				cl.show(panelCentral, "panelPerfil");
			}
		});
		
		PhotoAppl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarPanelPrincipal();
				CardLayout cl = (CardLayout) panelCentral.getLayout();
				cl.show(panelCentral, "panelPrincipal");
			}
		});
		
		
		
		JScrollPane panelFotos = new JScrollPane();
		panelCentral.add(panelFotos, "name_353730463731600");

		
	}
	
	private void iniciarPanelPerfil() {
		
		JPanel panelPerfil = new VentanaPerfil(usuario,usuario).getPanelPerfil();
		panelCentral.add(panelPerfil, "panelPerfil");
		
//		JPanel panelAlbum = new PanelAlbum(user, user).getPanelPerfil();
//		panelCentral.add(panelAlbum, "panelAlbum");
//		
//
//		JPanel panelEdit = new PanelEdit(user).getPanel();
//		panelCentralCardLayout.add(panelEdit, "panelEdit");
	}
	
	private void iniciarPanelPrincipal() {
		initialize();
	}

}