package photo.tds.dao;

import java.util.List;

import photo.tds.dominio.Comentario;

public interface ComentarioDAO {
	public void create(Comentario comentario);
	public boolean delete(Comentario comentario);
	void update(Comentario comentario);
	public Comentario get(int id);
	public List<Comentario> getAll();
}
