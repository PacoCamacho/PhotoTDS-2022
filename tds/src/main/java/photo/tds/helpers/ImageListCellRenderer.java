package photo.tds.helpers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;


public class ImageListCellRenderer extends DefaultListCellRenderer{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ICON_SIZE = 150; // Tamaño de la imagen en píxeles
	private static final int ANCHO_IMAGEN = 150;
	private static final int ALTO_IMAGEN = 150;
	
	
@Override
public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    Item item = (Item) value;
    ImageIcon icon = new ImageIcon(item.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
    label.setIcon(icon);
    return label;
}





}