package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.sql.BatchUpdateException;
import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ConfirmaQuartoController extends GenericForwardComposer {
	private Window win_confirm_qrt;
	private Button btn_nao;
	private Button btn_sim;
	private Comboitem combo;
	
	
	public void onClick$btn_nao(ForwardEvent e) {
		 win_confirm_qrt.onClose();	  
	}
	
	public void onClick$btn_sim(ForwardEvent e){
	   List<Alocacao>  aloca = new AlocacaoDao().findAll();
	   Quarto quarto = (Quarto)combo.getValue();
	  
	  
	    		     new QuartoDao().delete(quarto);
	    		     List <Subpaginas>li = new SubpaginasDao().findAll();
	    			   	Subpaginas sub = new Subpaginas();
	    			    sub = li.get(0);
	    			    sub.setAreaGestor("qr");
	    			    new SubpaginasDao().update(sub);
				     win_confirm_qrt.onClose();
				     Executions.sendRedirect("/areaGestor.zul"); 
	}
	

}
