package com.encuesta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.encuesta.model.Acceso;
import com.encuesta.model.Cargo;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.Usuario;
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	private final Log log = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager em; 
	
//	//@Override
	public Usuario insertarOActualizar(Usuario a) {
		log.debug("usuario1:"+a.getIdUsuario());
		log.debug("usuario1:"+a.getNombre());
		if(a.getIdUsuario()==0){
			em.persist(a);
		}else{
			em.merge(a);
		} 
		return a;
	} 

	//@Override
	public List<Usuario> buscarXId(Usuario a) throws Exception {
		Query q =  em.createNamedQuery("Usuario.findId",Usuario.class);
		q.setParameter(1, a.getIdUsuario()); 
        return q.getResultList();
	}

	//@Override
	public List<Usuario> buscarXUserName(Usuario a) throws Exception {
		Query q =  em.createNamedQuery("Usuario.findUsuario",Usuario.class);
		q.setParameter(1, a.getUserName()); 
        return q.getResultList();
	}
//	findId 
	public List<Acceso> listarAccesoXcargo(Cargo a) throws Exception {
		Query q =  em.createNamedQuery("Acceso.findIdCargo",Acceso.class);
//		log.debug("cargo"+a.getIdCargo());
		q.setParameter(1, a.getIdCargo()); 
        return q.getResultList();
	}

	public List<EncuestaAlumno> listarEncuestaAlumnoXUsuario(Usuario u) {
		Query q =  em.createNamedQuery("Encuestaalumno.findIdUsuario",EncuestaAlumno.class);
		q.setParameter(1, u.getIdUsuario()); 
		List<EncuestaAlumno> lEncuestaAlumno = q.getResultList();
		if(lEncuestaAlumno==null || lEncuestaAlumno.isEmpty()){
			 return null;
		}
        return lEncuestaAlumno;
	}
	

}
