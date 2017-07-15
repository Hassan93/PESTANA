package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Menubar;

public class MenuPrincipalController extends GenericForwardComposer{
	private Menubar mb_tarefas;
	
	public void onClick$mnit_eftReserva(Event e){
		Executions.sendRedirect("/reserva_form1.zul");
	}
	
	public void onClick$mnit_gr_quarto(Event e){
	    Executions.sendRedirect("/gerir_quarto.zul");	
	}
	
	public void onClick$mnit_Comentario(Event e ){
		Executions.sendRedirect("/submeterComentario.zul");
	}
	
	public void onClick$mnit_servico(Event e){
	   Executions.sendRedirect("/solicitar_serviços.zul");  	
	}

}
