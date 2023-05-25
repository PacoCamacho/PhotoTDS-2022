package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import photo.tds.controlador.Controlador;
import photo.tds.dominio.Usuario;

import javax.swing.JButton;

public class VentanaPerfil {
	 	private JFrame frame;
	 	private Usuario usuario;

	    public VentanaPerfil(Usuario usuario) {
	        initialize();
	        this.usuario = usuario;
	    }

	    public void mostrarVentana() {
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }
	    
	    public void initialize() {
	        frame = new JFrame();
	        frame.setTitle("Perfil");
	        frame.setSize(800, 600);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        JPanel panel = new JPanel();
	        frame.getContentPane().add(panel, BorderLayout.NORTH);
	        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	        
	        JLabel lblMiauuuu = new JLabel(Controlador.INSTANCE.getUsuarioActual().getNombre());
	        lblMiauuuu.setHorizontalAlignment(SwingConstants.LEFT);
	        panel.add(lblMiauuuu);
	        
	        JPanel panel_3 = new JPanel();
	        frame.getContentPane().add(panel_3, BorderLayout.CENTER);
	        panel_3.setLayout(new BorderLayout(0, 0));
	        
	        JPanel panel_1 = new JPanel();
	        panel_3.add(panel_1, BorderLayout.NORTH);
	        
	        JLabel lblSeguidores = new JLabel("Seguidores" + this.usuario.getNumSeguidores());
	        panel_1.add(lblSeguidores);
	        
	        JLabel lblSeguidos = new JLabel("Seguidos" + this.usuario.getNumSeguidos());
	        panel_1.add(lblSeguidos);
	    }
	    //
}
