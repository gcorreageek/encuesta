package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the dominio database table.
 * 
 */
@Entity
@NamedQuery(name="Dominio.findAll", query="SELECT d FROM Dominio d")
@NamedQueries({  
	@NamedQuery(name="Dominio.findId", query="SELECT u FROM Dominio u WHERE u.idDominio = ?1 "),
	@NamedQuery(name="Dominio.findCampo", query="SELECT u FROM Dominio u WHERE u.campo like ?1 ")
})
public class Dominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDominio;

	private String campo;

	private String habilitado;

	@Column(name="mas_datos")
	private String masDatos;

	private String obligatorio;

	private String valor;

	@Column(name="valor_corto")
	private String valorCorto;

	//bi-directional many-to-one association to Dominio
	@ManyToOne
	@JoinColumn(name="idDominio_hijo")
	private Dominio dominio1;

	//bi-directional many-to-one association to Dominio
	@OneToMany(mappedBy="dominio1")
	private List<Dominio> dominios1;

	//bi-directional many-to-one association to Dominio
	@ManyToOne
	@JoinColumn(name="idDominio_sub")
	private Dominio dominio2;

	//bi-directional many-to-one association to Dominio
	@OneToMany(mappedBy="dominio2")
	private List<Dominio> dominios2;

	public Dominio() {
	}

	public int getIdDominio() {
		return this.idDominio;
	}

	public void setIdDominio(int idDominio) {
		this.idDominio = idDominio;
	}

	public String getCampo() {
		return this.campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getMasDatos() {
		return this.masDatos;
	}

	public void setMasDatos(String masDatos) {
		this.masDatos = masDatos;
	}

	public String getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorCorto() {
		return this.valorCorto;
	}

	public void setValorCorto(String valorCorto) {
		this.valorCorto = valorCorto;
	}

	public Dominio getDominio1() {
		return this.dominio1;
	}

	public void setDominio1(Dominio dominio1) {
		this.dominio1 = dominio1;
	}

	public List<Dominio> getDominios1() {
		return this.dominios1;
	}

	public void setDominios1(List<Dominio> dominios1) {
		this.dominios1 = dominios1;
	}

	public Dominio addDominios1(Dominio dominios1) {
		getDominios1().add(dominios1);
		dominios1.setDominio1(this);

		return dominios1;
	}

	public Dominio removeDominios1(Dominio dominios1) {
		getDominios1().remove(dominios1);
		dominios1.setDominio1(null);

		return dominios1;
	}

	public Dominio getDominio2() {
		return this.dominio2;
	}

	public void setDominio2(Dominio dominio2) {
		this.dominio2 = dominio2;
	}

	public List<Dominio> getDominios2() {
		return this.dominios2;
	}

	public void setDominios2(List<Dominio> dominios2) {
		this.dominios2 = dominios2;
	}

	public Dominio addDominios2(Dominio dominios2) {
		getDominios2().add(dominios2);
		dominios2.setDominio2(this);

		return dominios2;
	}

	public Dominio removeDominios2(Dominio dominios2) {
		getDominios2().remove(dominios2);
		dominios2.setDominio2(null);

		return dominios2;
	}

}