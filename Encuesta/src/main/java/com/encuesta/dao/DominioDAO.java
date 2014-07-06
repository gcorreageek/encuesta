package com.encuesta.dao;

import java.util.List;

import com.encuesta.model.Dominio;

public interface DominioDAO {

	Dominio insertarOActualizar(Dominio a);    
	List<Dominio> buscarXId(Dominio a)throws Exception; 
	List<Dominio> buscarXCampo(Dominio a)throws Exception; 
}
