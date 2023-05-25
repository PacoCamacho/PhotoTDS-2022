package photo.tds.dominio;

public class Hashtag {
	private String nombre;
	private int codigo;
	
	private Hashtag(String nombre) {
		this.nombre = nombre;
	}
	
	public static Hashtag crearHashtag(String nombre) {
		if(nombre.length() <= 15) {
			return new Hashtag(nombre);
		}
		else return null;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
