package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCargo;

	private String cargo;

	//bi-directional many-to-one association to Acceso
	@OneToMany(mappedBy="cargo")
	private List<Acceso> accesos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="cargoDelUsuario")
	private List<Usuario> usuarios;

	public Cargo() {
	}

	public int getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Acceso> getAccesos() {
//		if(this.accesos==null){
//			this.accesos = new ArrayList<Acceso>();
//		}
		return this.accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public Acceso addAcceso(Acceso acceso) {
		getAccesos().add(acceso);
		acceso.setCargo(this);

		return acceso;
	}

	public Acceso removeAcceso(Acceso acceso) {
		getAccesos().remove(acceso);
		acceso.setCargo(null);

		return acceso;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

//	public Usuario addUsuario(Usuario usuario) {
//		getUsuarios().add(usuario);
//		usuario.setCargo(this);
//
//		return usuario;
//	}
//
//	public Usuario removeUsuario(Usuario usuario) {
//		getUsuarios().remove(usuario);
//		usuario.setCargo(null);
//
//		return usuario;
//	}

}