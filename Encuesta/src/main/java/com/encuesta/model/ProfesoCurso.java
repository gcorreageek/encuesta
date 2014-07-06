package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profesocurso database table.
 * 
 */
@Entity
@NamedQuery(name="Profesocurso.findAll", query="SELECT p FROM ProfesoCurso p")
public class ProfesoCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProfesoCurso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idProfesor")
	private Usuario usuario;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idCurso")
	private Curso curso;

	public ProfesoCurso() {
	}

	public int getIdProfesoCurso() {
		return this.idProfesoCurso;
	}

	public void setIdProfesoCurso(int idProfesoCurso) {
		this.idProfesoCurso = idProfesoCurso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}