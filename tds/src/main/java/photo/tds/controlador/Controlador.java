package photo.tds.controlador;

import photo.tds.dao.UsuarioDAO;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import photo.tds.dao.DAOException;
import photo.tds.dao.FactoriaDAO;
import photo.tds.dominio.Usuario;
import photo.tds.dominio.Album;
import photo.tds.dominio.Comentario;
import photo.tds.dominio.Conversor;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import photo.tds.dominio.RepositorioPublicaciones;
import photo.tds.dominio.RepositorioUsuarios;

public class Controlador implements PropertyChangeListener{
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
		repoPublicaciones = RepositorioPublicaciones.getInstancia();
		repoUsuarios = RepositorioUsuarios.getInstancia();
		umu.tds.fotos.CargadorFotos.getInstancia().addFotosListener(this);
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
			Date fechaNacimiento,String path) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento, false, path);

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
	public boolean actualizarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.update(usuario);

		RepositorioUsuarios.getInstancia().actualizarUsuario(usuario);
		return true;
	}
	
	
	public void borrarTodosUsuarios() {
		RepositorioUsuarios.getInstancia().removeTodosUsuarios();
	}
	
	public boolean crearFoto(String u, String titulo, String descripcion, String path) {

		if (!esUsuarioRegistrado(u)) {
			System.out.println("Usuario no registrado");
			return false;
		}
			
		Usuario user = this.repoUsuarios.findUsuario(u);
		Foto foto = user.crearFoto(titulo, descripcion, path);
		System.out.println("Foto creada: "+foto + " fecha: " + Conversor.DateToString(foto.getFecha()));
		this.repoPublicaciones.crearPublicacion(foto);
		return true;
	}
	
	public boolean crearAlbum(String u, String titulo, String descripcion, String path) {

		if (!esUsuarioRegistrado(u)) {
			System.out.println("Usuario no registrado");
			return false;
		}
			
		Usuario user = this.repoUsuarios.findUsuario(u);
		Album album = user.crearAlbum(titulo, descripcion, path);
		this.repoPublicaciones.crearPublicacion(album);
		return true;
	}
	
	public boolean añadirFotoAlbum(String usuario,String titulo, String descripcion, String path, Album album) {
		if (!esUsuarioRegistrado(usuario)) {
			System.out.println("Usuario no registrado");
			return false;
		}
		Usuario user = this.repoUsuarios.findUsuario(usuario);
		Foto foto = user.crearFotoAlbum(path, titulo, descripcion,album);
		this.repoPublicaciones.crearPublicacion(foto);
		this.repoPublicaciones.actualizarPublicacion(album);
		System.out.println("Se agregó al album "+album.titulo+" la imagen: "+foto+" con nombre: "+foto.getTitulo()+" y con path: "+foto.getPath());
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
	
	public boolean darMeGusta(Publicacion publicacion) {
		publicacion.anadirMeGusta();
		this.repoPublicaciones.actualizarPublicacion(publicacion);
		return true;
	}
	
	public void comentarPublicacion(String usuario, Foto foto, String texto) {
		if (!esUsuarioRegistrado(usuario)) {
			System.out.println("Usuario no registrado");
			return ;
		}
		Comentario comentario = new Comentario(texto, usuario, new Date());
		factoria.getComentarioDAO().create(comentario);
		foto.anadirComentario(comentario);
		this.repoPublicaciones.actualizarPublicacion(foto);
	}
	
	public boolean seguirUsuario(String usuarioSeguidor, String usuarioSeguido) {
		if (!esUsuarioRegistrado(usuarioSeguidor)) {
			System.out.println("Usuario no registrado");
			return false;
		}
		if (!esUsuarioRegistrado(usuarioSeguido)) {
			System.out.println("Usuario no registrado");
			return false;
		}
		Usuario seguidor = this.repoUsuarios.findUsuario(usuarioSeguidor);
		Usuario seguido = this.repoUsuarios.findUsuario(usuarioSeguido);
		
		seguidor.seguirUsuario(seguido);
		repoUsuarios.actualizarUsuario(seguido);
		repoUsuarios.actualizarUsuario(seguidor);
		
		return true;
	}
	
	public boolean hacerPremium(String usuario) {
		if (!esUsuarioRegistrado(usuario)) {
			System.out.println("Usuario no registrado");
			return false;
		}
		
		Usuario user = this.repoUsuarios.findUsuario(usuario);
		user.hacersePremium();
		
		return true;
	}
	public List<Object> buscar(/*String u,*/ String busqueda) throws DAOException {
			/*if (!esUsuarioRegistrado(u)) {
				System.out.println("Usuario no registrado");
				return null;
			}*/
		    if (busqueda.startsWith("#")) {
		        return this.getPublicacionesHashtag(busqueda);
		    } else {
		    	
		    	return this.getUsuariosCadena(busqueda);
		    	
		    }
		
		}
	
	public List<Object> getUsuariosCadena(String cadena) throws DAOException {
	    return this.repoUsuarios.findUsuarios().stream()
	            .filter(usuario -> usuario.getLogin().startsWith(cadena))
	            .collect(Collectors.toList());
	}

	public List<Object> getPublicacionesHashtag(String cadena) throws DAOException {
	    return this.repoPublicaciones.findPublicaciones().stream()
	            .filter(publicacion -> publicacion.getHashtags()
	                    .stream()
	                    .anyMatch(hashtag -> hashtag.getNombre().equals(cadena)))
	            .collect(Collectors.toList());
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

	public List<Album> getAlbumes(String usuario) throws DAOException {
		return this.repoPublicaciones.findPublicaciones().stream()
				.filter(p -> p instanceof Album)
				.map(p -> (Album) p)
				.collect(Collectors.toList());
	}

	public void subirFotos(String usuario, String fichero) {
		if (!esUsuarioRegistrado(usuario)) {
			System.out.println("Usuario no registrado");
			return;
		}
		umu.tds.fotos.CargadorFotos.getInstancia().setArchivo(fichero);
		
		
	} 

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		umu.tds.fotos.Fotos fotos = (umu.tds.fotos.Fotos) evt.getNewValue();
		Usuario u = this.getUsuarioActual();
		
		for (umu.tds.fotos.Foto f : fotos.getFoto()) {
			System.out.println("Titulo: "+ f.getTitulo()+ ", Descripcion: "+f.getDescripcion());
			Foto foto = u.crearFoto(f.getTitulo(), f.getDescripcion(), f.getPath(), f.getHashTags().get(0).getHashTag());

			this.repoPublicaciones.crearPublicacion(foto);
			

		}
		
	}
}

