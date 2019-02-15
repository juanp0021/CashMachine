package com.cashmachine.manageBean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.bytebuddy.asm.Advice.This;

import com.cashmachine.entity.AdminCajero;
import com.cashmachine.services.CashMachineService;
import com.cashmachine.validators.CashMachineValidators;

@ManagedBean(name="transaccion")

@ViewScoped
public class Transaccion implements Serializable {

   private int  valor  = 0; 
	
	public int getValor() {
	return valor;
}

public void setValor(int valor) {
	this.valor = valor;
}

	public void  dispensarDinero(){
		
		
		System.out.println(this.valor);
		
		
	}

}
