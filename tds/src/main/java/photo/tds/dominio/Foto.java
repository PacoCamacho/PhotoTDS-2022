package photo.tds.dominio;

import java.awt.Image;
import java.util.Date;
import java.util.List;

public class Foto extends Publicacion{
	
	private static final int NUMFOTOS = 1;
	private String path;
	private Image imagen;
	
	public Foto(String path, String titulo, Date fecha, String descripcion,int mg, String usuario, List<Hashtag> lh, List<Comentario> lc) {
		super(titulo, fecha, descripcion,mg, usuario,lh, lc);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion, String usuario, List<Hashtag> lh) {
		super(titulo, fecha, descripcion,0, usuario,lh);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion, String usuario, List<Hashtag> lh, List<Comentario> lc ) {
		super(titulo, fecha, descripcion,0, usuario,lh, lc);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion, String usuario) {
		super(titulo,fecha,descripcion,0,usuario);
		this.path = path;
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion,int mg, String usuario) {
		super(titulo,fecha,descripcion,mg,usuario);
		this.path = path;
	}
	
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int getNumFotos() {
		return NUMFOTOS;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	
	
}