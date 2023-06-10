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
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
	public PanelPublicaciones(){
		panelPublicaciones = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{224, 0};
		gridBagLayout.rowHeights = new int[]{2, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelPublicaciones.setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelPublicaciones.add(scrollPane, gbc_scrollPane);
		
		
		try {
			listaFotos = Controlador.getInstancia().getTodasFotos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JList<Item> list = new JList<>();
		scrollPane.setViewportView(list);
		DefaultListModel<Item> model = new DefaultListModel<>();
		list.setModel(model);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setCellRenderer(new ImageListCellRendererImagen());
        list.setVisibleRowCount(-1);
        

		
		for (Foto foto : listaFotos) {
			if(foto instanceof Foto) {
				Image image=null;
				try {
					image = ImageIO.read(new File(foto.getPath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Item item = new Item(image);
				model.addElement(item);
				System.out.println("Se agregó la imagen : "+foto.getTitulo()+" a la lista de publicaciones");
			}
		}
		

	}

}