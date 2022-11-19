package umu.tds.dominio;

import java.util.Date;

public class Notificacion {

	public Date fecha;
	
	public Notificacion(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
