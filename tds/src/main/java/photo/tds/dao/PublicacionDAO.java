package photo.tds.dao;

import java.util.List;

import photo.tds.dominio.Publicacion;

public interface PublicacionDAO {

	public void create(Publicacion foto);
	public boolean delete(Publicacion foto);
	void update(Publicacion foto);
	public Publicacion get(int id);
	public List<Publicacion> getAll();
	
}

