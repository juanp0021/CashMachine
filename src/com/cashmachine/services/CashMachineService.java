package com.cashmachine.services;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.cashmachine.entity.AdminCajero;
import com.cashmachine.manageBean.CajeroAdmin;



// el proposito del service esta clase es el codificar las reglas de negocio

public class CashMachineService {

	private EntityManager entityManager;
	
	public CashMachineService() {
		
		entityManager =   Persistence.createEntityManagerFactory("entityManager").createEntityManager();
		
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: Guarda los billetes por medio de la pantalla de administración
	 * */
	public AdminCajero guardar(AdminCajero adminCajero) throws Exception{
		entityManager.getTransaction().begin();
		try {
				
				adminCajero = entityManager.merge(adminCajero);
				entityManager.getTransaction().commit();
			    return adminCajero;	
		}catch(Exception err){
			if (entityManager.getTransaction().isActive()){
			   entityManager.getTransaction().rollback(); 
			}
		} 
	
		return null;
	}// fin de guardar


	@SuppressWarnings("unchecked")
	public ArrayList<AdminCajero> listarBilletes() {
		// TODO Auto-generated method stub
		entityManager.getTransaction().begin();
		try {
				
			ArrayList<AdminCajero> adminCajeroList = new ArrayList<AdminCajero>();
				
			adminCajeroList = (ArrayList<AdminCajero>) entityManager.createQuery(
			            "SELECT p FROM AdminCajero p order by p.denominacion desc").getResultList();
			
				entityManager.getTransaction().commit();
			    
				
				if (adminCajeroList == null){
					adminCajeroList= new ArrayList<AdminCajero>();
				}
				
				return adminCajeroList;
			    
			    
		}catch(Exception err){
			if (entityManager.getTransaction().isActive()){
			   entityManager.getTransaction().rollback(); 
			}
		} 
		
		
		return null;
	}

	
	public int eliminar(AdminCajero adminCajero){
		
		entityManager.getTransaction().begin();
		try {
				entityManager.remove(adminCajero);
				entityManager.getTransaction().commit();
			    return 1;	
		}catch(Exception err){
			if (entityManager.getTransaction().isActive()){
			   entityManager.getTransaction().rollback(); 
			}
			
			return 0;
		} 
	}

	//hacer la logica para que valide que billetes le va a devolver por denominación
	
	public ArrayList<AdminCajero> dispensarBilletes(ArrayList<AdminCajero> billetes, int valor) {
		
		ArrayList<AdminCajero> billetesDispensados = new ArrayList<AdminCajero>();
		int total = 0;
		int cantidad =0;
		
		for (AdminCajero fajo : billetes) {
			AdminCajero admincajero = new AdminCajero();
            if (valor >= fajo.getDenominacion()) { 
            	cantidad = calcularCantidadMaxima(fajo,valor);
            	valor = valor - cantidad * fajo.getDenominacion();
            	admincajero.setCantidad(cantidad);
            	admincajero.setDenominacion(fajo.getDenominacion());
            	if(cantidad>0){
            		billetesDispensados.add(admincajero);
            	}
           } 

		}
		
		
		return billetesDispensados;
	}

	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: Realiza el calculo y suministro de los billetes 
	 * de tal manera que se devuelva la cantidad justa solicitada.
	 * */
	public int  calcularCantidadMaxima(AdminCajero fajo, int valor){
		
		if(valor%fajo.getDenominacion()==0){
			int totalmaximo=0;
			for (int i =1; i<= fajo.getCantidad(); i++){
		
				totalmaximo += fajo.getDenominacion();
		    	if (totalmaximo==valor){
		    		return i;
		    	}else if(totalmaximo>valor){
		    		return i-1;
		    	}	
		     }
			 return fajo.getCantidad();
		}
		
		return 0;
	}
	
	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: Elimina los billetes dispensados del stock por medio de una transaccion
	 * */
	public ArrayList<AdminCajero> descontarBilletes(
			ArrayList<AdminCajero> billetesDispensados,
			ArrayList<AdminCajero> billetes) {
		
		
		entityManager.getTransaction().begin();
		try {
			for (AdminCajero fajodisponible : billetes) {
				for (AdminCajero fajoentregado : billetesDispensados) {
					if (fajodisponible.getDenominacion() == fajoentregado.getDenominacion() ){
						 int cantidadActual =fajodisponible.getCantidad() - fajoentregado.getCantidad(); 
						 fajodisponible.setCantidad(cantidadActual);
						 entityManager.merge(fajodisponible);
							
					}
				}
		 	}
				entityManager.getTransaction().commit();
				return billetes;	    	
				
		}catch(Exception err){
			if (entityManager.getTransaction().isActive()){
			   entityManager.getTransaction().rollback(); 
			}
			return null;
		} 
		
		
	}
	
	
}
