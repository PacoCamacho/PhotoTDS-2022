package photo.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import photo.tds.dao.DAOException;
import photo.tds.dao.FactoriaDAO;

public enum RepositorioPublicaciones {
	INSTANCE;
	private HashMap<Integer,Publicacion> publicacionesporID;
	private HashMap<String,Publicacion> publicacionesporTitulo;
	
	private FactoriaDAO dao;

	private RepositorioPublicaciones() {
		publicacionesporID = new HashMap<Integer,Publicacion>();
		publicacionesporTitulo = new HashMap<String,Publicacion>();
		try {
			dao = FactoriaDAO.getInstancia();
			
			List<Publicacion> listaPublicaciones = dao.getPublicacionDAO().getAll();
			for (Publicacion p : listaPublicaciones) {
				publicacionesporID.put(p.getId(), p);
				publicacionesporTitulo.put(p.getTitulo(), p);
				
			}
		} catch(DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	public List<Publicacion> findPublicaciones() throws DAOException {
		System.out.println("Entro a findPublicaciones");
		System.out.println(this.publicacionesporTitulo.values());
		return new LinkedList<Publicacion>(this.publicacionesporTitulo.values());
	}
	
	public List<Publicacion> findPublicacionesUsuario(String usuario) throws DAOException {
		System.out.println("findPublicacionesUsuario: "+usuario);
		System.out.println(findPublicaciones());
		for (Publicacion p : this.findPublicaciones()) {
			System.out.println("Publicación: "+p.getTitulo());
			System.out.println("Usuario: "+p.getUsuario());
		}
		
		return this.findPublicaciones().stream()
			.filter(p -> p.getUsuario().equals(usuario))
			.collect(Collectors.toList());
	}
	
	public Publicacion findPublicacion(String titulo) {
		return publicacionesporTitulo.get(titulo);
	}

	public Publicacion findPublicacion(int id) {
		return publicacionesporID.get(id);
	}
	
	public void addPublicacion(Publicacion publicacion) {
		publicacionesporID.put(publicacion.getId(), publicacion);
		publicacionesporTitulo.put(publicacion.getTitulo(), publicacion);
	}
	
	public void removeUsuario(Publicacion publicacion) {
		publicacionesporID.remove(publicacion.getId());
		publicacionesporTitulo.remove(publicacion.getTitulo());
	}
	
	public void crearPublicacion(Publicacion publi) {
		this.publicacionesporTitulo.put(publi.getTitulo(), publi);
		this.publicacionesporID.put(publi.getId(), publi);
		
		System.out.println("Añadida publicación: "+publi.getTitulo());
		System.out.println("usuario de la publicacion:" +publi.getUsuario());
		System.out.println(this.publicacionesporTitulo);
	}
	
}
