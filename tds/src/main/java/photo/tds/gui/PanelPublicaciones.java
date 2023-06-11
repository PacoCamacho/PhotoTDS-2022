package photo.tds.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import photo.tds.controlador.Controlador;
import photo.tds.dao.DAOException;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import photo.tds.helpers.ImageListCellRenderer;
import photo.tds.helpers.ImageListCellRendererImagen;
import photo.tds.helpers.Item;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PanelPublicaciones extends JPanel {
	private static List<Foto> listaFotos;
	private JPanel panelPublicaciones;

	
	public JPanel getPanelPublicaciones() {
		return panelPublicaciones;
	}
	/**
	 * Create the panel.
	 * @throws DAOException 
	 * @throws IOException 
	 */
	public PanelPublicaciones(String usuario, JFrame frame){
		
		panelPublicaciones = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{224, 0};
		gridBagLayout.rowHeights = new int[]{2, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelPublicaciones.setLayout(gridBagLayout);
		
		
		try {
			listaFotos = Controlador.getInstancia().getTodasFotos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor,BoxLayout.Y_AXIS));
		frame.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	                panelContenedor.setSize(panelPublicaciones.getSize());
	            }
	        });
		for (Foto foto : listaFotos) {
			PanelFoto panelFoto = null;
			try {
				panelFoto = new PanelFoto(foto, usuario,frame);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  //panelFoto.setPreferredSize(new Dimension(250, 250));
	          panelFoto.setBorder(BorderFactory.createLineBorder(Color.RED));
	          panelFoto.setSize(panelContenedor.getSize());
	          panelContenedor.add(panelFoto.getPanelFoto());
	          
		}
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelPublicaciones.add(scrollPane, gbc_scrollPane);
		
		
		
		
		
		

	}

}