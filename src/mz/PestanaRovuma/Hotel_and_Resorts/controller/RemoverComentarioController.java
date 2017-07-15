package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ComentarioDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Comentario;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;

public class RemoverComentarioController extends GenericForwardComposer {
	  private Window wd_removerComentario;
      private Comboitem cbit_comentario;
      private Button btn_sim;
      private Button btn_nao;
      
      public void onClick$btn_nao(ForwardEvent e) {
    	  wd_removerComentario.onClose();	  
	  }
	
	  public void onClick$btn_sim(ForwardEvent e){
		
		   Comentario coment =  (Comentario)cbit_comentario.getValue();
	   	   new ComentarioDao().delete(coment);
		   Clients.showNotification("Comentario removido com sucesso!");
		   wd_removerComentario.onClose();
		   Executions.sendRedirect("comentarioSubmetido.zul");
	}
	

}
