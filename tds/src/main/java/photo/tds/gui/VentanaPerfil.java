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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
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
import photo.tds.dominio.Album;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import photo.tds.dominio.Usuario;
import photo.tds.helpers.ImageListCellRenderer;
import photo.tds.helpers.Item;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JToolBar;

public class VentanaPerfil {
	 	private String usuario;
	 	private String usuarioSesion;
	 	private JPanel panelPerfil;
	 	private static List<Foto> listaFotos;
	 	private static List<Album> listaAlbumes;
	 	private boolean fotos;


	    public VentanaPerfil(String usuario, String usuarioSesion, boolean foto) throws DAOException{
	    	this.usuario = usuario;
	        System.out.println("Usuario perfil: "+usuario);
	        this.usuarioSesion = usuarioSesion;
	        fotos = foto;
	    	initialize();
	        
	    }
	    
	    public JPanel getPanelPerfil() {
	    	return panelPerfil;
	    }
	    

	    
	    public void initialize() throws DAOException{
	        
	        
	        panelPerfil = new JPanel();
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{0, 222, 0, 0, 0, 0, 0, 0};
	        gbl_panel.rowHeights = new int[]{40, 0, 32, 0, 0, 0, 0, 0, 0};
	        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
	        panelPerfil.setLayout(gbl_panel);
	        
	        JLabel nombreUsuario = new JLabel(usuario);
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
	        
	        JLabel numFotos = new JLabel(String.valueOf(Controlador.getInstancia().getFotosPerfil(usuario).size()));
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
	        
	        
	        
	        try {
				listaFotos = Controlador.getInstancia().getFotosPerfil(usuario);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        listaAlbumes = Controlador.getInstancia().getAlbumes(usuario);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	        gbc_scrollPane.gridheight = 3;
	        gbc_scrollPane.gridwidth = 5;
	        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
	        gbc_scrollPane.fill = GridBagConstraints.BOTH;
	        gbc_scrollPane.gridx = 1;
	        gbc_scrollPane.gridy = 4;
	        panelPerfil.add(scrollPane, gbc_scrollPane);
	        
	        if(fotos) {
	        	
	        	scrollPane.repaint();
	        	System.out.println("--------- Se muestran las fotos -----------");
	        	
	        	System.out.println("Lista fotos:");
		        for(Foto f : listaFotos) {
		        	System.out.println("Doto: "+ f.titulo);
		        }
		        JList<Item> list = new JList<>();
		        scrollPane.setViewportView(list);
		        DefaultListModel<Item> model = new DefaultListModel<>();
		        list.setModel(model);
		        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		        //list.setCellRenderer(new ImageListCellRenderer());
		        list.setCellRenderer(FotoCellListRenderer());
		        list.setVisibleRowCount(-1);
		        
		        
		        
		        for(Foto foto : listaFotos) {
		        	if(foto instanceof Foto) {
		        		System.out.println("Creo un panel foto para :"+ foto.getTitulo());
		        		
		        		Image image=null;
						try {
							image = ImageIO.read(new File(foto.getPath()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		Item item = new Item(image);
		        		model.addElement(item);
		        	    System.out.println("Se agregó la imagen: " + foto.getPath()); // Imprimir la ruta de la imagen

		        	}
		        }
	        }else {
	        	
	        	scrollPane.repaint();
	        	System.out.println("--------- Se muestran los albumes -----------");
	        	System.out.println("----------------------------------");
	        	System.out.println("Lista albumes:");
		        for(Album a: listaAlbumes) {
		        	System.out.println("Album: "+ a.getTitulo()+ " id: "+ a.getId());
		        	for(Foto f: a.getFotos()) {
		        		System.out.println("    Foto: "+ f.getTitulo()+ " id: "+ f.getId());
		        		System.out.println("    usuario: "+f.getUsuario());
		        	}
		        System.out.println("----------------------------------");
		        }
	        	
		        JList<Item> listAlbum = new JList<>();
		        scrollPane.setViewportView(listAlbum);
		        DefaultListModel<Item> modelAlbum = new DefaultListModel<>();
		        listAlbum.setModel(modelAlbum);
		        listAlbum.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		        //list.setCellRenderer(new ImageListCellRenderer());
		        listAlbum.setCellRenderer(AlbumCellListRenderer());
		        listAlbum.setVisibleRowCount(-1);  
		        for (Album album : listaAlbumes) {
		        	if(album instanceof Album) {
		        		System.out.println("Creo un panel album para :"+ album.getTitulo());
		        		
		        		Image image=null;
						try {
							image = ImageIO.read(new File(album.getPathPortada()));
						} catch (IOException ea) {
							// TODO Auto-generated catch block
							ea.printStackTrace();
						}
		        		Item item = new Item(image);
		        		modelAlbum.addElement(item);
		        	    System.out.println("Se agregó la imagen: " + album.getPathPortada()); // Imprimir la ruta de la imagen

		        	}
				}
	        
	        }
	       
	        
	        
	        JButton botonFotos = new JButton("Fotos");
	        botonFotos.setVisible(!fotos);
	        
	        JButton añadirAlbum = new JButton("Añadir album");
	        añadirAlbum.setHorizontalAlignment(SwingConstants.LEFT);
	        añadirAlbum.setVisible(!fotos);
	        GridBagConstraints gbc_añadirAlbum = new GridBagConstraints();
	        gbc_añadirAlbum.insets = new Insets(0, 0, 0, 5);
	        gbc_añadirAlbum.gridx = 1;
	        gbc_añadirAlbum.gridy = 7;
	        panelPerfil.add(añadirAlbum, gbc_añadirAlbum);
	        
	        GridBagConstraints gbc_botonFotos = new GridBagConstraints();
	        gbc_botonFotos.insets = new Insets(0, 0, 0, 5);
	        gbc_botonFotos.gridx = 4;
	        gbc_botonFotos.gridy = 7;
	        panelPerfil.add(botonFotos, gbc_botonFotos);
	        
	        
	        JButton botonAlbum = new JButton("Álbumes");
	        botonAlbum.setVisible(fotos);
	        GridBagConstraints gbc_botonAlbum = new GridBagConstraints();
	        gbc_botonAlbum.insets = new Insets(0, 0, 0, 5);
	        gbc_botonAlbum.gridx = 5;
	        gbc_botonAlbum.gridy = 7;
	        panelPerfil.add(botonAlbum, gbc_botonAlbum);
	       
	        
	        botonAlbum.addActionListener(e -> {
	        	System.out.println("boton album");
	        	try {
					VentanaPrincipal.iniciarPanelPerfil(false);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        });
	       
	        botonFotos.addActionListener(e -> {
	        	try {
					VentanaPrincipal.iniciarPanelPerfil(true);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        });
	        
	        añadirAlbum.addActionListener(e -> {
	        	VentanaNuevaPublicacion nuevoAlbum = new VentanaNuevaPublicacion(usuario,false);
				nuevoAlbum.mostrarVentana(panelPerfil);
	        });
	       
	        

	       
	        
	        
	        
//	        JScrollPane scrollPane = new JScrollPane();
//	        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
//	        gbc_scrollPane.gridheight = 3;
//	        gbc_scrollPane.gridwidth = 5;
//	        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
//	        gbc_scrollPane.fill = GridBagConstraints.BOTH;
//	        gbc_scrollPane.gridx = 1;
//	        gbc_scrollPane.gridy = 4;
//	        panelPerfil.add(scrollPane,gbc_scrollPane);
	        
	       
	        
	        
//	        JLabel labelFoto = new JLabel("Aqui va la foto");
//	        //JPanel panelContenedorFotos = new JPanel();
//	        //panelContenedorFotos.setLayout(new BoxLayout(panelContenedorFotos, BoxLayout.Y_AXIS));
//	        for(Foto foto : listaFotos) {
//	        	if(foto instanceof Foto) {
//	        		System.out.println("Creo un panel foto para :"+ foto.getTitulo());
//	        		
//	        		scrollPane.add(labelFoto);
//	        		PanelFoto fotoPanel = new PanelFoto(foto,usuario);
//	        		scrollPane.add(fotoPanel);
//	        		fotoPanel.setVisible(true);
//	        		scrollPane.add(Box.createRigidArea(new Dimension(0, 10)));
//	        	}
//	        }
	       

	        
	        
	        

	    }
	    
	    private static ListCellRenderer<? super Item> FotoCellListRenderer(){
	    	return new DefaultListCellRenderer() {
	    		
	    		
	    		
	    		@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
	    			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    			Item item = (Item) value;
	    			ImageIcon icon = new ImageIcon(item.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	    			label.setIcon(icon);
	    			    
					if(value instanceof Item) {
						if(isSelected) {
							System.out.println("Foto seleccionada");
							JPanel panelFoto = null;
							try {
								panelFoto = new PanelFoto(listaFotos.get(index), listaFotos.get(index).getUsuario()).getPanelFoto();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							JPanel panelCentralCardLayout = VentanaPrincipal.getPanelCentral();
							panelCentralCardLayout.add(panelFoto, "panel foto");
							CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
							cl.show(panelCentralCardLayout, "panel foto");
						}
					}


					return label;
				}
	    	};
	    }
	    
	    private static ListCellRenderer<? super Item> AlbumCellListRenderer(){
	    	return new DefaultListCellRenderer() {
	    		
	    		
	    		
	    		@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
	    			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    			Item item = (Item) value;
	    			ImageIcon icon = new ImageIcon(item.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	    			label.setIcon(icon);
	    			    
					if(value instanceof Item) {
						if(isSelected) {
							System.out.println("Foto seleccionada");
							JPanel panelAlbum = null;
							try {
								panelAlbum = new PanelAlbum(listaAlbumes.get(index), listaAlbumes.get(index).getUsuario()).getPanelAlbumes();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							JPanel panelCentralCardLayout = VentanaPrincipal.getPanelCentral();
							panelCentralCardLayout.add(panelAlbum, "panel foto");
							CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
							cl.show(panelCentralCardLayout, "panel foto");
						}
					}


					return label;
				}
	    	};
	    }
	    
	    
	    
	    
	    
}
