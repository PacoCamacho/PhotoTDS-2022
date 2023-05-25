package photo.tds.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import photo.tds.dominio.Album;
import photo.tds.dominio.Conversor;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;

public class TDSPublicacionDAO implements PublicacionDAO{

	private static final String PUBLICACION = "Publicación";
	private static final String TITULO = "Título";
	private static final String FECHA = "Fecha";
	private static final String DESCRIPCION = "Descripción";
	private static final String MEGUSTAS = "Número de megustas";
	private static final String FOTOS = "Número de fotos";
	private static final String PATH = "Path de la foto o fotos";
	//private static final String HASHTAGS = "Hashtags";
	
	
	//Atributos
	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;
	
	public TDSPublicacionDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	}
	
	private Publicacion entidadToPublicacion(Entidad ePublicacion) {
		String titulo = servPersistencia.recuperarPropiedadEntidad(ePublicacion, TITULO);
		String fecha = servPersistencia.recuperarPropiedadEntidad(ePublicacion, FECHA);
		String descripcion = servPersistencia.recuperarPropiedadEntidad(ePublicacion, DESCRIPCION);
		int megustas = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePublicacion, MEGUSTAS)) ;
		int nfotos = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePublicacion, FOTOS));
		String path = servPersistencia.recuperarPropiedadEntidad(ePublicacion, PATH);
		String creador = servPersistencia.recuperarPropiedadEntidad(ePublicacion, TITULO);
		//no se q hacer con los hashtags, preguntar
		//String hashtags = servPersistencia.recuperarPropiedadEntidad(ePublicacion, HASHTAGS);
		if(nfotos>1) {
			Foto foto = new Foto(path, titulo, Conversor.StringToDate(fecha), descripcion, megustas, creador);
			foto.SetId(ePublicacion.getId());
			return foto;
		}
		else {
			Album album = new Album(titulo, Conversor.StringToDate(fecha), descripcion,  megustas, creador);
			album.SetId(ePublicacion.getId());
			return album;
		}
		/*Publicacion publicacion = new Publicacion(titulo, fecha, descripcion, megustas);
		publicacion.SetId(ePublicacion.getId());
		
		
		return publicacion;*/
	}
	
	
	private Entidad publicacionToEntidad(Publicacion publicacion) {
		Entidad ePublicacion = new Entidad();
		ePublicacion.setNombre(PUBLICACION);
		
		ePublicacion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(TITULO, publicacion.getTitulo()),
				new Propiedad(FECHA, Conversor.DateToString(publicacion.getFecha())),
				new Propiedad(DESCRIPCION, publicacion.getDescripcion()),
				new Propiedad(MEGUSTAS, Integer.toString(publicacion.getMg())),
				new Propiedad(FOTOS, Integer.toString(publicacion.getNumFotos())),
				new Propiedad(PATH,publicacion.getPath()))));
		
		
		return ePublicacion;
	}
	
	
	public void create(Publicacion publicacion) {
		Entidad ePublicacion = this.publicacionToEntidad(publicacion);
		ePublicacion = servPersistencia.registrarEntidad(ePublicacion);
		publicacion.SetId(ePublicacion.getId());
	}

	public boolean delete(Publicacion publicacion) {
		Entidad ePublicacion;
		ePublicacion = servPersistencia.recuperarEntidad(publicacion.getId());
		return servPersistencia.borrarEntidad(ePublicacion);
	}

	public void update(Publicacion publicacion) {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(publicacion.getId());
		
		for(Propiedad p : ePublicacion.getPropiedades()) {
			if (p.getNombre().equals(TITULO)) {
				p.setValor(publicacion.getTitulo());
			} else if (p.getNombre().equals(FECHA)) {
				p.setValor(dateFormat.format(publicacion.getFecha()));
			} else if (p.getNombre().equals(DESCRIPCION)) {
				p.setValor(publicacion.getDescripcion());
			} else if (p.getNombre().equals(MEGUSTAS)) {
				p.setValor(Integer.toString(publicacion.getMg()));
			}
			servPersistencia.modificarPropiedad(p);
		}
		
	}

	public Publicacion get(int id) {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(id);
		return entidadToPublicacion(ePublicacion);
	}

	public List<Publicacion> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PUBLICACION);
		
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();
		for (Entidad ePublicacion : entidades) {
			publicaciones.add(get(ePublicacion.getId()));
		}
		return publicaciones;
	}
	

	
	
}