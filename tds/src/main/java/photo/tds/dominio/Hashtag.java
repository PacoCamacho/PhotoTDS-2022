package photo.tds.dominio;

public class Hashtag {
	private String nombre;
	
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
	
}
