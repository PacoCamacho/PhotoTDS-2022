package photo.tds.controlador;

import photo.tds.dao.UsuarioDAO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import photo.tds.dao.DAOException;
import photo.tds.dao.FactoriaDAO;
import photo.tds.dao.PublicacionDAO;
import photo.tds.dominio.Usuario;
import photo.tds.dominio.Foto;
import photo.tds.dominio.RepositorioPublicaciones;
import photo.tds.dominio.RepositorioUsuarios;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDAO factoria;
	private RepositorioPublicaciones repoPublicaciones;
	private RepositorioUsuarios repoUsuarios;
	private UsuarioDAO usuarioDAO;
	private PublicacionDAO publicacionDAO;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		repoPublicaciones = RepositorioPublicaciones.INSTANCE;
		repoUsuarios = RepositorioUsuarios.INSTANCE;
		usuarioDAO = factoria.getUsuarioDAO();
		publicacionDAO = factoria.getPublicacionDAO();
		
	}
	

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return repoUsuarios.findUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = repoUsuarios.findUsuario(nombre);
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
		/* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);

		repoUsuarios.addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		/* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		repoUsuarios.removeUsuario(usuario);
		return true;
	}
	
	public boolean actualizarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;
		usuarioDAO.update(usuario);
		repoUsuarios.updateUsuario(usuario);
		return true;
	}
	
	public boolean crearFoto(Usuario u, String titulo, String descripcion, String path) {

		if (!esUsuarioRegistrado(u.getLogin()))
			return false;

		Foto foto = u.crearFoto(titulo, descripcion, path);

		publicacionDAO.create(foto);
		this.repoPublicaciones.crearPublicacion(foto);
		return true;
	}
	
	public List<Foto> getFotosPerfil(String usuario) throws DAOException{
		return  this.repoPublicaciones.findPublicacionesUsuario(usuario).stream()
				.filter(p -> p instanceof Foto)
				.map(p -> (Foto) p )
				.collect(Collectors.toList());
	}
	
	public boolean seguir(Usuario seguidor, Usuario seguido) {
		if(!(this.esUsuarioRegistrado(seguidor.getLogin()) && this.esUsuarioRegistrado(seguido.getLogin()))) {
			return false;
		}
		if(seguidor.seguirUsuario(seguido)) {
			this.actualizarUsuario(seguido);
			this.actualizarUsuario(seguidor);
			return true;
		}
		return false;
	}
	
	public boolean dejarDeSeguir(Usuario seguidor, Usuario seguido) {
		if(!(this.esUsuarioRegistrado(seguidor.getLogin()) && this.esUsuarioRegistrado(seguido.getLogin()))) {
			return false;
		}
		if(seguidor.dejarSeguir(seguido)) {
			this.actualizarUsuario(seguido);
			this.actualizarUsuario(seguidor);
			return true;
		}
		return false;
	}
}

