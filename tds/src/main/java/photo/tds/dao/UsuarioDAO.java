package photo.tds.dao;

import java.util.List;

import photo.tds.dominio.Usuario;

public interface UsuarioDAO {
	
	void create(Usuario usuario);
	boolean delete(Usuario usuario);
	void deleteAll(List<Usuario> lu);
	void update(Usuario usuario);
	Usuario get(int id);
	List<Usuario> getAll();
	
}