package com.ventas.abm.manager.models;

public class PuntoDeVentaDto {

	private Long idPuntoDeVenta;
	private Integer numeroTerminal;
	private String nombreTerminal;
	private Short numeroDeCaja;

	public PuntoDeVentaDto() {
		super();
		// TODO Auto-generated constructor stub
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

}
