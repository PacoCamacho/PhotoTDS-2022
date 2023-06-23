package photo.tds.gui;

import javax.swing.JPanel;

import photo.tds.controlador.Controlador;
import photo.tds.dao.DAOException;
import photo.tds.dominio.Album;
import photo.tds.dominio.Comentario;
import photo.tds.dominio.Conversor;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Album;
import photo.tds.dominio.Publicacion;
import photo.tds.helpers.Item;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PanelAlbum extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Album album;
	private String usuario;
	private JTextField textFieldComentario;
	private JPanel panelAlbum;
	private static List<Foto> listaFotos;
	private static Image fotoAlbum;


	
	public JPanel getPanelAlbumes() {
		return panelAlbum;
	}
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public PanelAlbum(Album albumm, String usuario) throws IOException {
		album = albumm;
		this.usuario = usuario;
		fotoAlbum = ImageIO.read(new File(album.getPath())).getScaledInstance(100, 100,ALLBITS);
		//setPreferredSize(new Dimension(400, 400));
		listaFotos = album.getFotos();
		
		initialize();
	}

	private void initialize() throws IOException {
		System.out.println("Panelalbum creado");
		
		panelAlbum = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{64, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAlbum.setLayout(gridBagLayout);
		
//		frame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                panelalbum.setSize(frame.getSize());
//            }
//        });
		
		JLabel nombreUsuario = new JLabel(album.getUsuario());
		nombreUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
		gbc_nombreUsuario.gridwidth = 2;
		gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuario.gridx = 2;
		gbc_nombreUsuario.gridy = 1;
		panelAlbum.add(nombreUsuario, gbc_nombreUsuario);
		
		
		
		JLabel labelAlbum = new JLabel(new ImageIcon(fotoAlbum));
		//labelalbum.setBounds(10,10,30,30);
		GridBagConstraints gbc_labelalbum = new GridBagConstraints();
		gbc_labelalbum.gridheight = 2;
		gbc_labelalbum.insets = new Insets(0, 0, 5, 5);
		gbc_labelalbum.gridx = 1;
		gbc_labelalbum.gridy = 2;
		labelAlbum.setSize(panelAlbum.getWidth()-20,panelAlbum.getHeight()-20);
		panelAlbum.add(labelAlbum, gbc_labelalbum);
		
		JButton botonNuevaFoto = new JButton("Añadir Foto");
		GridBagConstraints gbc_botonNuevaFoto = new GridBagConstraints();
		gbc_botonNuevaFoto.insets = new Insets(0, 0, 5, 5);
		gbc_botonNuevaFoto.gridx = 4;
		gbc_botonNuevaFoto.gridy = 3;
		panelAlbum.add(botonNuevaFoto, gbc_botonNuevaFoto);
		
		botonNuevaFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaNuevaFotoAlbum nuevaFoto = new VentanaNuevaFotoAlbum(usuario,album);
				nuevaFoto.mostrarVentana(panelAlbum);
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 6;
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 4;
        panelAlbum.add(scrollPane, gbc_scrollPane);
        
       
        	
       
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
		
		JButton botonVolver = new JButton("Volver");
		GridBagConstraints gbc_botonVolver = new GridBagConstraints();
		gbc_botonVolver.insets = new Insets(0, 0, 0, 5);
		gbc_botonVolver.gridx = 0;
		gbc_botonVolver.gridy = 10;
		panelAlbum.add(botonVolver, gbc_botonVolver);
		
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaPrincipal.iniciarPanelPerfil(false);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}		
		});
		

		
		
		
		
		
		JButton botonBorrar = new JButton("Borrar");
		GridBagConstraints gbc_botonBorrar = new GridBagConstraints();
		gbc_botonBorrar.gridx = 5;
		gbc_botonBorrar.gridy = 10;
		panelAlbum.add(botonBorrar, gbc_botonBorrar);
		

		
		panelAlbum.setVisible(true);
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Dibuja la imagen en el panel
	        Image resizedImage = fotoAlbum.getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING);

	        g.drawImage(resizedImage, 0, 0, 150, 150, this);
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
							JPanel panelFotoAlbum = null;
							try {
								panelFotoAlbum = new PanelFotoAlbum(listaFotos.get(index), listaFotos.get(index).getUsuario(),album).getPanelFoto();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							JPanel panelCentralCardLayout = VentanaPrincipal.getPanelCentral();
							panelCentralCardLayout.add(panelFotoAlbum, "panel foto");
							CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
							cl.show(panelCentralCardLayout, "panel foto");
						}
					}


					return label;
				}
	    	};
	    }
	
	
	

	

}
