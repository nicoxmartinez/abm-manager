package com.ventas.abm.manager.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEDIO_PAGO")
public class MedioDePago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MEDIO_PAGO")
	private Long idMedioPago;

	@Column(name = "CODIGO")
	private int codigo;

	@Column(name = "DETALLE")
	private String detalle;

	@Column(name = "ABREVIATURA")
	private String abreviatura;

	@Column(name = "CANTIDAD_MAX_CUOTAS")
	private int cantidadMaximaCuotas;

	@Column(name = "TIPO_TARJETA")
	private String tipoTarjeta;

	@Column(name = "HABILITADO")
	private Boolean habilitado;

	@Column(name = "PERMITE_BONIFICACION")
	private Boolean permiteBonificacion;

	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@ManyToOne
	@JoinColumn(name = "ID_FINANCIACION")
	private Financiacion financiacion;
	
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

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getCantidadMaximaCuotas() {
		return cantidadMaximaCuotas;
	}

	public void setCantidadMaximaCuotas(int cantidadMaximaCuotas) {
		this.cantidadMaximaCuotas = cantidadMaximaCuotas;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Boolean getPermiteBonificacion() {
		return permiteBonificacion;
	}

	public void setPermiteBonificacion(Boolean permiteBonificacion) {
		this.permiteBonificacion = permiteBonificacion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

}
