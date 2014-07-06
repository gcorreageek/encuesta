package com.encuesta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.encuesta.model.Dominio;
@Repository
public class DominioDAOImpl implements DominioDAO {
	private final Log log = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public Dominio insertarOActualizar(Dominio a) {
		if(a.getIdDominio()==0){
			em.persist(a);
		}else{
			em.merge(a);
		} 
		return a;
	} 

	@SuppressWarnings("unchecked")
	@Override
	public List<Dominio> buscarXId(Dominio a) throws Exception {
		Query q =  em.createNamedQuery("Dominio.findId",Dominio.class);
		q.setParameter(1, a.getIdDominio()); 
        return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dominio> buscarXCampo(Dominio a) throws Exception {
		Query q =  em.createNamedQuery("Dominio.findCampo",Dominio.class);
		q.setParameter(1, a.getCampo()); 
        return q.getResultList();
	}

//	@Override
//	public List<Dominio> buscarXUserName(Dominio a) throws Exception {
//		Query q =  em.createNamedQuery("Dominio.findDominio",Dominio.class);
//		q.setParameter(1, a.getUserName()); 
//        return q.getResultList();
//	}

	

}
