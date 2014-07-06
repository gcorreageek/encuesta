package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurso;

	private String curso;

	@Column(name="curso_corto")
	private String cursoCorto;

	@Column(name="tipo_curso_d")
	private int tipoCursoD;

	//bi-directional many-to-one association to Anio
	@OneToMany(mappedBy="curso")
	private List<Anio> anios;

	//bi-directional many-to-one association to Profesocurso
	@OneToMany(mappedBy="curso")
	private List<ProfesoCurso> profesocursos;

	public Curso() {
	}

	public int getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCursoCorto() {
		return this.cursoCorto;
	}

	public void setCursoCorto(String cursoCorto) {
		this.cursoCorto = cursoCorto;
	}

	public int getTipoCursoD() {
		return this.tipoCursoD;
	}

	public void setTipoCursoD(int tipoCursoD) {
		this.tipoCursoD = tipoCursoD;
	}

	public List<Anio> getAnios() {
		return this.anios;
	}

	public void setAnios(List<Anio> anios) {
		this.anios = anios;
	}

	public Anio addAnio(Anio anio) {
		getAnios().add(anio);
		anio.setCurso(this);

		return anio;
	}

	public Anio removeAnio(Anio anio) {
		getAnios().remove(anio);
		anio.setCurso(null);

		return anio;
	}

	public List<ProfesoCurso> getProfesocursos() {
		return this.profesocursos;
	}

	public void setProfesocursos(List<ProfesoCurso> profesocursos) {
		this.profesocursos = profesocursos;
	}

	public ProfesoCurso addProfesocurso(ProfesoCurso profesocurso) {
		getProfesocursos().add(profesocurso);
		profesocurso.setCurso(this);

		return profesocurso;
	}

	public ProfesoCurso removeProfesocurso(ProfesoCurso profesocurso) {
		getProfesocursos().remove(profesocurso);
		profesocurso.setCurso(null);

		return profesocurso;
	}

}