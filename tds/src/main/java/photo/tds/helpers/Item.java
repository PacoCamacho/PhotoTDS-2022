package photo.tds.helpers;

import java.awt.Image;

import photo.tds.gui.PanelFoto;
import photo.tds.gui.VentanaPerfil;

public class Item {
		private Image image;
	    private String text;
	    private PanelFoto pf;
	    private VentanaPerfil pp;
	    private String nombre;
	    private String username;

	    public Item(Image image) {
	        this.image = image;

	    }
	    
	    public Item(PanelFoto pf, Image image, String nombre) {
	        this.pf = pf;
	        this.image = image;
	        this.nombre = nombre;
	    }
	    
	    public Item(VentanaPerfil pp, Image image, String username) {
	        this.pp = pp;
	        this.image = image;
	        this.username = username;
	    }

	    public Image getImage() {
	        return image;
	    }
	    
	    public String getNombreFoto() {
	    	return nombre;
	    }
	    
	    public String getUsername() {
	    	return username;
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
