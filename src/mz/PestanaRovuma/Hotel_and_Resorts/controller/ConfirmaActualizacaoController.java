package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ConfirmaActualizacaoController extends GenericForwardComposer {
	
	private Button btn_sim;
	private Button btn_nao;
	private Window wd_confirma;
	private Comboitem com;
	
	 public void onClick$btn_nao(ForwardEvent e) {
		  wd_confirma.onClose();	  
	}
	
	public void onClick$btn_sim(ForwardEvent e){
		
		Alocacao o =  (Alocacao) com.getValue();
	   	 new AlocacaoDao().update(o);
		// Clients.showNotification( "+o.getDesignacao()+" removido com sucesso!");
	   	 
	   	 wd_confirma.onClose();
		 
		 Executions.sendRedirect("");
	}
	

}
