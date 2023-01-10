package photo.tds.dominio;

public class Foto extends Publicacion{
	
	private static final int NUMFOTOS = 1;
	private String path;
	
	public Foto(String path, String titulo, String fecha, String descripcion, int mg ) {
		super(titulo, fecha, descripcion, mg);
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