package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the matricula database table.
 * 
 */
@Entity
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
@NamedQueries({  
	@NamedQuery(name="Matricula.findId", query="SELECT u FROM Matricula u WHERE u.idMatricula = ?1 "),
	@NamedQuery(name="Matricula.findIdAsignacionProfesor", query="SELECT u FROM Matricula u WHERE u.asignacionprofesor.idAsignacionProfesor = ?1   ")
})
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMatricula;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Asignacionprofesor
	@ManyToOne
	@JoinColumn(name="idAsignacion")
	private AsignacionProfesor asignacionprofesor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idAlumno")
	private Usuario usuario;

	public Matricula() {
	}

	public int getIdMatricula() {
		return this.idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public AsignacionProfesor getAsignacionprofesor() {
		return this.asignacionprofesor;
	}

	public void setAsignacionprofesor(AsignacionProfesor asignacionprofesor) {
		this.asignacionprofesor = asignacionprofesor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}