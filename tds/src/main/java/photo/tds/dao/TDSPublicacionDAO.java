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
import photo.tds.dominio.Comentario;
import photo.tds.dominio.Conversor;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Hashtag;
import photo.tds.dominio.Publicacion;

public class TDSPublicacionDAO implements PublicacionDAO{
	private static final String PUBLICACION = "Publicación";
	private static final String TIPO = "Tipo de Publicacion";
	private static final String TITULO = "Título";
	private static final String FECHA = "Fecha";
	private static final String DESCRIPCION = "Descripción";
	private static final String MEGUSTAS = "Número de megustas";
	private static final String FOTOS = "Lista de fotos";
	private static final String PATH = "Path de la foto";
	private static final String CREADOR = "Creador de la publicacion";
	private static final String HASHTAGS = "Hashtags";
	private static final String COMENTARIOS = "Comentarios";
	
	//Atributos
	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;
	

	
	public TDSPublicacionDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("d MMM y");
		
	}
	
	private Publicacion entidadToPublicacion(Entidad ePublicacion) {
		String titulo = servPersistencia.recuperarPropiedadEntidad(ePublicacion, TITULO);
		String tipo = servPersistencia.recuperarPropiedadEntidad(ePublicacion, TIPO);
		String fecha = servPersistencia.recuperarPropiedadEntidad(ePublicacion, FECHA);
		String descripcion = servPersistencia.recuperarPropiedadEntidad(ePublicacion, DESCRIPCION);
		int megustas = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePublicacion, MEGUSTAS));
		String creador = servPersistencia.recuperarPropiedadEntidad(ePublicacion, CREADOR);
		List<Comentario>comentarios = getComentariosPorIds(servPersistencia.recuperarPropiedadEntidad(ePublicacion, COMENTARIOS));
		List<Hashtag> hashtags = hashtagsDesdeCadena(servPersistencia.recuperarPropiedadEntidad(ePublicacion, HASHTAGS));
		
		
		//String hashtags = servPersistencia.recuperarPropiedadEntidad(ePublicacion, HASHTAGS);

		if(tipo.equals("Foto")) {
			String path = servPersistencia.recuperarPropiedadEntidad(ePublicacion, PATH);
			Foto foto = new Foto(path, titulo, Conversor.StringToDate(fecha), descripcion, megustas, creador, hashtags, comentarios);
			foto.SetId(ePublicacion.getId());
			return foto;
		}
		else if(tipo.equals("Album")){
			String idsFotos = servPersistencia.recuperarPropiedadEntidad(ePublicacion, FOTOS);
			
			Album album = new Album(titulo, Conversor.StringToDate(fecha), descripcion, megustas, creador, this.getFotosPorIds(idsFotos), hashtags, comentarios);
			album.SetId(ePublicacion.getId());
			return album;
		}
		return null;
		
	}
	
	
	private Entidad publicacionToEntidad(Publicacion publicacion) {
		Entidad ePublicacion = new Entidad();
		ePublicacion.setNombre(PUBLICACION);

		if(publicacion instanceof Foto) {
		ePublicacion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(TITULO, publicacion.getTitulo()),
				new Propiedad(TIPO, "Foto"),
				new Propiedad(FECHA, Conversor.DateToString(publicacion.getFecha())),
				new Propiedad(DESCRIPCION, publicacion.getDescripcion()),
				new Propiedad(MEGUSTAS, Integer.toString(publicacion.getMg())),
				new Propiedad(PATH,((Foto)publicacion).getPath()),
				new Propiedad(CREADOR, publicacion.getUsuario()),
				new Propiedad(HASHTAGS, this.hashtagACadena(publicacion.getHashtags())),
				new Propiedad(COMENTARIOS, this.comentsAIds(publicacion.getComentarios()))
				
				)));
		}
		else if(publicacion instanceof Album) {
			ePublicacion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
					new Propiedad(TITULO, publicacion.getTitulo()),
					new Propiedad(TIPO, "Album"),
					new Propiedad(FECHA, Conversor.DateToString(publicacion.getFecha())),
					new Propiedad(DESCRIPCION, publicacion.getDescripcion()),
					new Propiedad(MEGUSTAS, Integer.toString(publicacion.getMg())),
					new Propiedad(PATH,this.fotosAIds(((Album)publicacion).getFotos())),
					new Propiedad(CREADOR, publicacion.getUsuario()),
					new Propiedad(HASHTAGS, this.hashtagACadena(publicacion.getHashtags())),
					new Propiedad(COMENTARIOS, this.comentsAIds(publicacion.getComentarios()))
					)));
			}

		
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
				p.setValor(Conversor.DateToString(publicacion.getFecha()));
			} else if (p.getNombre().equals(DESCRIPCION)) {
				p.setValor(publicacion.getDescripcion());
			} else if (p.getNombre().equals(MEGUSTAS)) {
				p.setValor(Integer.toString(publicacion.getMg()));
			} else if (p.getNombre().equals(PATH)) {
				p.setValor(((Foto)publicacion).getPath());
			} else if (p.getNombre().equals(FOTOS)) {
				p.setValor(this.fotosAIds(((Album)publicacion).getFotos()));
			} else if (p.getNombre().equals(CREADOR)) {
				p.setValor(publicacion.getUsuario());
			} else if (p.getNombre().equals(COMENTARIOS)) {
				p.setValor(this.comentsAIds(publicacion.getComentarios()));
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
	
	public String fotosAIds(List<Foto> fotos) {
		StringBuilder cadenaIds = new StringBuilder();

	    for (Foto f : fotos) {
	        // Obtener el ID de la foto y añadirlo a la cadena
	        cadenaIds.append(f.getId()).append(" ");
	    }

	    // Eliminar el último espacio en blanco si existe
	    if (cadenaIds.length() > 0) {
	        cadenaIds.deleteCharAt(cadenaIds.length() - 1);
	    }

	    return cadenaIds.toString();
	}
	
	public List<Foto> getFotosPorIds(String ListaIds){
		List<Foto> fotos = new LinkedList<Foto>();
		String[] ids = ListaIds.split(" ");
		
		for (String id : ids) {
			int fotoId = Integer.parseInt(id);
			Publicacion publicacion = this.get(fotoId);
			String path = ((Foto)publicacion).getPath(); //revisar
			Foto foto = new Foto(path, publicacion.getTitulo(), publicacion.getFecha(), publicacion.getDescripcion(), publicacion.getUsuario());
			fotos.add(foto);
		}
		    
		return fotos;
	}
	
	public String comentsAIds(List<Comentario> comentarios) {
		StringBuilder cadenaIds = new StringBuilder();
		for (Comentario c : comentarios) {
	        // Obtener el ID del comentario y añadirlo a la cadena
	        cadenaIds.append(c.getId()).append(" ");
	    }
		// Eliminar el último espacio en blanco si existe
	    if (cadenaIds.length() > 0) {
	        cadenaIds.deleteCharAt(cadenaIds.length() - 1);
	    }

	    return cadenaIds.toString();
		
	}
	
	public List<Comentario> getComentariosPorIds(String ListaIds){
		List<Comentario> comentarios = new LinkedList<Comentario>();
		if(ListaIds=="") return comentarios;
		String[] ids = ListaIds.split(" ");
		
		for (String id : ids) {
			int comentarioId = Integer.parseInt(id);
			Comentario comentario;
			try {
				comentario = TDSFactoriaDAO.getInstancia().getComentarioDAO().get(comentarioId);
				comentarios.add(comentario);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		    
		return comentarios;
	}
	
	public List<Hashtag> hashtagsDesdeCadena(String cadenaHashtags){
		List<Hashtag> hashtags = new LinkedList<Hashtag>();
		String[] hs = cadenaHashtags.split(" ");
		for(String h : hs) {
			Hashtag hashtag = Hashtag.crearHashtag(h);
			hashtags.add(hashtag);
		}
		return hashtags;
		
	}
	
	public String hashtagACadena(List<Hashtag> hashtags) {
		StringBuilder cadenaHashtag = new StringBuilder();
		for (Hashtag h : hashtags) {
	        // Obtener el ID del comentario y añadirlo a la cadena
			cadenaHashtag.append(h.getNombre()).append(" ");
	    }
		// Eliminar el último espacio en blanco si existe
	    if (cadenaHashtag.length() > 0) {
	    	cadenaHashtag.deleteCharAt(cadenaHashtag.length() - 1);
	    }

	    return cadenaHashtag.toString();
		
	}
	
	
}