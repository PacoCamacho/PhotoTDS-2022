package photo.tds.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import photo.tds.dominio.Comentario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class TDSComentarioDAO implements ComentarioDAO{
	
	private static final String COMENTARIO = "Comentario";
	private static final String TEXTO = "Texto";
	private static final String USUARIO = "Usuario";
	
	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;
	
	public TDSComentarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	@Override
	public void create(Comentario comentario) {
		Entidad eComentario = this.comentarioToEntidad(comentario);
		eComentario = servPersistencia.registrarEntidad(eComentario);
		comentario.setId(eComentario.getId());
	}

	@Override
	public boolean delete(Comentario comentario) {
		Entidad eComentario;
		eComentario = servPersistencia.recuperarEntidad(comentario.getId());
		servPersistencia.borrarEntidad(eComentario);
		return false;
	}

	@Override
	public void update(Comentario comentario) {
		
		Entidad eComentario = servPersistencia.recuperarEntidad(comentario.getId());
		
		for(Propiedad c : eComentario.getPropiedades()) {
			if (c.getNombre().equals(TEXTO)) {
				c.setValor(comentario.getTexto());
			} else if (c.getNombre().equals(USUARIO)) {
				c.setValor(dateFormat.format(comentario.getUsuario()));
			}
			servPersistencia.modificarPropiedad(c);
		}
		
	}

	@Override
	public Comentario get(int id) {
		Entidad eComentario = servPersistencia.recuperarEntidad(id);
		return entidadToComentario(eComentario);
	}

	@Override
	public List<Comentario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(COMENTARIO);
		
		List<Comentario> comentarios = new LinkedList<Comentario>();
		for (Entidad eComentario : entidades) {
			comentarios.add(get(eComentario.getId()));
		}
		return comentarios;
	}
	
	private Comentario entidadToComentario(Entidad eComentario){
		
		String texto = servPersistencia.recuperarPropiedadEntidad(eComentario, TEXTO);
		String autor = servPersistencia.recuperarPropiedadEntidad(eComentario, USUARIO);

		Comentario comentario = new Comentario(texto, autor);
		comentario.setId(eComentario.getId());

		return comentario;
	}
	
	private Entidad comentarioToEntidad(Comentario comentario) {
		
		Entidad eComentario = new Entidad();
		
		eComentario.setNombre(COMENTARIO);

		eComentario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(TEXTO, comentario.getTexto()),
				new Propiedad(USUARIO, comentario.getUsuario())
				)));
		
		return eComentario;
	}

}
