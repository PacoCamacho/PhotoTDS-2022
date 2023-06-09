package photo.tds.helpers;

import java.awt.Image;

public class Item {
		private Image image;
	    private String text;

	    public Item(Image image) {
	        this.image = image;

	    }

	    public Image getImage() {
	        return image;
	    }



	    @Override
	    public String toString() {
	        return text; // Utilizado para mostrar el texto en la lista
	    }
}
