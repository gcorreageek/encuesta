package com.encuesta.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encuesta.dao.DominioDAO;
import com.encuesta.dao.EncuestaDAO;
import com.encuesta.dao.MatriculaDAO;
import com.encuesta.model.Anio;
import com.encuesta.model.AsignacionProfesor;
import com.encuesta.model.Carrera;
import com.encuesta.model.Ciclo;
import com.encuesta.model.Curso;
import com.encuesta.model.Dominio;
import com.encuesta.model.Encuesta;
import com.encuesta.model.Matricula;
import com.encuesta.model.ProfesoCurso;
import com.encuesta.model.Usuario;
@Service
public class MatriculaServiceImpl implements MatriculaService {
	private final Log log = LogFactory.getLog(getClass());
 
	@Autowired
    MatriculaDAO dao;
	@Autowired
    DominioDAO daoDominio;
	@Autowired
    EncuestaDAO daoEncuesta;

	//@Override
	public List<Carrera> listarCarrera() throws Exception {
		Carrera a = new Carrera();
		a.setDescripcion("%");
		return dao.buscarCarreraXDescripcion(a);
	}

	//@Override
	public List<Curso> listarCurso() throws Exception {
		Curso a = new Curso();
		a.setCurso("%");
		return dao.buscarCursoXCurso(a);
	}

	//@Override
	public List<Anio> buscarAnio(Anio a) throws Exception {
		Ciclo c = new Ciclo();
		c.setModalidadD(a.getCiclo().getModalidadD());
		c.setAnnioD(a.getCiclo().getAnnioD());
		c.setNumeroD(a.getCiclo().getNumeroD());
//		c.setEstadoCicloD(a.getCiclo().getEstadoCicloD());
		
		List<Ciclo> lCiclo = dao.buscarCicloXVarios(c);
		c = lCiclo.get(0);
		a.setCiclo(c); 
		List<Anio> lAnio = dao.buscarAnioXVarios(a);
		for (Anio anio : lAnio) {
			log.debug("Dominio:"+anio.getCicloAcademicoD()+"|"+anio.getSessionD());
			
			Dominio d1 = new Dominio();
			d1.setIdDominio(anio.getCicloAcademicoD());
			d1 = daoDominio.buscarXId(d1).get(0);
			anio.setCicloAcademidoDominio(d1);
			
			Dominio d2 = new Dominio();
			d2.setIdDominio(anio.getSessionD());
			d2 = daoDominio.buscarXId(d2).get(0);
			anio.setSessionDominio(d2);
		}
		return lAnio;
	}

 

	//@Override
	public Anio getAnioXidAnio(Integer idAnio) throws Exception {
		List<Anio> lAnio = dao.listaAnioXIdAnio(idAnio);
		if(lAnio!=null){
			return lAnio.get(0);
		} 
		return null;
	}

	//@Override
	public Anio getAnio(Integer idAnio) throws Exception {
		Anio a = new Anio();
		a.setIdAnio(idAnio);
		List<Anio> lAnio = dao.listaAnioXIdAnio(idAnio);
		return lAnio.get(0);
	}

	//@Override
	public List<ProfesoCurso> listarProfesoresValidos(String idEncuesta)throws Exception {
		Encuesta e = new Encuesta();
		e.setIdEncuesta(Integer.parseInt(idEncuesta));
		return dao.listaProfesorXEncuesta(e);
	}

	//@Override
	public List<AsignacionProfesor> listarProfesoresAsignados(String idEncuesta)
			throws Exception {
		Encuesta e = new Encuesta();
		e.setIdEncuesta(Integer.parseInt(idEncuesta));
		return dao.listaProfesorAsignadoXEncuesta(e);
	}

	public List<Matricula> listarAlumnosMatriculados(String idEncuestaViene, String idProfesorViene) throws Exception {
		Integer idEncuesta = Integer.parseInt(idEncuestaViene);
		Integer idProfesor = Integer.parseInt(idProfesorViene);
		Encuesta e = new Encuesta();
		e.setIdEncuesta(idEncuesta);
		e = daoEncuesta.getEncuesta(e);
		Usuario profesor = new Usuario();
		profesor.setIdUsuario(idProfesor);
		AsignacionProfesor ap = new AsignacionProfesor();
		ap.setAnio(e.getAnio());
		ap.setUsuario(profesor);
		ap = dao.getAsignacionProfesorIdAnioIdProfesor(ap);
		return dao.listaMatriculaXAsignacion(ap);
	}

	 
 

}
