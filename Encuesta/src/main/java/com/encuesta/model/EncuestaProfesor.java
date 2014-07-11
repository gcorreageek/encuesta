package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the encuestaprofesor database table.
 * 
 */
@Entity
@NamedQuery(name="Encuestaprofesor.findAll", query="SELECT e FROM EncuestaProfesor e")
@NamedQueries({  
	@NamedQuery(name="Encuestaprofesor.findId", query="SELECT u FROM EncuestaProfesor u WHERE u.idEncuestaProfesor = ?1 "),
	@NamedQuery(name="Encuestaprofesor.findIdEncuestaIdProfesor", query="SELECT u FROM EncuestaProfesor u WHERE u.encuesta.idEncuesta = ?1 and u.usuario.idUsuario = ?2 ")
})
public class EncuestaProfesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEncuestaProfesor;

	//bi-directional many-to-one association to Encuestaalumno
	@OneToMany(mappedBy="encuestaprofesor")
	private List<EncuestaAlumno> encuestaalumnos;

	//bi-directional many-to-one association to Encuesta
	@ManyToOne
	@JoinColumn(name="idEncuesta")
	private Encuesta encuesta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idProfesor")
	private Usuario usuario;

	public EncuestaProfesor() {
	}

	public int getIdEncuestaProfesor() {
		return this.idEncuestaProfesor;
	}

	public void setIdEncuestaProfesor(int idEncuestaProfesor) {
		this.idEncuestaProfesor = idEncuestaProfesor;
	}

	public List<EncuestaAlumno> getEncuestaalumnos() {
		return this.encuestaalumnos;
	}

	public void setEncuestaalumnos(List<EncuestaAlumno> encuestaalumnos) {
		this.encuestaalumnos = encuestaalumnos;
	}

	public EncuestaAlumno addEncuestaalumno(EncuestaAlumno encuestaalumno) {
		getEncuestaalumnos().add(encuestaalumno);
		encuestaalumno.setEncuestaprofesor(this);

		return encuestaalumno;
	}

	public EncuestaAlumno removeEncuestaalumno(EncuestaAlumno encuestaalumno) {
		getEncuestaalumnos().remove(encuestaalumno);
		encuestaalumno.setEncuestaprofesor(null);

		return encuestaalumno;
	}

	public Encuesta getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}