package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menuitem;

public class MenuRecepcionistaController extends GenericForwardComposer {
	
	private Menuitem mnt_inicio;
	private Menuitem mnt_checkin;
	private Menuitem mnt_checkout;
	private Menuitem mnt_lista_reservas;
	private Menuitem mnt_lista_hospedagens;
	private Menuitem mnt_act_hospedagens;
	private Menuitem mnt_cobracas;
	private Include inc_corpoPrincipal;
	private Include inc_checkin;
	private Include inc_checkout;
	private Include inc_listReserva;
	private Include inc_listHospedagem;
	private Include inc_actHospedagem;
	private Include inc_cobranca;
	
	
	
	
	public void onClick$mnt_inicio(Event e){
		  inc_checkin.setVisible(false);
		  inc_checkout.setVisible(false);
		  inc_listReserva.setVisible(false);
		  inc_listHospedagem.setVisible(false);
		  inc_actHospedagem.setVisible(false);
		  inc_cobranca.setVisible(false);
		  inc_corpoPrincipal.setVisible(true);
	}
    
    public void onClick$mnt_checkin(Event e){
    	  inc_corpoPrincipal.setVisible(false);
      	  inc_checkout.setVisible(false);
      	  inc_listReserva.setVisible(false);
      	  inc_listHospedagem.setVisible(false);
      	  inc_actHospedagem.setVisible(false);
      	  inc_cobranca.setVisible(false);   	 
      	  inc_checkin.setVisible(true);
	}
    
    public void onClick$mnt_checkout(Event e){
    	  inc_corpoPrincipal.setVisible(false);
    	  inc_checkin.setVisible(false);
    	  inc_listReserva.setVisible(false);
    	  inc_listHospedagem.setVisible(false);
    	  inc_actHospedagem.setVisible(false);
    	  inc_cobranca.setVisible(false);
    	  inc_checkout.setVisible(true);   
	}
    
    public void onClick$mnt_lista_reservas(Event e){
    	  inc_corpoPrincipal.setVisible(false);
	  	  inc_checkin.setVisible(false);
	  	  inc_checkout.setVisible(false); 	  	  
	  	 inc_listHospedagem.setVisible(false);
	  	  inc_actHospedagem.setVisible(false);
	  	  inc_cobranca.setVisible(false);
	  	  inc_listReserva.setVisible(true);
	}
    
    public void onClick$mnt_lista_hospedagens(Event e) {
    	  inc_corpoPrincipal.setVisible(false);
	  	  inc_checkin.setVisible(false);
	  	  inc_checkout.setVisible(false); 	  	  
	  	  inc_listReserva.setVisible(false);
	  	  inc_actHospedagem.setVisible(false);
	  	  inc_cobranca.setVisible(false);
	  	  inc_listHospedagem.setVisible(true);
	}
    
    public void onClick$mnt_lista_quartos(Event e){
    	  inc_corpoPrincipal.setVisible(false);
	  	  inc_checkin.setVisible(false);
	  	  inc_checkout.setVisible(false); 	  	  
	  	  inc_listReserva.setVisible(false);
	  	 inc_listHospedagem.setVisible(false);
	  	  inc_actHospedagem.setVisible(false);
	  	  inc_cobranca.setVisible(false);	  	  
	}
    
    public void onClick$mnt_act_hospedagens(Event e){
    	  inc_corpoPrincipal.setVisible(false);
	  	  inc_checkin.setVisible(false);
	  	  inc_checkout.setVisible(false); 	  	  
	  	  inc_listReserva.setVisible(false);
	   	  inc_listHospedagem.setVisible(false);
	  	  inc_cobranca.setVisible(false);
	  	  inc_actHospedagem.setVisible(true);
	}
    
    public void onClick$mnt_cobracas(Event e){
    	  inc_corpoPrincipal.setVisible(false);
	  	  inc_checkin.setVisible(false);
	  	  inc_checkout.setVisible(false); 	  	  
	  	  inc_listReserva.setVisible(false);
	  	  inc_actHospedagem.setVisible(false);
	  	  inc_cobranca.setVisible(true);
   	}
}
