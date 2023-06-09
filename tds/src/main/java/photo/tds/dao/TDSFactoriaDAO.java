package photo.tds.dao;

import photo.tds.dao.FactoriaDAO;
import photo.tds.dao.TDSUsuarioDAO;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {	}
	
	@Override
	public TDSUsuarioDAO getUsuarioDAO() {	
		return new TDSUsuarioDAO(); 
	}

	@Override
	public PublicacionDAO getPublicacionDAO() {
		return new TDSPublicacionDAO();
	}

	@Override
	public ComentarioDAO getComentarioDAO() {
		return new TDSComentarioDAO();
	}

}
