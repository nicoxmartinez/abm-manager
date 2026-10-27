package com.ventas.abm.manager.domain;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOCAL")
@PrimaryKeyJoinColumn(name = "ID_TIENDA")
public class Local extends Tienda {

	/**
	 * 
	 */
	private static final long serialVersionUID = -375438973181619417L;

	@OneToMany(mappedBy = "local")
	@OrderColumn(name = "NUMERO_CAJA")
	@JsonIgnore
	private List<PuntoDeVenta> puntosDeVenta;

	@Column(name = "HORARIO_APERTURA")
	private LocalTime horarioApertura;

	@Column(name = "HORARIO_CIERRE")
	private LocalTime horarioCierre;

	@OneToOne
	@JoinColumn(name = "ID_DOMICILIO")
	private Domicilio domicilio;

	public Local() {
		// TODO Auto-generated constructor stub
		super();
		this.setTipo(TipoTienda.FISICA);
	}

	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}

	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
