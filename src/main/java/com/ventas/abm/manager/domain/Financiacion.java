package com.ventas.abm.manager.domain;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "FINANCIACION")
public class Financiacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FINANCIACION")
	private Long idFinanciacion;

	@Column(name = "CODIGO")
	private Integer codigo;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ACTIVA")
	private Boolean activa;

	@OneToMany
	@OrderColumn(name = "CANTIDAD_CUOTAS")
	private List<PlanCuota> planCuota;

	public Long getIdFinanciacion() {
		return idFinanciacion;
	}

	public void setIdFinanciacion(Long idFinanciacion) {
		this.idFinanciacion = idFinanciacion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public List<PlanCuota> getPlanCuota() {
		return planCuota;
	}

	public void setPlanCuota(List<PlanCuota> planCuota) {
		this.planCuota = planCuota;
	}

}