package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import photo.tds.dominio.Album;

public class PanelAlbum {

	private JPanel contentPane;
	private JFrame frame;
	private String usuario;
	
	private static List<Album> albumes ;
	
	public PanelAlbum(String usuario) {
		super();
		this.usuario = usuario;
		albumes = new LinkedList<>();
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 600);
		frame.getContentPane().setLayout(new BorderLayout(0,0));
		
		
	}
	

}
