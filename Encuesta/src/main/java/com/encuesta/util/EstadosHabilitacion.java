package com.encuesta.util;

public enum EstadosHabilitacion{
	SIN_ESTADO(0), HABILITADO(1), DESABILITADO(2) ;

	private Integer estadosHabilitacion;
	private EstadosHabilitacion(Integer s) {
		estadosHabilitacion = s;
	}
	public Integer getEstadosHabilitacion() {
		return estadosHabilitacion;
	}
}
 