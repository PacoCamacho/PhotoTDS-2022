
package photo.tds.dominio;

import java.time.LocalDate;
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
	private String fechaNacimiento;
	private String fotoPerfil;
	
	private List<Publicacion> publicaciones;
	private List<Usuario> seguidores;
	private List<Usuario> seguidos;

	public Usuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		
		this.publicaciones = new LinkedList<>();
		this.seguidores = new LinkedList<>();
		this.seguidos = new LinkedList<>();
	}
	

	public Usuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento, List<Usuario> seguidores, List<Usuario> seguidos) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.publicaciones = new LinkedList<Publicacion>();
		this.seguidores = seguidores;
		this.seguidos = seguidos;
	}
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;

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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	

	public int getNumSeguidores() {
		return this.seguidores.size();
	}
	
	public int getNumSeguidos() {
		return this.seguidos.size();
	}
	
	
	
	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(List<Usuario> seguidos) {
		this.seguidos = seguidos;
	}

	public Foto crearFoto(String titulo, String descripcion, String path) {
		String fecha = LocalDate.now().toString();
		Foto foto = new Foto(path, titulo, fecha, descripcion, this.login);
		this.publicaciones.add(foto);
		return foto;
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
