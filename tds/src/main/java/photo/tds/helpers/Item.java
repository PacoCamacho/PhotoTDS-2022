package photo.tds.helpers;

import java.awt.Image;

import photo.tds.gui.PanelFoto;
import photo.tds.gui.VentanaPerfil;

public class Item {
		private Image image;
	    private String text;
	    private PanelFoto pf;
	    private VentanaPerfil pp;

	    public Item(Image image) {
	        this.image = image;

	    }
	    
	    public Item(PanelFoto pf, Image image) {
	        this.pf = pf;
	        this.image = image;

	    }
	    
	    public Item(VentanaPerfil pp, Image image) {
	        this.pp = pp;
	        this.image = image;

	    }

	    public Image getImage() {
	        return image;
	    }
	    
	    

	    public PanelFoto getPanelFoto() {
	    	return pf;
	    }

	    public VentanaPerfil getPanelPerfil() {
	    	return pp;
	    }

	    @Override
	    public String toString() {
	        return text; // Utilizado para mostrar el texto en la lista
	    }
}
