package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ConfirmaCategoriaController extends GenericForwardComposer {
	private Button btn_sim;
	private Button btn_nao;
	private Window win_confirma;
	private Comboitem combo;
	
	 public void onClick$btn_nao(ForwardEvent e) {
		  win_confirma.onClose();	  
	}
	
	public void onClick$btn_sim(ForwardEvent e){
		 List<Quarto> quarto = new QuartoDao().findAll();
		 Categoria cat = (Categoria)combo.getValue();
							 
					   	 new CategoriaDao().delete(cat);
						 Clients.showNotification("Categoria removida com sucesso!");
						 List <Subpaginas>li = new SubpaginasDao().findAll();
						   	Subpaginas sub = new Subpaginas();
						    sub = li.get(0);
						    sub.setAreaGestor("cr");
						    new SubpaginasDao().update(sub);
						 win_confirma.onClose();
						 Executions.sendRedirect("/areaGestor.zul");	
	}
				
	}
