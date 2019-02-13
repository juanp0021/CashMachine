package com.cashmachine.manageBean;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.bytebuddy.asm.Advice.This;

import com.cashmachine.entity.Billete;
import com.cashmachine.services.CashMachineService;

@ManagedBean(name="cajeroAdmin")

@SessionScoped
public class CajeroAdmin implements Serializable {
	
	
	//  SE DOCUMENTA EL ATRIBUTO
	private int cantidadBillete;
	private Billete billete;
	private boolean obligatorio;
	private CashMachineService cashMachineService;
	
	
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
	public void  init (){
		
		this.billete = new Billete();
		this.cashMachineService = new CashMachineService();
		this.obligatorio = true;
		
	}

	public Billete getBillete() {
		return billete;
	}

	public void setBillete(Billete billete) {
		this.billete = billete;
	}

	public int getCantidadBillete() {
		return cantidadBillete;
	}

	public void setCantidadBillete(int cantidadBillete) {
		this.cantidadBillete = cantidadBillete;
	}
	
	// SE DOCUMENTA EL METODO
	public void  guardarCantidad(){
		
		System.out.println(" GUARDO!!!!!");
		System.out.println(this.billete.getDenominacionBillete() );
		System.out.println(this.cantidadBillete );
		
		
		boolean resultado;
		try {
			resultado = cashMachineService.guardar(this.billete, this.cantidadBillete);
			
			if (resultado){
				// una vez guarda limpia  las variables
				this.billete = new Billete();
				this.cantidadBillete = 0;
				 FacesContext context = FacesContext.getCurrentInstance();
		         context.addMessage(null, new FacesMessage("Resultado",  "se guardo el registro exitosamente.") );
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
	

}
