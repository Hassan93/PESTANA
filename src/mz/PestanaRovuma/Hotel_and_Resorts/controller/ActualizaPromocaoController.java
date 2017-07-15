package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.io.IOException;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.PromocaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Promocao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ActualizaPromocaoController extends GenericForwardComposer {

	private Textbox txt_descricao;
	private Combobox cbx_categoria;
	private Spinner sp_desconto;
	private Spinner sp_quant;
    private Datebox dbx_datain;
    private Datebox dbx_dataout;
    private CategoriaDao catDao = new CategoriaDao();
	private Window win_addServi;
	private Comboitem combo;
	
	 public void onClick$btn_cancelar(){
			 win_addServi.detach();
	}
	 
	  public void onClick$btn_addPromo(Event e) {
		  
		  Promocao servico = (Promocao) combo.getValue();
		
			servico.setDescricao(txt_descricao.getText());
			servico.setCategoria(cbx_categoria.getSelectedItem().getValue());
			servico.setQuantidadeMinimaQuartos(Integer.parseInt(sp_quant.getText()));
			servico.setReducaoPercentual(Integer.parseInt(sp_desconto.getText()));
			servico.setData_inicial(dbx_datain.getValue());
			servico.setData_final(dbx_dataout.getValue());
		    
		    
			  List <Subpaginas>li = new SubpaginasDao().findAll();
			    Subpaginas sub = new Subpaginas();
			    sub = li.get(0);
			    sub.setAreaGestor("p");
			    new SubpaginasDao().update(sub);
			    
			    Executions.sendRedirect("/areaGestor.zul");
			
				
		    new PromocaoDao().update(servico);
		    Clients.showNotification("Servi�o Actualizado com sucesso!");
		    win_addServi.detach();
	 }
	  
	  public List<Categoria> getCategorias(){
			 return catDao.findAll();
		 }
	
	
}
