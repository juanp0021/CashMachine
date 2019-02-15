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
			            "SELECT p FROM AdminCajero p").getResultList();
			
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


	public ArrayList<AdminCajero> dispensarBilletes(
			ArrayList<AdminCajero> billetes, int valor) {
		//PILASSSSSSSSSSSSSSSSSSSSSSS
		//hacer la logica para que valide que billetes le va a devolver por denominaci�n
		
		
		return billetes;
	}


	
	  /// elimina los billetes dispensados  usar .merge
	public ArrayList<AdminCajero> descontarBilletes(
			ArrayList<AdminCajero> billetesDispensados,
			ArrayList<AdminCajero> billetes) {
		
		
		//PILASSSSSSSSSSSSSSSSSSSSSSS
		//hacer la logica para que valide que billetes le va a devolver por denominaci�n
				
		
		return null;
	}
}
