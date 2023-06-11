package photo.tds.controlador;

import photo.tds.dao.UsuarioDAO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import photo.tds.dao.DAOException;
import photo.tds.dao.FactoriaDAO;
import photo.tds.dominio.Usuario;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import photo.tds.dominio.RepositorioPublicaciones;
import photo.tds.dominio.RepositorioUsuarios;

public class Controlador {
	private static Controlador instancia = null;
	
	private Usuario usuarioActual;
	private FactoriaDAO factoria;
	private RepositorioPublicaciones repoPublicaciones;
	private RepositorioUsuarios repoUsuarios;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		repoPublicaciones = RepositorioPublicaciones.INSTANCE;
		repoUsuarios = RepositorioUsuarios.getInstancia();
	}
	
	public static Controlador getInstancia() {
		if(instancia == null) {
			instancia = new Controlador();
		}
		return instancia;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		System.out.println("parametro: "+login);
		return this.repoUsuarios.findUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = this.repoUsuarios.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);

		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);

		this.repoUsuarios.addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		RepositorioUsuarios.getInstancia().removeUsuario(usuario);
		return true;
	}
	
	public boolean crearFoto(String u, String titulo, String descripcion, String path) {

		if (!esUsuarioRegistrado(u)) {
			System.out.println("Usuario no registrado");
			return false;
		}
			
		Usuario user = this.repoUsuarios.findUsuario(u);
		Foto foto = user.crearFoto(titulo, descripcion, path);
		System.out.println("Foto creada: "+foto);
		this.repoPublicaciones.crearPublicacion(foto);
		return true;
	}
	public void borrarFotoSinUsuario(Publicacion publicacion) {
		this.repoPublicaciones.borrarPublicacion(publicacion);
	}
	
	public boolean borrarFoto(String u, Publicacion publicacion) {
		if(!esUsuarioRegistrado(u)) {
			System.out.println("Usuario no registrado");
			return false;
		}
		
		Usuario usuario = this.repoUsuarios.findUsuario(u);
		usuario.borrarFoto(publicacion);
		
		this.repoPublicaciones.borrarPublicacion(publicacion);
		this.repoUsuarios.actualizarUsuario(usuario);
		return true;
	}
	
	public List<Foto> getFotosPerfil(String usuario) throws DAOException{
		return  this.repoPublicaciones.findPublicacionesUsuario(usuario).stream()
				.filter(p -> p instanceof Foto)
				.map(p -> (Foto) p )
				.collect(Collectors.toList());
	}
	public List<Foto> getTodasFotos() throws DAOException{
		return this.repoPublicaciones.findPublicaciones().stream()
				.filter(p -> p instanceof Foto)
				.map(p -> (Foto) p)
				.collect(Collectors.toList());
	}
}

