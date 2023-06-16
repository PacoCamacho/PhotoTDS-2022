package photo.tds.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Publicacion {

	public int id;
	public String titulo;
	public Date fecha;
	public String descripcion;
	public int mg;
	public String usuario;
	public List<Hashtag> hashtags;
	private List<Comentario> comentarios;
	
	
	public Publicacion(String titulo, Date fecha, String descripcion, int mg, String usuario ) {
		super();
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.mg = mg;
		this.usuario = usuario;
		this.hashtags = this.getHashtagsDescripcion(descripcion);
		this.comentarios = new ArrayList<>();
	}
	public Publicacion(String titulo, Date fecha, String descripcion, int mg, String usuario, List<Hashtag> lh) {
		this(titulo,fecha,descripcion,mg,usuario);
		this.hashtags = lh;
		this.comentarios = new ArrayList<>();
	}
	
	public Publicacion(String titulo, Date fecha, String descripcion, int mg, String usuario, List<Hashtag> lh, List<Comentario> lc) {
		this(titulo,fecha,descripcion,mg,usuario);
		this.hashtags = lh;
		this.comentarios = lc;
	}
	
	public void anadirMeGusta() {
		this.mg = mg + 1;
	}
	
	public void anadirComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	public List<Hashtag> getHashtagsDescripcion(String descripcion) {
	    List<Hashtag> hashtags = new ArrayList<>();

	    // Expresión regular para encontrar los hashtags en el texto
	    Pattern pattern = Pattern.compile("#\\w+");
	    Matcher matcher = pattern.matcher(descripcion);

	    // Iterar sobre los hashtags encontrados
	    while (matcher.find()) {
	        String hashtagText = matcher.group();
	        // Crear un objeto Hashtag y agregarlo a la lista
	        Hashtag hashtag = Hashtag.crearHashtag(hashtagText);
	        hashtags.add(hashtag);
	    }

	    return hashtags;
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


	public List<Hashtag> getHashtags() {
		return new ArrayList<Hashtag>(this.hashtags);
	}


	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	
	public List<Comentario> getComentarios() {
		return new LinkedList<>(comentarios);
	}
	
	
	public abstract String getPath();
	
	public abstract int getNumFotos();
	
	
}
