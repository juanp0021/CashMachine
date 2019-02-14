package com.cashmachine.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@javax.persistence.Entity
@Table(name = "adminCajero")
public class AdminCajero {
	
	private  int id;
	private  int denominacion;
	private  int cantidad;
	
	
	// constructor 
	public void adminCajero(int denominacion,int cantidad ){
		
		
		this.denominacion = denominacion;
		this.cantidad = cantidad;
		
		
	}
	
	
	@Id 
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column (name="cantidad")
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		cantidad = cantidad;
	}
	
	@Column (name="denominacion")
	public int getDenominacion() {
		return denominacion;
	}


	public void setDenominacion(int denominacion) {
		this.denominacion = denominacion;
	}


}
