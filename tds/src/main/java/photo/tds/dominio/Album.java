package photo.tds.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album extends Publicacion{

	private List<Foto> fotos;
	

	public Album(String titulo, String fecha, String descripcion, int mg, String creador, List<Foto> fotos) {
		super(titulo, fecha, descripcion, mg, creador);
		this.fotos = fotos;
	}
	

	public List<Foto> getFotos() {
		return this.fotos;
	}
	
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	public int getNumFotos() {
		return fotos.size();
	}
	
	public String getPath(){
		StringBuilder sb = new StringBuilder();
        for (Foto foto : fotos) {
            sb.append(foto.getPath());
            sb.append(" ");
        }
        return sb.toString().trim();
	}
}
