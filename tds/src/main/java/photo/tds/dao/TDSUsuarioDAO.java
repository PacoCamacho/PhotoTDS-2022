package photo.tds.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import photo.tds.dominio.Publicacion;
import photo.tds.dominio.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class TDSUsuarioDAO implements UsuarioDAO {

	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String SEGUIDORES = "seguidores";
	private static final String SEGUIDOS = "seguidos";
	private static final String PUBLICACIONES = "publicaciones";


	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);

		
		//Añadir al usuario seguidores y seguidos
		String seguidoresIds = servPersistencia.recuperarPropiedadEntidad(eUsuario, SEGUIDORES);
		String seguidosIds = servPersistencia.recuperarPropiedadEntidad(eUsuario, SEGUIDOS);
		String publicacionesIds = servPersistencia.recuperarPropiedadEntidad(eUsuario, PUBLICACIONES);
		List<Usuario> seguidores = this.getUsuariosPorIds(seguidoresIds);
		List<Usuario> seguidos = this.getUsuariosPorIds(seguidosIds);


		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento, seguidores, seguidos);
		usuario.setId(eUsuario.getId());
		usuario.setPublicaciones(this.getPublicacionesPorIds(publicacionesIds));

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento()),
				new Propiedad(SEGUIDORES, this.usuariosConvertidosAIds(usuario.getSeguidores())),
				new Propiedad(SEGUIDOS, this.usuariosConvertidosAIds(usuario.getSeguidos())),
				new Propiedad(PUBLICACIONES, this.publicacionesConvertidasAIds(usuario.getPublicaciones()))
				
				)));
		return eUsuario;
	}

	public void create(Usuario usuario) {
		System.out.println(usuario);
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getLogin());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			} else if (prop.getNombre().equals(SEGUIDORES)) {
				prop.setValor(this.usuariosConvertidosAIds(usuario.getSeguidores()));
			} else if (prop.getNombre().equals(SEGUIDOS)) {
				prop.setValor(this.usuariosConvertidosAIds(usuario.getSeguidos()));
			} else if (prop.getNombre().equals(PUBLICACIONES)) {
				prop.setValor(this.publicacionesConvertidasAIds(usuario.getPublicaciones()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Usuario get(int id) {
		//Si está en el PoolDAO
		if (PoolDAO.getUnicaInstancia().contiene(id))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		//Si no está, accedemos al servico de persistencia, lo recuperamos y lo añadimos al pool
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		Usuario usuario = entidadToUsuario(eUsuario);
		
		PoolDAO.getUnicaInstancia().addObjeto(id, usuario);
		return usuario;
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}
	
	
	public List<Usuario> getUsuariosPorIds(String ListaIds) {
		
		List<Usuario> usuarios = new LinkedList<Usuario>();
		if(ListaIds == null) return usuarios;
		String[] ids = ListaIds.split(" ");
		
		for (String id : ids) {
			int usuarioId = Integer.parseInt(id);
			usuarios.add(this.get(usuarioId));
		}
		    
		return usuarios;
		
	}
	
	public String usuariosConvertidosAIds(List<Usuario> usuarios) {
		
		if(usuarios.isEmpty()) return "";
		
		StringBuilder cadenaIds = new StringBuilder();

	    for (Usuario u : usuarios) {
	        // Obtener el ID del usuario y añadirlo a la cadena
	        cadenaIds.append(u.getId()).append(" ");
	    }

	    // Eliminar el último espacio en blanco si existe
	    if (cadenaIds.length() > 0) {
	        cadenaIds.deleteCharAt(cadenaIds.length() - 1);
	    }

	    return cadenaIds.toString();
	}
	
	public List<Publicacion> getPublicacionesPorIds(String ListaIds) {
		
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();
		if(ListaIds==null || ListaIds.equals("")) return publicaciones;
		String[] ids = ListaIds.split(" ");
		
		for (String id : ids) {
			int publicacionId = Integer.parseInt(id);
			try {
				publicaciones.add(TDSFactoriaDAO.getInstancia().getPublicacionDAO().get(publicacionId));
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    
		return publicaciones;
		
	}
	
	public String publicacionesConvertidasAIds(List<Publicacion> publicaciones) {
		
		if(publicaciones.isEmpty())return "";
		
		StringBuilder cadenaIds = new StringBuilder();

	    for (Publicacion p : publicaciones) {
	        // Obtener el ID de la publicacion y añadirlo a la cadena
	        cadenaIds.append(p.getId()).append(" ");
	    }

	    // Eliminar el último espacio en blanco si existe
	    if (cadenaIds.length() > 0) {
	        cadenaIds.deleteCharAt(cadenaIds.length() - 1);
	    }

	    return cadenaIds.toString();
	}

}

