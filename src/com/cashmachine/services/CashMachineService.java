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
	
	
	public AdminCajero guardar(AdminCajero adminCajero) throws Exception{
		
		
		
		entityManager.getTransaction().begin();
		try {
				System.out.println("id------"+adminCajero.getId());
				adminCajero = entityManager.merge(adminCajero);
				System.out.println("id>>>>>>"+adminCajero.getId());
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
            	cantidad = valor / fajo.getDenominacion(); 
            	valor = valor - cantidad * fajo.getDenominacion();
            	if (cantidad>fajo.getCantidad()){
            		continue;
            	}
            	admincajero.setCantidad(cantidad);
            	admincajero.setDenominacion(fajo.getDenominacion());
            	billetesDispensados.add(admincajero);
                System.out.println( "MONTO FALTANTE" + valor );
            } 

		}
		
		
		return billetesDispensados;
		
		/*
		 
		    int amount = 60000;
            int notes[] = { 100000,50000 ,20000 ,10000 }; 
            int[] noteCounter = new int[9];
              
            // count notes using Greedy approach 
            for (int i = 0; i < notes.length; i++) { 
                if (amount >= notes[i]) { 
                    noteCounter[i] = amount / notes[i]; 
                    amount = amount - noteCounter[i] * notes[i]; 
                     //System.out.println( "MONTO" + amount );
                } 
                 System.out.println( notes[i] );
                 System.out.println( noteCounter[i] );
            } 
		  
		 
		 */
	}


	
	  /// elimina los billetes dispensados  usar .merge
	public ArrayList<AdminCajero> descontarBilletes(
			ArrayList<AdminCajero> billetesDispensados,
			ArrayList<AdminCajero> billetes) {
		
		
		//PILASSSSSSSSSSSSSSSSSSSSSSS
		//hacer la logica para que valide que billetes le va a devolver por denominación
				
		
		return null;
	}
	
	
	
}
