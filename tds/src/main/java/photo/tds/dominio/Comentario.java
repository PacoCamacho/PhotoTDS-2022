package photo.tds.dominio;

import java.util.Date;

public class Comentario {

	private String texto;
	private String usuario;
	private Date fecha;
	
	public Comentario(String texto, String usuario, Date fecha) {
		this.texto = texto;
		this.usuario = usuario;
		this.fecha = fecha;
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
	
}
