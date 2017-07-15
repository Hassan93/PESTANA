package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.Alocacao_has_ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ComentarioDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao_has_Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Comentario;

public class RemoverServicoSolicitadoController extends GenericForwardComposer{
	private Window wd_removerServico;
    private Comboitem cbit_servico;
    private Button btn_sim;
    private Button btn_nao;	
    
    public void onClick$btn_nao(ForwardEvent e){
    	wd_removerServico.onClose();	  
	  }
	
	  public void onClick$btn_sim(ForwardEvent e){		
		 
         Alocacao_has_Servico alse = (Alocacao_has_Servico)cbit_servico.getValue();		  
         new Alocacao_has_ServicoDao().delete(alse);
		 Clients.showNotification("Comentario removido com sucesso!");
		 wd_removerServico.onClose();
		 Executions.sendRedirect("servicosSolicitados.zul");
	}

}
