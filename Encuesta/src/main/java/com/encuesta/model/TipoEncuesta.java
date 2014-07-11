package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipoencuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Tipoencuesta.findAll", query="SELECT t FROM TipoEncuesta t")
public class TipoEncuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipoEncuesta;

	private String encuesta;

	private String habilitado;

	//bi-directional many-to-one association to Encuesta
	@OneToMany(mappedBy="tipoencuesta")
	private List<Encuesta> encuestas;

	//bi-directional many-to-one association to Ciclo
	@ManyToOne
	@JoinColumn(name="idCiclo")
	private Ciclo ciclo;

	public TipoEncuesta() {
	}

	 

	public String getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(String encuesta) {
		this.encuesta = encuesta;
	}

	public String getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public List<Encuesta> getEncuestas() {
		return this.encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public Encuesta addEncuesta(Encuesta encuesta) {
		getEncuestas().add(encuesta);
		encuesta.setTipoencuesta(this);

		return encuesta;
	}

	public Encuesta removeEncuesta(Encuesta encuesta) {
		getEncuestas().remove(encuesta);
		encuesta.setTipoencuesta(null);

		return encuesta;
	}

	public Ciclo getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}



	public int getIdTipoEncuesta() {
		return idTipoEncuesta;
	}



	public void setIdTipoEncuesta(int idTipoEncuesta) {
		this.idTipoEncuesta = idTipoEncuesta;
	}

}