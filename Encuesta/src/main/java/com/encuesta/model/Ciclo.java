package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ciclo database table.
 * 
 */
@Entity
@NamedQuery(name="Ciclo.findAll", query="SELECT c FROM Ciclo c")
public class Ciclo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCiclo;

	@Column(name="annio_d")
	private int annioD;

	@Column(name="estado_ciclo_d")
	private int estadoCicloD;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String habilitado;

	@Column(name="modalidad_d")
	private int modalidadD;

	private String nombre;

	@Column(name="numero_d")
	private int numeroD;

	//bi-directional many-to-one association to Anio
	@OneToMany(mappedBy="ciclo")
	private List<Anio> anios;

	//bi-directional many-to-one association to Tipoencuesta
	@OneToMany(mappedBy="ciclo")
	private List<TipoEncuesta> tipoencuestas;

	public Ciclo() {
	}

	public int getIdCiclo() {
		return this.idCiclo;
	}

	public void setIdCiclo(int idCiclo) {
		this.idCiclo = idCiclo;
	}

	public int getAnnioD() {
		return this.annioD;
	}

	public void setAnnioD(int annioD) {
		this.annioD = annioD;
	}

	public int getEstadoCicloD() {
		return this.estadoCicloD;
	}

	public void setEstadoCicloD(int estadoCicloD) {
		this.estadoCicloD = estadoCicloD;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public int getModalidadD() {
		return this.modalidadD;
	}

	public void setModalidadD(int modalidadD) {
		this.modalidadD = modalidadD;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroD() {
		return this.numeroD;
	}

	public void setNumeroD(int numeroD) {
		this.numeroD = numeroD;
	}

	public List<Anio> getAnios() {
		return this.anios;
	}

	public void setAnios(List<Anio> anios) {
		this.anios = anios;
	}

	public Anio addAnio(Anio anio) {
		getAnios().add(anio);
		anio.setCiclo(this);

		return anio;
	}

	public Anio removeAnio(Anio anio) {
		getAnios().remove(anio);
		anio.setCiclo(null);

		return anio;
	}

	public List<TipoEncuesta> getTipoencuestas() {
		return this.tipoencuestas;
	}

	public void setTipoencuestas(List<TipoEncuesta> tipoencuestas) {
		this.tipoencuestas = tipoencuestas;
	}

	public TipoEncuesta addTipoencuesta(TipoEncuesta tipoencuesta) {
		getTipoencuestas().add(tipoencuesta);
		tipoencuesta.setCiclo(this);

		return tipoencuesta;
	}

	public TipoEncuesta removeTipoencuesta(TipoEncuesta tipoencuesta) {
		getTipoencuestas().remove(tipoencuesta);
		tipoencuesta.setCiclo(null);

		return tipoencuesta;
	}

}