package com.cashmachine.manageBean;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.bytebuddy.asm.Advice.This;

import com.cashmachine.entity.AdminCajero;
import com.cashmachine.services.CashMachineService;

@ManagedBean(name="cajeroAdmin")

@SessionScoped
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
		
	// SE DOCUMENTA EL METODO
	public void  guardarCantidad(){
		
		System.out.println(" GUARDO!!!!!"+this.admincajero.getCantidad());
		
		
		AdminCajero resultadoAdminCajero;
		try {
			
			resultadoAdminCajero = cashMachineService.guardar(this.admincajero);
			
			if (resultadoAdminCajero != null){
				// una vez guarda limpia  las variables
			     this.admincajero = new AdminCajero();
				 FacesContext context = FacesContext.getCurrentInstance();
		         context.addMessage(null, new FacesMessage("Resultado",  "se guardo el registro exitosamente.") );
		         this.billetes.add(resultadoAdminCajero);
		         
			}else{
			     FacesContext context = FacesContext.getCurrentInstance();
		         context.addMessage(null, new FacesMessage("Resultado",  "error al guardar.") );
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    FacesContext context = FacesContext.getCurrentInstance();
		    context.addMessage(null, new FacesMessage("Resultado",  "error al guardar.") );
		}
		

	}
	
	public void eliminar(AdminCajero adminCajero){
		
		cashMachineService.eliminar(adminCajero);
		loadBilletes ();
		
	}
}
