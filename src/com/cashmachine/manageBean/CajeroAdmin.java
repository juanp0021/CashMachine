package com.cashmachine.manageBean;
import java.io.Serializable;
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
		
		this.admincajero = new AdminCajero();
		this.cashMachineService = new CashMachineService();
		this.obligatorio = true;
		
	}

		
	// SE DOCUMENTA EL METODO
	public void  guardarCantidad(){
		
		System.out.println(" GUARDO!!!!!");
		
		
		boolean resultado;
		try {
			resultado = cashMachineService.guardar(this.admincajero);
			
			if (resultado){
				// una vez guarda limpia  las variables
				
			     this.admincajero = new AdminCajero();
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
