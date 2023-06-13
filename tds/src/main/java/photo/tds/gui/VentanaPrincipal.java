package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import photo.tds.controlador.Controlador;
import photo.tds.dao.DAOException;
import photo.tds.dominio.RepositorioUsuarios;
import photo.tds.dominio.Usuario;
import photo.tds.dominio.Publicacion;
import pulsador.Luz;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class VentanaPrincipal {
	
	private static String usuario;

	private JFrame frmVentanaPrincipal;
	private static JPanel panelCentral;
	private static List<Publicacion> listaPublicaciones;
	
	

	public static JPanel getPanelCentral() {
		return panelCentral;
	}
	
	public VentanaPrincipal(String u) {
		usuario=u;
		initialize();
	}


	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
		frmVentanaPrincipal.setVisible(true);
	}
	
	public void initialize(){
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
		//panelCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frmVentanaPrincipal.addComponentListener(new ComponentAdapter() {
			@Override
            public void componentResized(ComponentEvent e) {
                panelCentral.setSize(frmVentanaPrincipal.getSize());
                panelCentral.revalidate();
            }
		});
		
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
			VentanaNuevaPublicacion nuevaFoto = new VentanaNuevaPublicacion(usuario);
			nuevaFoto.mostrarVentana(frmVentanaPrincipal);
		});
		
		JLabel perfil = new JLabel("Perfil");
		perfil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(perfil);
		
		perfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					iniciarPanelPerfil();
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				CardLayout cl = (CardLayout) panelCentral.getLayout();
				cl.show(panelCentral, "panelPerfil");
			}
		});
		
		PhotoAppl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCentral.removeAll();
				iniciarPanelPublicaciones();
				CardLayout cl = (CardLayout) panelCentral.getLayout();
				cl.show(panelCentral, "panelPrincipal");
			}
		});
		
		
		
		
		iniciarPanelPublicaciones();
		
	}
	
	private void iniciarPanelPerfil() throws DAOException{
		System.out.println("Cambio al panel de perfil");
		JPanel panelPerfil = new VentanaPerfil(usuario,usuario).getPanelPerfil();
		panelCentral.add(panelPerfil, "panelPerfil");
		
	}
	

	
	private void iniciarPanelPublicaciones(){
		System.out.println("Cambio al panel de publicaciones");
		JPanel panelPublicaciones = new PanelPublicaciones(usuario,frmVentanaPrincipal).getPanelPublicaciones();
		//panelPublicaciones.setBorder(BorderFactory.createLineBorder(Color.RED));
		frmVentanaPrincipal.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	                // Obtener el tamaño de la ventana
	                Dimension size = frmVentanaPrincipal.getSize();
	                // Ajustar el tamaño del panel al tamaño de la ventana
	                panelPublicaciones.setSize(size);
	            }
	        });
		panelCentral.add(panelPublicaciones,"panelPublicaciones");
		
	}

}