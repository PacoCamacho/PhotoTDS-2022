package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import photo.tds.controlador.Controlador;
import photo.tds.dominio.Album;
import java.awt.Toolkit;

public class VentanaNuevaFotoAlbum {
	private JFrame frame;
	private JTextField textFieldTitulo;
	private JTextField textFieldDescripcion;
	private String usuario;
	private String path;
	private Album album;
	
	/**
	 * Create the application.
	 */
	public VentanaNuevaFotoAlbum(String usuario,Album album) {
		this.usuario = usuario;
		this.album = album;
		System.out.println(this.usuario);
		initialize();
	}
	
	
	public void esconderVentana() {
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);
	}
	
	public void mostrarVentana(Component c) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(c);
	}
	
	public String getPath() {
		return this.path;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaNuevaFotoAlbum.class.getResource("/photo/tds/imagenes/PhotoTDS_logo-1.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nueva Publicación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{0, 0, 202, 80, 0, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblNewLabel_1 = new JLabel("* Selecciona la foto:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelCentral.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton selectorFoto = new JButton("Selecciona");
		selectorFoto.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_selectorFoto = new GridBagConstraints();
		gbc_selectorFoto.anchor = GridBagConstraints.WEST;
		gbc_selectorFoto.insets = new Insets(0, 0, 5, 5);
		gbc_selectorFoto.gridx = 2;
		gbc_selectorFoto.gridy = 1;
		panelCentral.add(selectorFoto, gbc_selectorFoto);
		selectorFoto.addActionListener(e -> {
			JFileChooser selector = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo imágenes jpg y GIF","jpg","gif");
			selector.setFileFilter(filtro);
			int aceptado = selector.showOpenDialog(null);
			if(aceptado == JFileChooser.APPROVE_OPTION) {
				System.out.println("Has seleccionado esta imagen:"+ selector.getSelectedFile());
				path = selector.getSelectedFile().getPath();
			}
			
		});
		
		JLabel lblNewLabel_2 = new JLabel("* Título:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panelCentral.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldTitulo = new JTextField();
		GridBagConstraints gbc_textFieldTitulo = new GridBagConstraints();
		gbc_textFieldTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitulo.gridx = 2;
		gbc_textFieldTitulo.gridy = 2;
		panelCentral.add(textFieldTitulo, gbc_textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Descripción:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panelCentral.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textFieldDescripcion = new JTextField();
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcion.gridx = 2;
		gbc_textFieldDescripcion.gridy = 3;
		panelCentral.add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JButton BotonSubirFoto = new JButton("Subir");
		GridBagConstraints gbc_BotonSubirFoto = new GridBagConstraints();
		gbc_BotonSubirFoto.insets = new Insets(0, 0, 0, 5);
		gbc_BotonSubirFoto.gridx = 2;
		gbc_BotonSubirFoto.gridy = 5;
		panelCentral.add(BotonSubirFoto, gbc_BotonSubirFoto);
		BotonSubirFoto.addActionListener(e -> {
			String titulo = textFieldTitulo.getText();
			if (titulo.length() == 0) {
				VentanaError ve = new VentanaError("No existe título para la publicación");
				ve.mostrarVentana(frame);
				return;
			}else if(album.getFotos().size()>16) {
				VentanaError ve = new VentanaError("No se pueden añadir mas de 16 fotos a un álbum");
				ve.mostrarVentana(frame);
			}
			this.esconderVentana();
			System.out.println("le doy a subir y entro a crear foto del controlador");
			System.out.println("this.usuario= "+this.usuario);
			Controlador.getInstancia().añadirFotoAlbum(this.usuario, titulo, textFieldDescripcion.getText(), path,album);
			//crear foto y añadirla a album
			
				
			
			
			
		});
	}
}
