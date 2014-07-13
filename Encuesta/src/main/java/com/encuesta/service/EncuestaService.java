package com.encuesta.service;

import java.util.List;

import com.encuesta.model.Alternativa;
import com.encuesta.model.Encuesta;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.EncuestaProfesor;
import com.encuesta.model.Marcada;
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.TipoEncuesta;
import com.encuesta.model.Usuario;


public interface EncuestaService {
 
	TipoEncuesta registrarTipoEncuesta(TipoEncuesta te) throws Exception;
	Encuesta registrarEncuesta(Encuesta e) throws Exception;
	NumeroEncuesta registrarNumeroEncuesta(NumeroEncuesta ne) throws Exception;
	
	NumeroEncuesta registrarNumeroEncuestaAndPreguntasAlternativas(NumeroEncuesta ne,List<Pregunta> lPregunta) throws Exception;
	
	Encuesta registrarEncuesta(String[] anio,String nombreReferente) throws Exception;
	Encuesta registrarEncuesta(String[] anio,NumeroEncuesta ne) throws Exception;
	List<Encuesta> listarEncuesta() throws Exception;
	List<NumeroEncuesta> listarNumeroEncuesta();
	
//	EncuestaProfesor getEncuestaProfesor(EncuestaProfesor ea);
	
	void registrarPregunta(Integer idEncuesta,List<Pregunta> lPregunta);
	void registrarEncuestaProfesor(Integer idEncuesta,String[] profesores);
	void registrarEncuestaAlumno(Integer idProfesor,Integer idEncuesta,String[] alumnos,String txt)  throws Exception;
	
	List<Pregunta> listarPreguntaXNumeroEncuesta(NumeroEncuesta ne)  ;
	List<Alternativa> listarAlternativaXPregunta(Pregunta p)  ;
	EncuestaAlumno getEncuestaAlumno(String idEncuestaAlumno);
	EncuestaAlumno actualizarEncuestaAlumno(EncuestaAlumno ea);
	
	void registrarMarcadas(EncuestaAlumno ea,String[] idAlternativasMarcadas);
	List<EncuestaProfesor> listarEncuestasXprofesor(Usuario profesor);
	
	EncuestaProfesor getEncuestaProfesor(Integer idEncuestaProfesor);
	
	List<EncuestaAlumno> listarEncuestasAlumnoXIdEncuestaProfesor(EncuestaProfesor	Eprofesor);
	List<Marcada> listarMarcadasXEncuestaAlumno(EncuestaAlumno	ea);
	
}
