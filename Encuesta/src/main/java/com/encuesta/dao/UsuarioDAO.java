package com.encuesta.dao;

import java.util.List;

import com.encuesta.model.Usuario;

public interface UsuarioDAO {

	Usuario insertarOActualizar(Usuario a);    
	List<Usuario> buscarXId(Usuario a)throws Exception; 
	List<Usuario> buscarXUserName(Usuario a)throws Exception; 
}
