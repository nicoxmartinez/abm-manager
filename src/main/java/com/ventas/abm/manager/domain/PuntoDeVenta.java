package com.ventas.abm.manager.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUNTO_DE_VENTA")
public class PuntoDeVenta {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PUNTO_VENTA")
	private Long idPuntoDeVenta;

	@Column(name = "NUMERO_TERMINAL")
	private Integer numeroTerminal;

	@Column(name = "NOMBRE_TERMINAL")
	private String nombreTerminal;

	@Column(name = "NUMERO_CAJA")
	private Short numeroDeCaja;

	public PuntoDeVenta() {
		// TODO Auto-generated constructor stub
		super();
	}

	public PuntoDeVenta(Integer numeroTerminal, String nombreTerminal, Short numeroDeCaja) {
		this.numeroTerminal = numeroTerminal;
		this.nombreTerminal = nombreTerminal;
		this.numeroDeCaja = numeroDeCaja;
	}

	public Long getIdPuntoDeVenta() {
		return idPuntoDeVenta;
	}

	public void setIdPuntoDeVenta(Long idPuntoDeVenta) {
		this.idPuntoDeVenta = idPuntoDeVenta;
	}

	public Integer getNumeroTerminal() {
		return numeroTerminal;
	}

	public void setNumeroTerminal(Integer numeroTerminal) {
		this.numeroTerminal = numeroTerminal;
	}

	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

	public Short getNumeroDeCaja() {
		return numeroDeCaja;
	}

	public void setNumeroDeCaja(Short numeroDeCaja) {
		this.numeroDeCaja = numeroDeCaja;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
