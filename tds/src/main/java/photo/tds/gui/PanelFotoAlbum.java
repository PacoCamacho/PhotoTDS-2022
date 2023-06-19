package photo.tds.gui;

import javax.swing.JPanel;

import photo.tds.controlador.Controlador;
import photo.tds.dao.DAOException;
import photo.tds.dominio.Album;
import photo.tds.dominio.Comentario;
import photo.tds.dominio.Conversor;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
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

public class PanelFotoAlbum extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Foto foto;
	private String usuario;
	private JTextField textFieldComentario;
	private JPanel panelFoto;
	private JList<String> listaComentarios;
	private DefaultListModel<String> modeloComentarios;
	private static Album album;
	
	public JPanel getPanelFoto() {
		return panelFoto;
	}
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public PanelFotoAlbum(Foto foto, String usuario,Album album) throws IOException {
		this.foto = foto;
		this.usuario = usuario;
		this.album = album;
		//setPreferredSize(new Dimension(400, 400));
		List<Comentario> comentarios = foto.getComentarios();
		modeloComentarios = new DefaultListModel<>();

		for (Comentario comentario : comentarios) {
		    modeloComentarios.addElement(comentario.getTexto());
		}
		
		initialize();
	}
	
	public void actualizarComentarios(List<Comentario> comentarios) {
	    modeloComentarios.clear();
	    for (Comentario comentario : comentarios) {
	        modeloComentarios.addElement(this.usuario+ " <"+Conversor.DateToString(comentario.getFecha())+">:"+ comentario.getTexto());
	    }
	}
	
	private void initialize() throws IOException {
		System.out.println("PanelFoto creado");
		
		panelFoto = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 35, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFoto.setLayout(gridBagLayout);
		
//		frame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                panelFoto.setSize(frame.getSize());
//            }
//        });
		
		JLabel nombreUsuario = new JLabel(foto.getUsuario());
		nombreUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
		gbc_nombreUsuario.gridwidth = 2;
		gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuario.gridx = 3;
		gbc_nombreUsuario.gridy = 1;
		panelFoto.add(nombreUsuario, gbc_nombreUsuario);
		
		Image image = ImageIO.read(new File(foto.getPath())).getScaledInstance(300, 300, ALLBITS);
		
		JLabel labelFoto = new JLabel(new ImageIcon(image));
		//labelFoto.setBounds(10,10,30,30);
		GridBagConstraints gbc_labelFoto = new GridBagConstraints();
		gbc_labelFoto.gridheight = 4;
		gbc_labelFoto.gridwidth = 4;
		gbc_labelFoto.insets = new Insets(0, 0, 5, 5);
		gbc_labelFoto.gridx = 2;
		gbc_labelFoto.gridy = 2;
		labelFoto.setSize(panelFoto.getWidth()-20,panelFoto.getHeight()-20);
		panelFoto.add(labelFoto, gbc_labelFoto);
		
		JLabel meGustas = new JLabel(String.valueOf(foto.getMg()));
		GridBagConstraints gbc_meGustas = new GridBagConstraints();
		gbc_meGustas.insets = new Insets(0, 0, 5, 5);
		gbc_meGustas.gridx = 2;
		gbc_meGustas.gridy = 6;
		panelFoto.add(meGustas, gbc_meGustas);
		
		JLabel lblNewLabel = new JLabel("Comenta en la publicacion!");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 6;
		panelFoto.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton botonComentario = new JButton("Comentar");
		botonComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Has comentado: "+ textFieldComentario.getText());
				Controlador.getInstancia().comentarPublicacion(usuario, foto, textFieldComentario.getText());;
				actualizarComentarios(foto.getComentarios());
			}
		});
		
		JButton botonMg = new JButton("<3");
		botonMg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Le has dado Like");
				Controlador.getInstancia().darMeGusta(foto);
			}
		});
		botonMg.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_botonMg = new GridBagConstraints();
		gbc_botonMg.insets = new Insets(0, 0, 5, 5);
		gbc_botonMg.gridx = 2;
		gbc_botonMg.gridy = 7;
		panelFoto.add(botonMg, gbc_botonMg);
		
		textFieldComentario = new JTextField();
		GridBagConstraints gbc_textFieldComentario = new GridBagConstraints();
		gbc_textFieldComentario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldComentario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldComentario.gridx = 4;
		gbc_textFieldComentario.gridy = 7;
		panelFoto.add(textFieldComentario, gbc_textFieldComentario);
		textFieldComentario.setColumns(10);
		GridBagConstraints gbc_botonComentario = new GridBagConstraints();
		gbc_botonComentario.insets = new Insets(0, 0, 5, 5);
		gbc_botonComentario.gridx = 5;
		gbc_botonComentario.gridy = 7;
		panelFoto.add(botonComentario, gbc_botonComentario);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 8;
		panelFoto.add(scrollPane, gbc_scrollPane);
		
		modeloComentarios = new DefaultListModel<>();
		listaComentarios = new JList<>(modeloComentarios);
		scrollPane.setViewportView(listaComentarios);
		
		JButton botonVolver = new JButton("Volver");
		GridBagConstraints gbc_botonVolver = new GridBagConstraints();
		gbc_botonVolver.insets = new Insets(0, 0, 0, 5);
		gbc_botonVolver.gridx = 0;
		gbc_botonVolver.gridy = 10;
		panelFoto.add(botonVolver, gbc_botonVolver);
		
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel panelAlbum = null;
						try {
							panelAlbum = new PanelAlbum(album, usuario).getPanelAlbumes();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JPanel panelCentralCardLayout = VentanaPrincipal.getPanelCentral();
						panelCentralCardLayout.add(panelAlbum, "panel foto");
						CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
						cl.show(panelCentralCardLayout, "panel foto");
						
					}		
				});
					
			}
				
		});
		
		
		
		JButton botonBorrar = new JButton("Borrar");
		GridBagConstraints gbc_botonBorrar = new GridBagConstraints();
		gbc_botonBorrar.gridx = 6;
		gbc_botonBorrar.gridy = 10;
		panelFoto.add(botonBorrar, gbc_botonBorrar);
		
		botonBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controlador.getInstancia().borrarFoto(usuario,foto);
				
			}
		});
		
		panelFoto.setVisible(true);
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Dibuja la imagen en el panel
	        Image resizedImage = foto.getImagen().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING);

	        g.drawImage(resizedImage, 0, 0, 150, 150, this);
	    }
	
	
	

	

}

