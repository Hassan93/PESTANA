package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ConfirmaController extends GenericForwardComposer {
	
	private Button btn_sim;
	private Button btn_nao;
	private Window wd_confirma;
	private Comboitem com;
	
	 public void onClick$btn_nao(ForwardEvent e) {
		  wd_confirma.onClose();	  
	}
	
	public void onClick$btn_sim(ForwardEvent e){
		
		Servico o =  (Servico) com.getValue();
	   	 new ServicoDao().delete(o);
		 Clients.showNotification("Servico "+o.getDesignacao()+" removido com sucesso!");
		 List <Subpaginas>li = new SubpaginasDao().findAll();
		   	Subpaginas sub = new Subpaginas();
		    sub = li.get(0);
		    sub.setAreaGestor("sr");
		    new SubpaginasDao().update(sub);
		 wd_confirma.onClose();
		 Executions.sendRedirect("/areaGestor.zul");
	}
	

}
