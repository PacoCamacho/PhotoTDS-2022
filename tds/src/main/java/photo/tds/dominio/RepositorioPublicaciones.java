package photo.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
		return new LinkedList<Publicacion>(publicacionesporID.values());
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
	
}
