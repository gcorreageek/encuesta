package com.encuesta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.encuesta.model.Usuario;
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	private final Log log = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager em; 
	
	@Override
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

	@Override
	public List<Usuario> buscarXId(Usuario a) throws Exception {
		Query q =  em.createNamedQuery("Usuario.findId",Usuario.class);
		q.setParameter(1, a.getIdUsuario()); 
        return q.getResultList();
	}

	@Override
	public List<Usuario> buscarXUserName(Usuario a) throws Exception {
		Query q =  em.createNamedQuery("Usuario.findUsuario",Usuario.class);
		q.setParameter(1, a.getUserName()); 
        return q.getResultList();
	}

	

}
