package photo.tds.dominio;

import java.awt.Image;
import java.util.Date;
import java.util.List;

public class Foto extends Publicacion{

	private String path;
	private Image imagen;
	
	public Foto(String path, String titulo, String fecha, String descripcion,int mg, String usuario, List<Hashtag> lh, List<Comentario> comentarios) {
		super(titulo, fecha, descripcion,mg, usuario,lh, comentarios);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, String fecha, String descripcion, String usuario, List<Hashtag> lh, List<Comentario> comentarios) {
		super(titulo, fecha, descripcion,0, usuario,lh, comentarios);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, String fecha, String descripcion, String usuario) {
		super(titulo,fecha,descripcion,0,usuario);
		this.path = path;
	}
	
	public Foto(String path, String titulo, String fecha, String descripcion,int mg, String usuario) {
		super(titulo,fecha,descripcion,mg,usuario);
		this.path = path;
	}
	
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}


}