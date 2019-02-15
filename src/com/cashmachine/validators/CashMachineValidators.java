package com.cashmachine.validators;

import java.util.ArrayList;

import com.cashmachine.entity.AdminCajero;

public class CashMachineValidators {
	
	String resultado ="";
	
	public String  validarFajoBillete(ArrayList<AdminCajero> lista, AdminCajero admincajero){
		
		resultado ="";
		for (AdminCajero adminCajero : lista) {
			
			if(adminCajero.getDenominacion() == admincajero.getDenominacion()){
				
				resultado = "Aleerta! ya existe un billete de la denominación presentada.";
			}
		}
		
		return resultado;
	}
	
	/*
	 * 
	 * VALIDAR VALOR SOLICITADO VS BILLETES EXISTENTES
	 * 
	 * */
	
	public String  validarTransaccion(ArrayList<AdminCajero> lista, int valor){
		
		int total=0;
		resultado ="";
		
		for (AdminCajero adminCajero : lista) {
			total += adminCajero.getCantidad() * adminCajero.getDenominacion();
		}
		
		if (total< valor){
			resultado = "Alerta! no hay dinero suficiente para dispensar su pedido.";
		}
		
		return resultado;
	}

}
