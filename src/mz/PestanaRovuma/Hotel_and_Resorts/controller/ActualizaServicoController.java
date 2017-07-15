package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ActualizaServicoController extends GenericForwardComposer {

	private Textbox txt_designacao;
	private Textbox txt_descricao;
	private Textbox txt_preco;
	private Button btn_actual;
	private Button btn_cancelar;
	private Window win_addServ;
	private Comboitem combo;
	
	 public void onClick$btn_cancelar(){
		 // win_addServ.setVisible(false);
			 win_addServ.detach();
	}
	 
	 
	  public void onClick$btn_actual(Event e) {
		  
		  Servico servico = (Servico) combo.getValue();
		    servico.setDesignacao(txt_designacao.getText());
			servico.setDescricao(txt_descricao.getText());
			servico.setPreco(Integer.parseInt(txt_preco.getText()));
			 new ServicoDao().update(servico);
			   
			    
			    List <Subpaginas>li = new SubpaginasDao().findAll();
			    Subpaginas sub = new Subpaginas();
			    sub = li.get(0);
			    sub.setAreaGestor("s");
			    new SubpaginasDao().update(sub);
			    
			    Executions.sendRedirect("/areaGestor.zul");
			    
			   win_addServ.detach();
		   
		    
	 }
	 
	
	
	
}
