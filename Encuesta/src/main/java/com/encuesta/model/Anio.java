package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the anio database table.
 * 
 */
@Entity
@NamedQuery(name="Anio.findAll", query="SELECT a FROM Anio a")
public class Anio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnio;

	@Column(name="ciclo_academico_d")
	private int cicloAcademicoD;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name="maximo_alumnos")
	private int maximoAlumnos;

	@Column(name="minimo_alumnos")
	private int minimoAlumnos;

	@Column(name="nombre_seccion")
	private String nombreSeccion;

	@Column(name="session_d")
	private int sessionD;

	//bi-directional many-to-one association to Carrera
	@ManyToOne
	@JoinColumn(name="idCarrera")
	private Carrera carrera;

	//bi-directional many-to-one association to Ciclo
	@ManyToOne
	@JoinColumn(name="idCiclo")
	private Ciclo ciclo;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idCurso")
	private Curso curso;

	//bi-directional many-to-one association to Asignacionprofesor
	@OneToMany(mappedBy="anio")
	private List<AsignacionProfesor> asignacionprofesors;

	//bi-directional many-to-one association to Encuesta
	@OneToMany(mappedBy="anio")
	private List<Encuesta> encuestas;

	public Anio() {
	}

	public int getIdAnio() {
		return this.idAnio;
	}

	public void setIdAnio(int idAnio) {
		this.idAnio = idAnio;
	}

	public int getCicloAcademicoD() {
		return this.cicloAcademicoD;
	}

	public void setCicloAcademicoD(int cicloAcademicoD) {
		this.cicloAcademicoD = cicloAcademicoD;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getMaximoAlumnos() {
		return this.maximoAlumnos;
	}

	public void setMaximoAlumnos(int maximoAlumnos) {
		this.maximoAlumnos = maximoAlumnos;
	}

	public int getMinimoAlumnos() {
		return this.minimoAlumnos;
	}

	public void setMinimoAlumnos(int minimoAlumnos) {
		this.minimoAlumnos = minimoAlumnos;
	}

	public String getNombreSeccion() {
		return this.nombreSeccion;
	}

	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}

	public int getSessionD() {
		return this.sessionD;
	}

	public void setSessionD(int sessionD) {
		this.sessionD = sessionD;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Ciclo getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<AsignacionProfesor> getAsignacionprofesors() {
		return this.asignacionprofesors;
	}

	public void setAsignacionprofesors(List<AsignacionProfesor> asignacionprofesors) {
		this.asignacionprofesors = asignacionprofesors;
	}

	public AsignacionProfesor addAsignacionprofesor(AsignacionProfesor asignacionprofesor) {
		getAsignacionprofesors().add(asignacionprofesor);
		asignacionprofesor.setAnio(this);

		return asignacionprofesor;
	}

	public AsignacionProfesor removeAsignacionprofesor(AsignacionProfesor asignacionprofesor) {
		getAsignacionprofesors().remove(asignacionprofesor);
		asignacionprofesor.setAnio(null);

		return asignacionprofesor;
	}

	public List<Encuesta> getEncuestas() {
		return this.encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public Encuesta addEncuesta(Encuesta encuesta) {
		getEncuestas().add(encuesta);
		encuesta.setAnio(this);

		return encuesta;
	}

	public Encuesta removeEncuesta(Encuesta encuesta) {
		getEncuestas().remove(encuesta);
		encuesta.setAnio(null);

		return encuesta;
	}

}