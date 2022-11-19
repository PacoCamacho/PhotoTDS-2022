package umu.tds.dominio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Publicacion {

	public String titulo;
	public Date fecha;
	public String descripcion;
	public int mg;
	public List<String> hashtags;
	
	
	public Publicacion(String titulo, Date fecha, String descripcion, int mg, List<String> hashtags) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.mg = mg;
		this.hashtags = hashtags;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
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
	
	
}
