package com.ventas.abm.manager.models;

import java.util.Date;

public class MedioDePagoDto {

	private Long idMedioPago;
	private int codigo;
	private String detalle;
	private String tipo;
	private Boolean habilitado;
	private Date fechaBaja;
	private FinanciacionDto financiacion;

	public MedioDePagoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public FinanciacionDto getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(FinanciacionDto financiacion) {
		this.financiacion = financiacion;
	}

}
