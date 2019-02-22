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
import com.cashmachine.services.CashMachineService;

@ManagedBean(name = "transaccion")
@ViewScoped
public class Transaccion implements Serializable {

	private int valor = 0;
	private ArrayList<AdminCajero> billetes;
	private ArrayList<AdminCajero> billetesDispensados; // resultado de los billetes dispensados
	public ArrayList<AdminCajero> getBilletes() {
		return billetes;
	}

	public void setBilletes(ArrayList<AdminCajero> billetes) {
		this.billetes = billetes;
	}

	public ArrayList<AdminCajero> getBilletesDispensados() {
		return billetesDispensados;
	}

	public void setBilletesDispensados(ArrayList<AdminCajero> billetesDispensados) {
		this.billetesDispensados = billetesDispensados;
	}

	CashMachineService cashMachineService;

	// se inicializa los objetos en caso de ser null
	@PostConstruct
	public void init() {
		this.cashMachineService = new CashMachineService();
		loadBilletes();
	}

	public void loadBilletes() {

		this.billetes = cashMachineService.listarBilletes();

	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void reiniciar (){
		
		
		this.billetesDispensados = new ArrayList<AdminCajero>();
		this.valor =0;
		
	}

	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: Realiza el calculo y suministro de los billetes segun el pedido del usuario.
	 * 
	 * */
	public void dispensarDinero() {

		System.out.println(this.valor);
		FacesContext context = FacesContext.getCurrentInstance();
		CashMachineValidators validar = new CashMachineValidators();
		// set de validaciones para la transacción actual
		String resultado  =	validar.validarTransaccion(this.billetes, this.valor);
		
		if (resultado !=""){
			context.addMessage(null, new FacesMessage("Resultado",resultado) );
			this.billetesDispensados = new ArrayList<AdminCajero>();
			return;
		}else{
			
			//Recuperar billetes que se van a descontar  
			this.billetesDispensados = cashMachineService.dispensarBilletes(this.billetes, this.valor);
			
			
			 resultado = validar.validarCantidadEntregada(this.billetesDispensados,this.valor);
			if (resultado !=""){
				this.billetesDispensados.clear();
				context.addMessage(null, new FacesMessage("Resultado",resultado));
			}
			else if (this.billetesDispensados.isEmpty()){
				context.addMessage(null, new FacesMessage("Resultado","El cajero no tiene recursos financieros suficientes para dispensar su pedido.") );	
			}else{
				cashMachineService.descontarBilletes(this.billetesDispensados,this.billetes);
				loadBilletes();
			}
		}

	} // fin de dispensar dinero

}
