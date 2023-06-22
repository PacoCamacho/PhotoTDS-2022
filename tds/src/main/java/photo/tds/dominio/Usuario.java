package photo.tds.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;




public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private Date fechaNacimiento;
	private List<Usuario> seguidores;
	private List<Usuario> seguidos;
	private String fotoPerfil;
	private boolean premium;
	
	private List<Publicacion> publicaciones;

	public Usuario(String nombre, String apellidos, String email, String login, String password,
			Date fechaNacimiento, boolean premium) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.premium = premium;
		this.publicaciones = new LinkedList<>();
		this.seguidores = new LinkedList<>();
		this.seguidos = new LinkedList<>();
	}
	
	public void hacersePremium() {
		if(this.premium == false) {
			this.premium = true;
		}
	}
	
	public Usuario getUsuario() {
		return this;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public List<Publicacion> getPublicaciones() {
		return new LinkedList<Publicacion>(publicaciones);
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;

	}
	
	public int getNumSeguidores() {
		return this.seguidores.size();
	}
	
	public int getNumSeguidos() {
		return this.seguidos.size();
	}
	
	public List<Usuario> getSeguidores() {
		return new LinkedList<Usuario>(seguidores);
	}

	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Usuario> getSeguidos() {
		return new LinkedList<Usuario>(seguidos);
	}

	public void setSeguidos(List<Usuario> seguidos) {
		this.seguidos = seguidos;
	}
	
	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public Foto crearFoto(String titulo, String descripcion, String path, List<String> hashtags) {
		List<Hashtag> lh = new ArrayList<>();
		for(String h : hashtags) {
			Hashtag hashtag = Hashtag.crearHashtag("#"+h);
			if(hashtag != null)
				lh.add(hashtag);
		}
		Foto foto = new Foto(path,titulo,new Date(),descripcion, this.getNombre(), lh);
		this.publicaciones.add(foto);
		return foto;
	}
	
	public Foto crearFoto(String titulo, String descripcion,String path) {
		Foto f = new Foto(path, titulo, new Date(), descripcion, this.getLogin());
		this.publicaciones.add(f);
		return f;
	}
	
	public Foto crearFotoAlbum(String path, String titulo,String descripcion,Album album) {
		Foto f = new Foto(path, titulo, new Date(), descripcion, this.getLogin());
		this.publicaciones.add(f);
		album.añadirFoto(f);
		return f;
	}
	
	public Album crearAlbum(String titulo, String descripcion, String path) {
		Album a = new Album(titulo, new Date(), descripcion, this.getLogin(), path);
		this.publicaciones.add(a);
		return a;
	}
	
	public boolean borrarFoto(Publicacion publi) {
		return this.publicaciones.remove(publi);
	}
	
	public boolean seguirUsuario(Usuario u) {
		
		if(this.seguidos.contains(u)) {
			return false;		
			}
		this.seguidos.add(u);
		u.getSeguidores().add(this);
		return true;
	}
	
	public boolean dejarSeguir(Usuario u) {
		if(!this.seguidos.contains(u)) {
			return false;		
			}
		this.seguidos.remove(u);
		u.getSeguidores().remove(u);
		return true;
	}



	




	
	

}
