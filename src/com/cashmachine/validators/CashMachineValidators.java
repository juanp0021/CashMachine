package com.cashmachine.validators;

import java.util.ArrayList;

import com.cashmachine.entity.AdminCajero;

public class CashMachineValidators {
	
	String resultado ="";
	
	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: valida las entradas y salidas de cada una de los ingresos y egresos de billetes a realizar.
	 * 
	 * */
	


	public String  validarFajoBillete(ArrayList<AdminCajero> lista, AdminCajero admincajero){

		resultado ="";

		if(lista != null) {

			for (AdminCajero adminCajero : lista) {

				

				if(adminCajero.getDenominacion() == admincajero.getDenominacion()){

					

					resultado = "Alerta! ya existe un billete de la denominaci?n presentada.";

				}

				

			}

		}
		return resultado;
	}

	
	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: valida las entradas de cada una de las transacciones a realizar
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
		
		if (valor%10 != 0)
	    {
			resultado = "Alerta! Transacción Invalida: no es posible dispensar la cantidad solicitada acerquese a nuestras oficinas.";
	        
	    }

		if (valor< 0 ){
			resultado = "Alerta! El valor solicitado no debe ser un valor negativo.";
		}
		
		return resultado;
	}
	
	public String validarCantidadEntregada(ArrayList<AdminCajero> billetesDispensados, int valor){
		
		resultado ="";
		int totalentregado = 0;
		for (AdminCajero adminCajero : billetesDispensados) {
			totalentregado += adminCajero.getCantidad() * adminCajero.getDenominacion();
		}
		if (totalentregado != valor){
			
			resultado = "Alerta! El valor solicitado no puede ser entregado.";
		}

		return resultado;
	}
		

}
