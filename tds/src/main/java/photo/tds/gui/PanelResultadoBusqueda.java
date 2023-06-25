package photo.tds.gui;

import javax.swing.JPanel;

import photo.tds.dominio.Publicacion;
import photo.tds.dominio.Usuario;

import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

public class PanelResultadoBusqueda extends JPanel {
	
	private static Object objeto;

	/**
	 * Create the panel.
	 */
	public PanelResultadoBusqueda(Object o) {
		objeto = o;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 197, 450, 0};
		gridBagLayout.rowHeights = new int[]{142, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Image image = null;
		String txt = "";
		if(o instanceof Publicacion) {
			try {
				image = ImageIO.read(new File(((Publicacion) o).getPath())).getScaledInstance(100, 100, 0);
				txt = ((Publicacion) o).getTitulo();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (o instanceof Usuario) {
			try {
				image = ImageIO.read(new File(((Usuario) o).getFotoPerfil())).getScaledInstance(100, 100, 0);
				txt = ((Usuario) o).getNombre();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JLabel icono = new JLabel(new ImageIcon(image));
		GridBagConstraints gbc_icono = new GridBagConstraints();
		gbc_icono.insets = new Insets(0, 0, 0, 5);
		gbc_icono.gridx = 1;
		gbc_icono.gridy = 0;
		add(icono, gbc_icono);
		
		JLabel texto = new JLabel(txt);
		GridBagConstraints gbc_texto = new GridBagConstraints();
		gbc_texto.fill = GridBagConstraints.BOTH;
		gbc_texto.gridx = 2;
		gbc_texto.gridy = 0;
		add(texto, gbc_texto);

	}
	
	public boolean esPublicacion(){
		if(objeto instanceof Publicacion) return true;
		else return false;
	}
	
	public Object getObjeto() {
		return objeto;
	}
	

}
