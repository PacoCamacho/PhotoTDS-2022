package photo.tds.dominio;

public class Comentario {

	private int id;
	private String texto;
	private String autor;
	
	public Comentario(String texto, String autor) {
		this.texto = texto;
		this.autor = autor;
	}

	//setters y getters 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
