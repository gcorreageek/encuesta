package com.encuesta.service;

import java.util.List;

import com.encuesta.model.Alternativa;
import com.encuesta.model.Encuesta;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.EncuestaProfesor;
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.TipoEncuesta;


public interface EncuestaService {
 
	TipoEncuesta registrarTipoEncuesta(TipoEncuesta te) throws Exception;
	Encuesta registrarEncuesta(Encuesta e) throws Exception;
	NumeroEncuesta registrarNumeroEncuesta(NumeroEncuesta ne) throws Exception;
	
	Encuesta registrarEncuesta(String[] anio,String nombreReferente) throws Exception;
	List<Encuesta> listarEncuesta() throws Exception;
	
//	EncuestaProfesor getEncuestaProfesor(EncuestaProfesor ea);
	
	void registrarPregunta(Integer idEncuesta,List<Pregunta> lPregunta);
	void registrarEncuestaProfesor(Integer idEncuesta,String[] profesores);
	void registrarEncuestaAlumno(Integer idProfesor,Integer idEncuesta,String[] alumnos)  throws Exception;
	
	List<Pregunta> listarPreguntaXNumeroEncuesta(NumeroEncuesta ne)  ;
	List<Alternativa> listarAlternativaXPregunta(Pregunta p)  ;
	EncuestaAlumno getEncuestaAlumno(String idEncuestaAlumno);
	EncuestaAlumno actualizarEncuestaAlumno(EncuestaAlumno ea);
	
	void registrarMarcadas(EncuestaAlumno ea,String[] idAlternativasMarcadas);
	
}
