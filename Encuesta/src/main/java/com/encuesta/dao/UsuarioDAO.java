package com.encuesta.dao;

import java.util.List;

import com.encuesta.model.Acceso;
import com.encuesta.model.Cargo;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.Usuario;

public interface UsuarioDAO {

	Usuario insertarOActualizar(Usuario a);    
	List<Usuario> buscarXId(Usuario a)throws Exception; 
	List<Usuario> buscarXUserName(Usuario a)throws Exception; 
	List<Acceso> listarAccesoXcargo(Cargo a)throws Exception;
	List<EncuestaAlumno> listarEncuestaAlumnoXUsuario(Usuario u);
//	List<EncuestaAlumno> listarEncuestaAlumnoXUsuario(Usuario u); 
}
