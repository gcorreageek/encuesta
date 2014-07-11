package com.encuesta.service;

import java.util.List;

import com.encuesta.model.Anio;
import com.encuesta.model.AsignacionProfesor;
import com.encuesta.model.Carrera;
import com.encuesta.model.Curso;
import com.encuesta.model.Matricula;
import com.encuesta.model.ProfesoCurso;

public interface MatriculaService {
 
	List<Carrera> listarCarrera() throws Exception;
	List<Curso> listarCurso() throws Exception;
	List<Anio> buscarAnio(Anio a) throws Exception;
//	Ciclo getCicloXidCiclo(Integer idCiclo) throws Exception;
	Anio getAnioXidAnio(Integer idAnio) throws Exception;
	Anio getAnio(Integer idAnio) throws Exception;
	
	List<ProfesoCurso> listarProfesoresValidos(String idEncuesta)throws Exception;
	List<AsignacionProfesor> listarProfesoresAsignados(String idEncuesta)throws Exception;
	List<Matricula> listarAlumnosMatriculados(String idEncuesta,String idProfesor)throws Exception;
//	listarAlumnosMatriculados
}
