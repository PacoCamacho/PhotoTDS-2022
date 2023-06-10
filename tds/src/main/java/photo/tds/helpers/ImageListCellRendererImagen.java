package photo.tds.helpers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class ImageListCellRendererImagen extends DefaultListCellRenderer{
	  private static final int IMAGE_WIDTH = 150;
	    private static final int IMAGE_HEIGHT = 150;

	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        if (value instanceof Item) {
	            Item item = (Item) value;
	            Image image = item.getImage();
	            Image scaledImage = image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
	            ImageIcon icon = new ImageIcon(scaledImage);
	            setIcon(icon);
	        } else {
	            setIcon(null);
	        }

	        setText(value.toString());
	        setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));

	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }

	        setEnabled(list.isEnabled());
	        setFont(list.getFont());
	        setOpaque(true);

	        return this;
	    }
}
