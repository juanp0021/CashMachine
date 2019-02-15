package com.cashmachine.validators;

import java.util.ArrayList;

import com.cashmachine.entity.AdminCajero;

public class CashMachineValidators {
	
	String resultado ="";
	
	public String  validarFajoBillete(ArrayList<AdminCajero> lista, AdminCajero admincajero){
		
		
		for (AdminCajero adminCajero : lista) {
			
			if(adminCajero.getDenominacion() == admincajero.getDenominacion()){
				
				resultado = "Aleerta! ya existe un billete de la denominación presentada.";
			}
		}
		
		return resultado;
	}

}
