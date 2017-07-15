package mz.PestanaRovuma.Hotel_and_Resorts.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import mz.PestanaRovuma.Hotel_and_Resorts.model.Promocao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.PromocaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;




public class PublicarPromocaoController extends GenericForwardComposer{
	
    private Button  btn_canc;
    private Listbox lista_servicos;
	private Textbox txt_descricao;
	private Combobox cbx_categoria;
	private Spinner sp_desconto;
	private Spinner sp_quant;
    private Datebox dbx_datain;
    private Datebox dbx_dataout;
	
	PromocaoDao s = new PromocaoDao();
	CategoriaDao catDao = new CategoriaDao();
	private Promocao servt;
	Button b2; 
	Button b1;
	
		 
		 public void onClick$celula_a(ForwardEvent e) {
			 
			 Button b = (Button)e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);	
		      servt=(Promocao) li.getValue();
		      servt.setSituacao("publicada");
		       new PromocaoDao().update(servt);		  
		 }
		 
        public void onClick$celula_r(ForwardEvent e) {
			 
			 Button b = (Button)e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);	
		      servt=(Promocao) li.getValue();
		      servt.setSituacao("nao_publicada");
		       new PromocaoDao().update(servt);		  
		 }
		
		 public List<Promocao> getPromocoes(){
			 return new PromocaoDao().findAll();
		 }	
		
		 public void onClick$btn_canc(ForwardEvent e){
			 btn_canc.setVisible(false);
			    Executions.sendRedirect("");
	     }
		 

	}


