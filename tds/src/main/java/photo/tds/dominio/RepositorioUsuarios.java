package photo.tds.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import photo.tds.dao.DAOException;
import photo.tds.dao.FactoriaDAO;
import photo.tds.dao.TDSUsuarioDAO;
import photo.tds.dao.UsuarioDAO;

public class RepositorioUsuarios {
	private FactoriaDAO dao;
	private UsuarioDAO persistenciaUsuario;
	private static RepositorioUsuarios instancia = null;
	private HashMap<Integer, Usuario> usuariosPorID;
	private HashMap<String, Usuario> usuariosPorLogin;

	private RepositorioUsuarios (){
		
		
		try {
			dao = FactoriaDAO.getInstancia();
			usuariosPorID = new HashMap<Integer, Usuario>();
			usuariosPorLogin = new HashMap<String, Usuario>();
			persistenciaUsuario = dao.getUsuarioDAO();
			List<Usuario> listausuarios = persistenciaUsuario.getAll();
			for (Usuario usuario : listausuarios) {
				usuariosPorID.put(usuario.getId(), usuario);
				usuariosPorLogin.put(usuario.getLogin(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public static RepositorioUsuarios getInstancia() {
		if(instancia == null)
			instancia = new RepositorioUsuarios();
		return instancia;
	}
	
	public List<Usuario> findUsuarios() throws DAOException {
		return new LinkedList<Usuario>(usuariosPorLogin.values());
	}
	
	public Usuario findUsuario(String login) {
		return usuariosPorLogin.get(login);
	}

	public Usuario findUsuario(int id) {
		return usuariosPorID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		usuariosPorID.put(usuario.getId(), usuario);
		usuariosPorLogin.put(usuario.getLogin(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuariosPorID.remove(usuario.getId());
		usuariosPorLogin.remove(usuario.getLogin());
	}
	
	public void removeTodosUsuarios() {
		HashMap<Integer, Usuario> aux = usuariosPorID;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		for (Map.Entry<Integer, Usuario> entry : aux.entrySet() ) {
			Usuario user = entry.getValue();
			listaUsuarios.add(user);
		}
		persistenciaUsuario.deleteAll(listaUsuarios);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		this.persistenciaUsuario.update(usuario);
	}

}
