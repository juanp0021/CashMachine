package com.cashmachine.entity;


import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;


@Entity
@Table(appliesTo = "billete")
public class Billete {

	private String denominacionBillete;

	public String getDenominacionBillete() {
		return denominacionBillete;
	}

	public void setDenominacionBillete(String denominacionBillete) {
		this.denominacionBillete = denominacionBillete;
	}
	
}
