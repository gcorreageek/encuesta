package com.encuesta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.encuesta.model.Alternativa;
import com.encuesta.model.Carrera;
import com.encuesta.model.Encuesta;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.EncuestaProfesor;
import com.encuesta.model.Marcada;
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.TipoEncuesta;

@Repository
public class EncuestaDAOImpl implements EncuestaDAO {
	private final Log log = LogFactory.getLog(getClass());

	@PersistenceContext
	private EntityManager em;

//	//@Override
	public TipoEncuesta registrarTipoEncuesta(TipoEncuesta te) {
		em.persist(te);
		return te;
	}

//	//@Override
	public NumeroEncuesta registrarNumeroEncuesta(NumeroEncuesta ne) {
		em.persist(ne);
		return ne;
	}

//	//@Override
	public Encuesta registrarEncuesta(Encuesta e) {
		em.persist(e);
		return e;
	}
//	//@Override
	public List<Encuesta> buscarEncuestaXNombreR(Encuesta e) {
		Query q =  em.createNamedQuery("Encuesta.findNombreReferente",Encuesta.class);
		q.setParameter(1, e.getNombreReferente()); 
        return q.getResultList();
	}

//	//@Override
	public Pregunta registrarPregunta(Pregunta p) {
		em.persist(p);
		return p;
	}

//	//@Override
	public Alternativa registrarAlternativa(Alternativa a) {
		em.persist(a);
		return a;
	}

//	//@Override
	public  NumeroEncuesta  getNumeroEncuestaXidEncuesta(Integer idEncuesta) {
		Encuesta n = em.find(Encuesta.class, idEncuesta);
		return n.getNumeroencuesta();
	}

//	//@Override
	public EncuestaProfesor registrarEncuestaProfesor(EncuestaProfesor ep) {
		em.persist(ep);
		return ep;
	}

	public Encuesta getEncuesta(Encuesta e) {
		Encuesta encuesta = em.find(Encuesta.class, e.getIdEncuesta());
		return encuesta;
	}

	public EncuestaAlumno registrarEncuestaAlumno(EncuestaAlumno ea) {
		em.persist(ea);
		return ea;
	}

	@SuppressWarnings("unchecked")
	public EncuestaProfesor getEncuestaProfesor(EncuestaProfesor ep) {
		Query q =  em.createNamedQuery("Encuestaprofesor.findIdEncuestaIdProfesor",EncuestaProfesor.class);
		q.setParameter(1, ep.getEncuesta().getIdEncuesta()); 
		q.setParameter(2, ep.getUsuario().getIdUsuario()); 
		List<EncuestaProfesor> lEncuestaProfesor = q.getResultList();
		if(lEncuestaProfesor==null || lEncuestaProfesor.isEmpty()){
			return null;
		}else{
			return (EncuestaProfesor) lEncuestaProfesor.get(0);
		} 
	}

	public List<Pregunta> listarPreguntaXidNumeroEncuesta(NumeroEncuesta ne) {
		Query q =  em.createNamedQuery("Pregunta.findIdNumeroEncuesta",Pregunta.class);
		q.setParameter(1, ne.getIdNumeroEncuesta());  
		List<Pregunta> lPregunta = q.getResultList();
		if(lPregunta==null || lPregunta.isEmpty()){
			return null;
		}else{
			return  lPregunta;
		}
	}
//	Alternativa.findIdPregunta
	public List<Alternativa> listarAlternativaXidPregunta(Pregunta p) {
		Query q =  em.createNamedQuery("Alternativa.findIdPregunta",Alternativa.class);
		q.setParameter(1, p.getIdPregunta());  
		List<Alternativa> lAlternativa = q.getResultList();
		if(lAlternativa==null || lAlternativa.isEmpty()){
			return null;
		}else{
			return  lAlternativa;
		}
	}

	public EncuestaAlumno getEncuestaAlumno(EncuestaAlumno ea) {
		return em.find(EncuestaAlumno.class, ea.getIdEncuestaAlumno());
	}

	public EncuestaAlumno actualizarEncuestaAlumno(EncuestaAlumno ea) {
		ea = em.merge(ea);
		return ea;
	}

	public Marcada registrarMarcadas(Marcada m) {
		em.persist(m); 
		return m;
	}

	public Alternativa getAlternativa(Alternativa a) {
		log.debug("id:"+a.getIdAlternativas());
		Alternativa alternativa = em.find(Alternativa.class, a.getIdAlternativas());
		return alternativa;
	}

	public List<NumeroEncuesta> listaNumeroEncuesta(NumeroEncuesta ne) {
		Query q =  em.createNamedQuery("Numeroencuesta.findNumeroReferente",NumeroEncuesta.class);
		q.setParameter(1, ne.getNumeroReferente());  
		List<NumeroEncuesta> lNumeroEncuesta = q.getResultList();
		if(lNumeroEncuesta==null || lNumeroEncuesta.isEmpty()){
			return null;
		}else{
			return  lNumeroEncuesta;
		}
	}

	public NumeroEncuesta getNumeroEncuestaXid(NumeroEncuesta ne) {
		NumeroEncuesta ne2 = em.find(NumeroEncuesta.class, ne.getIdNumeroEncuesta());
		return ne2;
	}

	public List<EncuestaProfesor> listarEncuestaProfesor(EncuestaProfesor ep) {
		Query q =  em.createNamedQuery("Encuestaprofesor.findIdProfesor",EncuestaProfesor.class);
		q.setParameter(1, ep.getUsuario().getIdUsuario());  
		List<EncuestaProfesor> lEncuestaProfesor = q.getResultList();
		if(lEncuestaProfesor==null || lEncuestaProfesor.isEmpty()){
			return null;
		}else{
			return  lEncuestaProfesor;
		}
	}

	public EncuestaProfesor getEncuestaProfesorXId(EncuestaProfesor ep) {
		return em.find(EncuestaProfesor.class, ep.getIdEncuestaProfesor());
	}

	public List<EncuestaAlumno> listarEncuestaAlumno(EncuestaProfesor ep) {
		Query q =  em.createNamedQuery("Encuestaalumno.findIdEncuestaProfesor",EncuestaAlumno.class);
		q.setParameter(1, ep.getIdEncuestaProfesor());  
		List<EncuestaAlumno> EncuestaAlumno = q.getResultList();
		if(EncuestaAlumno==null || EncuestaAlumno.isEmpty()){
			return null;
		}else{
			return  EncuestaAlumno;
		}
	}
//	Marcada.findIdEncuestaAlumno
	public List<Marcada> listarMarcadasXEncuestaAlumno(EncuestaAlumno ea) {
		Query q =  em.createNamedQuery("Marcada.findIdEncuestaAlumno",Marcada.class);
		q.setParameter(1, ea.getIdEncuestaAlumno());  
		List<Marcada> lMarcada = q.getResultList();
		if(lMarcada==null || lMarcada.isEmpty()){
			return null;
		}else{
			return  lMarcada;
		}
	}

	
	
}
