package com.encuesta.dao;

import java.util.List;

import com.encuesta.model.Anio;
import com.encuesta.model.AsignacionProfesor;
import com.encuesta.model.Carrera;
import com.encuesta.model.Ciclo;
import com.encuesta.model.Curso;
import com.encuesta.model.Encuesta;
import com.encuesta.model.Matricula;
import com.encuesta.model.ProfesoCurso;

public interface MatriculaDAO {
	List<Carrera> buscarCarreraXDescripcion(Carrera a)throws Exception; 
	List<Curso> buscarCursoXCurso(Curso a)throws Exception;
	List<Ciclo> buscarCicloXVarios(Ciclo a)throws Exception;
	List<Anio> buscarAnioXVarios(Anio a)throws Exception;
	List<Anio> listaAnioXIdAnio(Integer a) throws Exception;
	
	List<ProfesoCurso> listaProfesorXEncuesta(Encuesta e) throws Exception;
	List<AsignacionProfesor> listaProfesorAsignadoXEncuesta(Encuesta e) throws Exception;
	List<Matricula> listaMatriculaXAsignacion(AsignacionProfesor e) throws Exception;
	AsignacionProfesor getAsignacionProfesorIdAnioIdProfesor(AsignacionProfesor e) throws Exception;
	
	
	
}
