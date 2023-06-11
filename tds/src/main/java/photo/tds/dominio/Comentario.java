package photo.tds.dominio;

import java.util.Date;

public class Comentario {

	private String texto;
	private String usuario;
	private Date fecha;
	private int id;
	
	public Comentario(String texto, String usuario, Date fecha, int id) {
		this.texto = texto;
		this.usuario = usuario;
		this.fecha = fecha;
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
