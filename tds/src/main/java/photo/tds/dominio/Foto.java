package photo.tds.dominio;

import java.util.Date;
import java.util.List;

public class Foto extends Publicacion{
	
	private static final int NUMFOTOS = 1;
	private String path;
	
	public Foto(String path, String titulo, Date fecha, String descripcion,int mg, Usuario usuario, List<Hashtag> lh ) {
		super(titulo, fecha, descripcion,mg, usuario,lh);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion, Usuario usuario, List<Hashtag> lh ) {
		super(titulo, fecha, descripcion,0, usuario,lh);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion, Usuario usuario) {
		super(titulo,fecha,descripcion,0,usuario);
		this.path = path;
	}
	
	public Foto(String path, String titulo, Date fecha, String descripcion,int mg, Usuario usuario) {
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
	
	
	
}