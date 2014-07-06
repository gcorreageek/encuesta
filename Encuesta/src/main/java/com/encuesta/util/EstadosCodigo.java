package com.encuesta.util;

public enum EstadosCodigo {
	VACIO(0), SIN_ERROR(1), CON_ERROR(2) ;

	private Integer estadosCodigo;
	private EstadosCodigo(Integer s) {
		estadosCodigo = s;
	}
	public Integer getEstadosCodigo() {
		return estadosCodigo;
	}
}
 