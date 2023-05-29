package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import photo.tds.controlador.Controlador;
import photo.tds.dao.DAOException;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import photo.tds.dominio.Usuario;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VentanaPerfil {
	 	private String usuario;
	 	private String usuarioSesion;
	 	private JPanel panelPerfil;
	 	private static List<Foto> listaFotos;

	    public VentanaPerfil(String usuario, String usuarioSesion) throws DAOException {
	        initialize();
	        this.usuario = usuario;
	        this.usuarioSesion = usuarioSesion;
	    }
	    
	    public JPanel getPanelPerfil() {
	    	return panelPerfil;
	    }
	    
	    private ImageIcon crearImagenIcon(String path) {
	    	if(path == null) {
	    		System.err.println("La ruta no es correcta");
	    		return null;
	    	}
    		java.net.URL imagenURL = getClass().getResource(path);
    		ImageIcon imagen = new ImageIcon(path);
	    	if(imagenURL != null) {
	    		return new ImageIcon(imagenURL);
	    	}
	    	return imagen;
	    	
	    }

	    
	    public void initialize() throws DAOException {
	        
	        
	        panelPerfil = new JPanel();
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{0, 222, 0, 0, 0, 0, 0, 0};
	        gbl_panel.rowHeights = new int[]{40, 0, 32, 0, 0, 0, 0, 0};
	        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	        panelPerfil.setLayout(gbl_panel);
	        
	        JLabel nombreUsuario = new JLabel("aqui va el nombre del usuario");
	        GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
	        gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
	        gbc_nombreUsuario.gridx = 1;
	        gbc_nombreUsuario.gridy = 0;
	        panelPerfil.add(nombreUsuario, gbc_nombreUsuario);
	        
	        JLabel subirFoto = new JLabel("+");
	        GridBagConstraints gbc_subirFoto = new GridBagConstraints();
	        gbc_subirFoto.insets = new Insets(0, 0, 5, 5);
	        gbc_subirFoto.gridx = 5;
	        gbc_subirFoto.gridy = 0;
	        panelPerfil.add(subirFoto, gbc_subirFoto);
	        
	        JLabel numFotos = new JLabel("0");
	        GridBagConstraints gbc_numFotos = new GridBagConstraints();
	        gbc_numFotos.insets = new Insets(0, 0, 5, 5);
	        gbc_numFotos.gridx = 3;
	        gbc_numFotos.gridy = 1;
	        panelPerfil.add(numFotos, gbc_numFotos);
	        
	        JLabel numSeguidores = new JLabel("0");
	        GridBagConstraints gbc_numSeguidores = new GridBagConstraints();
	        gbc_numSeguidores.insets = new Insets(0, 0, 5, 5);
	        gbc_numSeguidores.gridx = 4;
	        gbc_numSeguidores.gridy = 1;
	        panelPerfil.add(numSeguidores, gbc_numSeguidores);
	        
	        JLabel numSeguidos = new JLabel("0");
	        GridBagConstraints gbc_numSeguidos = new GridBagConstraints();
	        gbc_numSeguidos.insets = new Insets(0, 0, 5, 5);
	        gbc_numSeguidos.gridx = 5;
	        gbc_numSeguidos.gridy = 1;
	        panelPerfil.add(numSeguidos, gbc_numSeguidos);
	        
	        JLabel fotoPerfil = new JLabel("aqui va la foto de perfil");
	        GridBagConstraints gbc_fotoPerfil = new GridBagConstraints();
	        gbc_fotoPerfil.gridheight = 3;
	        gbc_fotoPerfil.insets = new Insets(0, 0, 5, 5);
	        gbc_fotoPerfil.gridx = 1;
	        gbc_fotoPerfil.gridy = 1;
	        panelPerfil.add(fotoPerfil, gbc_fotoPerfil);
	        
	        JLabel txtFotos = new JLabel("Fotos");
	        GridBagConstraints gbc_txtFotos = new GridBagConstraints();
	        gbc_txtFotos.insets = new Insets(0, 0, 5, 5);
	        gbc_txtFotos.gridx = 3;
	        gbc_txtFotos.gridy = 2;
	        panelPerfil.add(txtFotos, gbc_txtFotos);
	        
	        JLabel txtSeguidores = new JLabel("Seguidores");
	        GridBagConstraints gbc_txtSeguidores = new GridBagConstraints();
	        gbc_txtSeguidores.insets = new Insets(0, 0, 5, 5);
	        gbc_txtSeguidores.gridx = 4;
	        gbc_txtSeguidores.gridy = 2;
	        panelPerfil.add(txtSeguidores, gbc_txtSeguidores);
	        
	        JLabel txtSeguidos = new JLabel("Seguidos");
	        GridBagConstraints gbc_txtSeguidos = new GridBagConstraints();
	        gbc_txtSeguidos.insets = new Insets(0, 0, 5, 5);
	        gbc_txtSeguidos.gridx = 5;
	        gbc_txtSeguidos.gridy = 2;
	        panelPerfil.add(txtSeguidos, gbc_txtSeguidos);
	        
	        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	        gbc_scrollPane.gridheight = 3;
	        gbc_scrollPane.gridwidth = 5;
	        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
	        gbc_scrollPane.fill = GridBagConstraints.BOTH;
	        gbc_scrollPane.gridx = 1;
	        gbc_scrollPane.gridy = 4;
	        listaFotos = Controlador.INSTANCE.getFotosPerfil(usuario);
	        System.out.println("Lista fotos:");
	        for(Foto f : listaFotos) {
	        	System.out.println("foto:");
	        	System.out.println(f.titulo);
	        	
	        }
	        
	        System.out.println();
	        JPanel panelContenedorFotos = new JPanel();
	        panelContenedorFotos.setLayout(new BoxLayout(panelContenedorFotos, BoxLayout.Y_AXIS));
	        for(Foto foto : listaFotos) {
	        	if(foto instanceof Foto) {
	        		PanelFoto fotoPanel = new PanelFoto(foto,usuario);
	        		panelContenedorFotos.add(fotoPanel);
	        		panelContenedorFotos.add(Box.createRigidArea(new Dimension(0, 10)));
	        	}
	        }
	        JScrollPane scrollPane = new JScrollPane(panelContenedorFotos);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	        panelPerfil.add(scrollPane, gbc_scrollPane);

	        
	        
	        

	    }
	    
	    
	    
	    
}
