package com.cashmachine.services;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.cashmachine.entity.AdminCajero;



// el proposito del service esta clase es el codificar las reglas de negocio

public class CashMachineService {

	private EntityManager entityManager;
	
	public CashMachineService() {
		
		entityManager =   Persistence.createEntityManagerFactory("entityManager").createEntityManager();
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean guardar(AdminCajero adminCajero) throws Exception{
		
		// aca va el persist
		/*
	
		if (entityManager.isOpen()){
			entityManager.getTransaction().begin();
			entityManager.persist(adminCajero);
			entityManager.getTransaction().commit();
		
		}
		*/
		
		entityManager.getTransaction().begin();
		try {
		    //do something with your database
			System.out.println("id------"+adminCajero.getId());
			adminCajero = entityManager.merge(adminCajero);
			
			System.out.println("id>>>>>>"+adminCajero.getId());
			
			
			
			entityManager.getTransaction().commit();
		} 
		finally {
		   if (entityManager.getTransaction().isActive())
			   entityManager.getTransaction().rollback(); 
		}
		
			
				
				
		//entityManager.close();
		
		return false;
	}

}
