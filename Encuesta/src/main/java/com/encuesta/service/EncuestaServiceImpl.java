package com.encuesta.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encuesta.dao.EncuestaDAO;
import com.encuesta.dao.MatriculaDAO;
import com.encuesta.model.Alternativa;
import com.encuesta.model.Anio;
import com.encuesta.model.AsignacionProfesor;
import com.encuesta.model.Ciclo;
import com.encuesta.model.Encuesta;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.EncuestaProfesor;
import com.encuesta.model.Marcada;
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.TipoEncuesta;
import com.encuesta.model.Usuario;
import com.encuesta.util.EstadosHabilitacion;
@Service
public class EncuestaServiceImpl implements EncuestaService {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
    EncuestaDAO dao;
	@Autowired
    MatriculaDAO daoMatricula;
	@Autowired
    MatriculaService matriculaService;

	//@Override
	public TipoEncuesta registrarTipoEncuesta(TipoEncuesta te) throws Exception {
		return dao.registrarTipoEncuesta(te);
	}

	//@Override
	public Encuesta registrarEncuesta(Encuesta e) throws Exception {
		return dao.registrarEncuesta(e);
	}

	//@Override
	public NumeroEncuesta registrarNumeroEncuesta(NumeroEncuesta ne)
			throws Exception {
		return dao.registrarNumeroEncuesta(ne);
	}

	@Transactional
	//@Override
	public Encuesta registrarEncuesta(String[] anios,String nombreReferente) throws Exception {
		for (String s : anios) {
			Anio a = matriculaService.getAnioXidAnio(Integer.parseInt(s));
			TipoEncuesta te = new TipoEncuesta();
			Ciclo ciclo = new Ciclo();
			ciclo.setIdCiclo(a.getCiclo().getIdCiclo());
			te.setCiclo(ciclo);
			te.setHabilitado(EstadosHabilitacion.HABILITADO.toString());
			te.setEncuesta(ciclo.getNombre());
			registrarTipoEncuesta(te); 
			NumeroEncuesta ne = new NumeroEncuesta();
			ne.setFecha(new Date());
			registrarNumeroEncuesta(ne); 
			Encuesta e = new Encuesta();
			log.debug("tipo iD:"+te.getIdTipoEncuesta());
			e.setTipoencuesta(te);
			log.debug("Anio iD:"+a.getIdAnio());
			e.setAnio(a);
			log.debug("numero id:"+ne.getIdNumeroEncuesta());
			e.setNumeroencuesta(ne); 
			e.setNombreReferente(nombreReferente);
			registrarEncuesta(e);
		} 
		return null;
	}

	//@Override
	public List<Encuesta> listarEncuesta() throws Exception {
		Encuesta e = new Encuesta();
		e.setNombreReferente("%");
		return dao.buscarEncuestaXNombreR(e);
	}

	@Transactional
	//@Override
	public void registrarPregunta(Integer idEncuesta, List<Pregunta> lPregunta) {
		NumeroEncuesta ne = dao.getNumeroEncuestaXidEncuesta(idEncuesta);
		for (Pregunta p : lPregunta) {
			p.setNumeroencuesta(ne); 
			p = dao.registrarPregunta(p);
			List<Alternativa> lAlternativa = p.getAlternativas();
			for (Alternativa a : lAlternativa) {
				a.setPregunta(p);
				dao.registrarAlternativa(a);
			} 
		} 
	}

	@Transactional
	//@Override
	public void registrarEncuestaProfesor(Integer idEncuesta,
			String[] profesores) {
		for (String s : profesores) {
			Integer idProfesor = Integer.parseInt(s);
			EncuestaProfesor ep = new EncuestaProfesor();
			Encuesta e = new Encuesta();
			e.setIdEncuesta(idEncuesta);
			ep.setEncuesta(e);
			Usuario p = new Usuario();
			p.setIdUsuario(idProfesor);
			ep.setUsuario(p);
			dao.registrarEncuestaProfesor(ep);
		}
		
	}

	@Transactional
	public void registrarEncuestaAlumno(Integer idProfesor, Integer idEncuesta,
			String[] alumnos) throws Exception {
		Encuesta e = new Encuesta();
		e.setIdEncuesta(idEncuesta);
		e = dao.getEncuesta(e);
		Usuario profesor = new Usuario();
		profesor.setIdUsuario(idProfesor);
		
		log.debug("encuesta:"+e.getIdEncuesta());
		log.debug("profesor:"+profesor.getIdUsuario());
		
		EncuestaProfesor ep = new EncuestaProfesor();
		ep.setEncuesta(e);
		ep.setUsuario(profesor); 
		ep = dao.getEncuestaProfesor(ep);
		log.debug("EncuestaProfesor:"+ep.getIdEncuestaProfesor());
		
		for (String s : alumnos) {  
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(Integer.parseInt(s));
			
			EncuestaAlumno ea = new EncuestaAlumno();
			ea.setUsuario(usuario);
			ea.setEncuestaprofesor(ep);
			dao.registrarEncuestaAlumno(ea);
		}
		
	}

	public List<Pregunta> listarPreguntaXNumeroEncuesta(NumeroEncuesta ne) {
		return dao.listarPreguntaXidNumeroEncuesta(ne);
	}

	public List<Alternativa> listarAlternativaXPregunta(Pregunta p) {
		return dao.listarAlternativaXidPregunta(p);
	}

	public EncuestaAlumno getEncuestaAlumno(String idEncuestaAlumno) {
		EncuestaAlumno ea =  new EncuestaAlumno();
		ea.setIdEncuestaAlumno(Integer.parseInt(idEncuestaAlumno));
		return dao.getEncuestaAlumno(ea);
	}

	@Transactional
	public EncuestaAlumno actualizarEncuestaAlumno(EncuestaAlumno ea) {
		ea = dao.actualizarEncuestaAlumno(ea);
		return ea;
	}

	@Transactional
	public void registrarMarcadas(EncuestaAlumno ea, String[] idAlternativasMarcadas) {
		log.debug("tam alt:"+idAlternativasMarcadas.length);
		for (String id : idAlternativasMarcadas) {
			Marcada m = new Marcada();
			m.setEncuestaalumno(ea);
			Alternativa a = new Alternativa();
			a.setIdAlternativas(Integer.parseInt(id));
			a = dao.getAlternativa(a);
			m.setTipoRespuestaD(1);
			m.setAlternativa(a);
			log.debug("Marcadas:"+m.getAlternativa().getIdAlternativas());
			log.debug("id2:"+m.getEncuestaalumno().getIdEncuestaAlumno());
			dao.registrarMarcadas(m);
		} 
	}
 

}
