package photo.tds.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album extends Publicacion{

	private List<Foto> fotos;
	private String pathPortada;
	
	public Album(String titulo, Date fecha, String descripcion, String usuario, String path) {
		super(titulo, fecha, descripcion, 0, usuario);
		this.fotos = new ArrayList<Foto>();
		this.pathPortada = path;
	}
	
	public Album(String titulo, Date fecha, String descripcion, int mg,String usuario, List<Foto> fotos) {
		super(titulo, fecha, descripcion, mg, usuario);
		this.fotos = fotos;
	}
	
	public Album(String path,String titulo, Date fecha, String descripcion, int mg,String usuario, List<Foto> fotos, List<Hashtag> lh, List<Comentario> lc) {
		super(titulo, fecha, descripcion, mg, usuario, lh, lc);
		this.fotos = fotos;
		this.pathPortada = path;
	}
	
	public Album(String titulo, Date fecha, String descripcion, int mg,String usuario) {
		super(titulo, fecha, descripcion, mg, usuario);
		this.fotos = new ArrayList<>();
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
//		StringBuilder sb = new StringBuilder();
//        for (Foto foto : fotos) {
//            sb.append(foto.getPath());
//            sb.append(" ");
//        }
//        return sb.toString().trim();
		return this.pathPortada;

	}

	
	public void añadirFoto(Foto foto) {
		fotos.add(foto);
	}
}
