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

@ManagedBean(name="cajeroAdmin")

@ViewScoped
public class CajeroAdmin implements Serializable {
	
	
	//  SE DOCUMENTA EL ATRIBUTO
	private AdminCajero admincajero;
	private boolean obligatorio;
	private CashMachineService cashMachineService;
	private ArrayList<AdminCajero> billetes;
	
	public ArrayList<AdminCajero> getBilletes() {
		return billetes;
	}

	public void setBilletes(ArrayList<AdminCajero> billetes) {
		this.billetes = billetes;
	}

	public AdminCajero getAdmincajero() {
		return admincajero;
	}

	public void setAdmincajero(AdminCajero admincajero) {
		this.admincajero = admincajero;
	}

	
	public CashMachineService getCashMachineService() {
		return cashMachineService;
	}

	
	public void setCashMachineService(CashMachineService cashMachineService) {
		this.cashMachineService = cashMachineService;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public CajeroAdmin() {
		super();
		init();
	
	}

	// se inicializa los objetos en caso de ser null
	@PostConstruct
	public void  init (){
		this.admincajero = new AdminCajero();
		this.cashMachineService = new CashMachineService();
		this.obligatorio = true;
		loadBilletes ();
	}

	
	public void loadBilletes (){
		
		this.billetes = cashMachineService.listarBilletes();
		
	}
		
	/*
	 * Autor Juan Pablo castiblanco
	 * Descripción: Guarda la cantidad y la denominacion de los billetes
	 * */
	public void  guardarCantidad(){
		
		CashMachineValidators validar = new CashMachineValidators();
		FacesContext context = FacesContext.getCurrentInstance();
      
		String resultado = validar.validarFajoBillete(this.billetes,this.admincajero);
		
		if(resultado != ""){
			  context.addMessage(null, new FacesMessage("Error",  resultado) );
			  return;
		}
		
		AdminCajero resultadoAdminCajero;
		try {
			
			resultadoAdminCajero = cashMachineService.guardar(this.admincajero);
			
			if (resultadoAdminCajero != null){
				// una vez guarda limpia  las variables
			     this.admincajero = new AdminCajero();
				 context.addMessage(null, new FacesMessage("Resultado",  "se guardo el registro exitosamente.") );
		         this.billetes.add(resultadoAdminCajero);
		         
			}else{
		
		         context.addMessage(null, new FacesMessage("Resultado",  "error al guardar.") );
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		    context.addMessage(null, new FacesMessage("Resultado",  "error al guardar.") );
		}
		

	}
	
	public void eliminar(AdminCajero adminCajero){
		
		cashMachineService.eliminar(adminCajero);
		loadBilletes ();
		
	}
}
