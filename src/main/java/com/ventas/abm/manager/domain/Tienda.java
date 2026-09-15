package com.ventas.abm.manager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIENDA")
public class Tienda implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIENDA")
    private Long idTienda;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CODIGO")
    private String codigo;
    
    @OneToOne
    @JoinColumn(name = "ID_DOMICILIO")
    private Domicilio domicilio;

    @OneToMany
    @OrderBy(value="ID_ALMACEN")
    private List<Almacen> almacenes;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "FECHA_BAJA")
    private Date fechaBaja;

    @Column(name = "MAIL_CONTACTO")
    private String mailContacto;

	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Almacen> getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getMailContacto() {
		return mailContacto;
	}

	public void setMailContacto(String mailContacto) {
		this.mailContacto = mailContacto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
	