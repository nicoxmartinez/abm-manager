package com.ventas.abm.manager.models;

import java.util.List;

public class FinanciacionDto {

	private Long idFinanciacion;
	private Integer codigo;
	private String descripcion;
	private Boolean activa;
	private List<Integer> planCuotas;

	public FinanciacionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public List<Integer> getPlanCuotas() {
		return planCuotas;
	}

	public void setPlanCuotas(List<Integer> planCuotas) {
		this.planCuotas = planCuotas;
	}

}
