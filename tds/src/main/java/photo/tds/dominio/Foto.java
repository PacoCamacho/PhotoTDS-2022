package photo.tds.dominio;


public class Foto extends Publicacion{

	private String path;
	
	public Foto(String path, String titulo, String fecha, String descripcion, int mg, String creador ) {
		super(titulo, fecha, descripcion, mg, creador);
		this.path = path;
		
	}
	
	public Foto(String path, String titulo, String fecha, String descripcion, String creador ) {
		super(titulo, fecha, descripcion, creador);
		this.path = path;
		
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

}