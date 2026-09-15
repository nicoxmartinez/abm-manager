package com.ventas.abm.manager.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DOMICILIO")
public class Domicilio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DOMICILIO")
	private Long idDomicilio;

	@Column(name = "CALLE")
	private String calle;

	@Column(name = "NUMERO")
	private Integer numero;

	@Column(name = "LOCALIDAD")
	private String localidad;

	@Column(name = "PARTIDO")
	private String partido;

	@Column(name = "CODIGO_POSTAL")
	private String codigoPostal;

	@Column(name = "ENTRE_CALLES")
	private String entreCalles;

	@Column(name = "PROVINCIA")
	private String provincia;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "DIRECCION")
	private String direccion;

	public Long getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(Long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEntreCalles() {
		return entreCalles;
	}

	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}