package photo.tds.dominio;

import java.util.List;

public class Album extends Publicacion{

	private List<Foto> fotos;
	
	public Album(String titulo, String fecha, String descripcion, int mg, List<Foto> fotos) {
		super(titulo, fecha, descripcion, mg);
		this.fotos = fotos;
	}
	
	public Album(String titulo, String fecha, String descripcion,List<String> paths, int mg ) {
		super(titulo, fecha, descripcion, mg);
		for(String foto : paths) {
			Foto foton = new Foto(foto, titulo, fecha, descripcion, mg);
			this.fotos.add(foton);
		}
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
