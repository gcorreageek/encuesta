package com.encuesta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.encuesta.model.Anio;
import com.encuesta.model.AsignacionProfesor;
import com.encuesta.model.Carrera;
import com.encuesta.model.Ciclo;
import com.encuesta.model.Curso;
import com.encuesta.model.Encuesta;
import com.encuesta.model.Matricula;
import com.encuesta.model.ProfesoCurso;

@Repository
public class MatriculaDAOImpl implements MatriculaDAO {
	private final Log log = LogFactory.getLog(getClass());

	@PersistenceContext
	private EntityManager em;
//	findDescripcion
//	//@Override
	public List<Carrera> buscarCarreraXDescripcion(Carrera a) throws Exception {
		Query q =  em.createNamedQuery("Carrera.findDescripcion",Carrera.class);
		q.setParameter(1, a.getDescripcion()); 
        return q.getResultList();
	}
//	//@Override
	public List<Curso> buscarCursoXCurso(Curso a) throws Exception {
		Query q =  em.createNamedQuery("Curso.findCurso",Curso.class);
		q.setParameter(1, a.getCurso()); 
        return q.getResultList();
	}
//	@NamedQuery(name="Ciclo.findVarios", query="
//	SELECT u FROM Ciclo u WHERE u.modalidadD = ?1 and u.annioD = ?2 and u.numeroD = ?3 and u.estadoCicloD = ?4")
//	//@Override
	public List<Ciclo> buscarCicloXVarios(Ciclo a) throws Exception {
		Query q =  em.createNamedQuery("Ciclo.findVarios",Ciclo.class);
		q.setParameter(1, a.getModalidadD());
		q.setParameter(2, a.getAnnioD());
		q.setParameter(3, a.getNumeroD());
//		q.setParameter(4, a.getEstadoCicloD());
        return q.getResultList();
	}
//	@NamedQuery(name="Anio.findVarios", query="SELECT u FROM Anio u WHERE u.ciclo.idCiclo = ?1 "
//			+ "and u.carrera.idCarrera = ?2 "
//			+ " and u.curso.idCurso = ?3  and u.cicloAcademicoD = ?4 and u.sessionD = ?5 ")
//	//@Override
	public List<Anio> buscarAnioXVarios(Anio a) throws Exception {
		Query q =  em.createNamedQuery("Anio.findVarios",Anio.class);
		q.setParameter(1, a.getCiclo().getIdCiclo());
		q.setParameter(2, a.getCarrera().getIdCarrera());
		q.setParameter(3, a.getCicloAcademicoD());
		q.setParameter(4, a.getCurso().getIdCurso());
		q.setParameter(5, a.getSessionD()); 
        return q.getResultList();
	}
//	//@Override
	public List<Anio> listaAnioXIdAnio(Integer a) throws Exception {
		Query q =  em.createNamedQuery("Anio.findId",Anio.class);
		q.setParameter(1, a);
		List<Anio> lAnio = q.getResultList();
		return lAnio;
	}
//	//@Override
	public List<ProfesoCurso> listaProfesorXEncuesta(Encuesta eViene)
			throws Exception {  
		Encuesta e = em.find(Encuesta.class, eViene.getIdEncuesta());
		return e.getAnio().getCurso().getProfesocursos();
	}
//	//@Override
	public List<AsignacionProfesor> listaProfesorAsignadoXEncuesta(Encuesta eViene)
			throws Exception {
		Encuesta e = em.find(Encuesta.class, eViene.getIdEncuesta());
		Query q =  em.createNamedQuery("Asignacionprofesor.findIdAnio",AsignacionProfesor.class);
		q.setParameter(1,e.getAnio().getIdAnio()); 
		List<AsignacionProfesor> lAsignacionProfesor = q.getResultList();
		if(lAsignacionProfesor==null || lAsignacionProfesor.isEmpty()){
			return null;
		}
		return  lAsignacionProfesor;
	}
//	//@Override
	public List<Matricula> listaMatriculaXAsignacion(AsignacionProfesor e)
			throws Exception { 
		Query q =  em.createNamedQuery("Matricula.findIdAsignacionProfesor",Matricula.class);
		q.setParameter(1, e.getIdAsignacionProfesor()); 
		List<Matricula> lMatricula = q.getResultList();
		if(lMatricula==null || lMatricula.isEmpty()){
			return null;
		}
		return  lMatricula;
	}
	public AsignacionProfesor getAsignacionProfesorIdAnioIdProfesor(
			AsignacionProfesor e) throws Exception {
		Query q =  em.createNamedQuery("Asignacionprofesor.findIdAnioIdProfesor",AsignacionProfesor.class);
		q.setParameter(1, e.getAnio().getIdAnio());
		q.setParameter(2, e.getUsuario().getIdUsuario());
		List<AsignacionProfesor> lAsignacionProfesor = q.getResultList();
		if(lAsignacionProfesor==null || lAsignacionProfesor.isEmpty()){
			return null;
		}
		return lAsignacionProfesor.get(0);
	}

}
