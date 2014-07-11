package com.encuesta.service;

import java.util.List;

import com.encuesta.model.Acceso;
import com.encuesta.model.Cargo;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.Usuario;

public interface UsuarioService {

	Usuario insertar(Usuario a)throws Exception;
	Usuario actualiza(Usuario a)throws Exception;  
	List<Usuario> listar()throws Exception;
	List<Usuario> buscarXIdUsario(Usuario a)throws Exception; 
	List<Usuario> buscarXUserName(Usuario a)throws Exception;
	
	Object insertarMuchos(List<Usuario> lstUsuario); 
	List<Acceso> listarAccesosXIdCargo(Cargo a)throws Exception;
	List<EncuestaAlumno> listarEncuestaAlumnoXUsuario(Usuario u)throws Exception;
}
