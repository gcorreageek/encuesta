package com.encuesta.dao;

import java.util.List;

import com.encuesta.model.Alternativa;
import com.encuesta.model.Encuesta;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.EncuestaProfesor;
import com.encuesta.model.Marcada;
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.TipoEncuesta;

public interface EncuestaDAO {
	
	TipoEncuesta registrarTipoEncuesta(TipoEncuesta te);
	NumeroEncuesta registrarNumeroEncuesta(NumeroEncuesta ne);
	Encuesta registrarEncuesta(Encuesta e);

	List<Encuesta> buscarEncuestaXNombreR(Encuesta e);
	
	
	Pregunta registrarPregunta(Pregunta p);
	Alternativa registrarAlternativa(Alternativa a);
	
	NumeroEncuesta getNumeroEncuestaXidEncuesta(Integer idEncuesta);
	
	EncuestaProfesor registrarEncuestaProfesor(EncuestaProfesor ep);
	Encuesta getEncuesta(Encuesta e);
	
	EncuestaProfesor getEncuestaProfesor(EncuestaProfesor ep);
	
	EncuestaAlumno registrarEncuestaAlumno(EncuestaAlumno ea);
	
	List<Pregunta> listarPreguntaXidNumeroEncuesta(NumeroEncuesta ne);
	List<Alternativa> listarAlternativaXidPregunta(Pregunta p);
	
	EncuestaAlumno getEncuestaAlumno(EncuestaAlumno ea);
	EncuestaAlumno actualizarEncuestaAlumno(EncuestaAlumno ea);
	
	Marcada registrarMarcadas(Marcada m);
	
	Alternativa getAlternativa(Alternativa a);
	 
}
