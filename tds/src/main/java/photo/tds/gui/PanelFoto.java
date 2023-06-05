package photo.tds.gui;

import javax.swing.JPanel;

import photo.tds.dominio.Comentario;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Insets;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PanelFoto extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Foto foto;
	private String usuario;
	private JTextField textFieldComentario;
	private JPanel panelFoto;
	
	public JPanel getPanel() {
		return panelFoto;
	}
	
	/**
	 * Create the panel.
	 */
	public PanelFoto(Foto foto, String usuario) {
		this.foto = foto;
		this.usuario = usuario;
		setPreferredSize(new Dimension(200, 200));
		
		initialize();
	}
	
	private void initialize() {
		panelFoto = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 35, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelFoto.setLayout(gridBagLayout);
		
		JLabel nombreUsuario = new JLabel("Aqui va el nombre del usuario");
		nombreUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
		gbc_nombreUsuario.gridwidth = 2;
		gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuario.gridx = 3;
		gbc_nombreUsuario.gridy = 1;
		panelFoto.add(nombreUsuario, gbc_nombreUsuario);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		panelFoto.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton botonMg = new JButton("<3");
		botonMg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonMg.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_botonMg = new GridBagConstraints();
		gbc_botonMg.insets = new Insets(0, 0, 5, 5);
		gbc_botonMg.gridx = 2;
		gbc_botonMg.gridy = 4;
		panelFoto.add(botonMg, gbc_botonMg);
		
		textFieldComentario = new JTextField();
		GridBagConstraints gbc_textFieldComentario = new GridBagConstraints();
		gbc_textFieldComentario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldComentario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldComentario.gridx = 4;
		gbc_textFieldComentario.gridy = 4;
		panelFoto.add(textFieldComentario, gbc_textFieldComentario);
		textFieldComentario.setColumns(10);
		
		JButton botonComentario = new JButton("Send");
		GridBagConstraints gbc_botonComentario = new GridBagConstraints();
		gbc_botonComentario.insets = new Insets(0, 0, 5, 5);
		gbc_botonComentario.gridx = 5;
		gbc_botonComentario.gridy = 4;
		panelFoto.add(botonComentario, gbc_botonComentario);
		
		JLabel meGustas = new JLabel("0");
		GridBagConstraints gbc_meGustas = new GridBagConstraints();
		gbc_meGustas.insets = new Insets(0, 0, 5, 5);
		gbc_meGustas.gridx = 2;
		gbc_meGustas.gridy = 5;
		panelFoto.add(meGustas, gbc_meGustas);
		
		
		
		
		
		JButton botonBorrar = new JButton("Borrar");
		GridBagConstraints gbc_botonBorrar = new GridBagConstraints();
		gbc_botonBorrar.insets = new Insets(0, 0, 0, 5);
		gbc_botonBorrar.gridx = 5;
		gbc_botonBorrar.gridy = 7;
		panelFoto.add(botonBorrar, gbc_botonBorrar);
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Dibuja la imagen en el panel(hablar con jordi)
	        //g.drawImage(foto.getImagen(), 0, 0, getWidth(), getHeight(), this);
	    }
	
	
	

	

}
