package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the acceso database table.
 * 
 */
@Entity
@NamedQuery(name="Acceso.findAll", query="SELECT a FROM Acceso a")
public class Acceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAcceso;

	private String habilitado;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="idCargo")
	private Cargo cargo;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="idMenu")
	private Menu menu;

	public Acceso() {
	}

	public int getIdAcceso() {
		return this.idAcceso;
	}

	public void setIdAcceso(int idAcceso) {
		this.idAcceso = idAcceso;
	}

	public String getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public Cargo getCargo() {
//		if(this.cargo==null){
//			this.cargo = new Cargo();
//		}
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Menu getMenu() {
//		if(this.menu==null){
//			this.menu = new Menu();
//		}
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}