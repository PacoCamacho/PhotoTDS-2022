package photo.tds.dominio;

import java.util.LinkedList;
import java.util.List;

public abstract class Publicacion {

	public int id;
	public String titulo;
	public String fecha;
	public String descripcion;
	public int mg;
	public List<String> hashtags;
	
	
	public Publicacion(String titulo, String fecha, String descripcion, int mg ) {
		this.id = 0;
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.mg = mg;
		this.hashtags = new LinkedList<String>();
	}


	public int getId() {
		return id;
	}
	
	public void SetId(int id) {
		this.id = id;
	}
	
	
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getMg() {
		return mg;
	}


	public void setMg(int mg) {
		this.mg = mg;
	}


	public List<String> getHashtags() {
		return new LinkedList<String>(this.hashtags);
	}


	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}
	
	public abstract String getPath();
	
	public abstract int getNumFotos();
	
	
	
}
