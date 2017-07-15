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
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Hospede;



public class ListarHospedagemController extends GenericForwardComposer{
	private Textbox txt_designacao, txt_pesquisar;
	private Textbox txt_descricao;
	private Button btn_car;
	private Button  btn_canc;
	private Textbox txt_preco;
	private Button btn_addService;
	private Button btn_actualizar;
	private Button btn_pesquisar;
	private Button btn_remover;
	private Listbox lista_servicos;
	private Div bl_servico;
	
	AlocacaoDao s = new AlocacaoDao();
	
	private Alocacao servt;
	private Button btn_carregar;
	private List<Alocacao> listaA= new AlocacaoDao().findAll();
	Button b2; 
	Button b1;
	
	boolean confirma=false;
	Alocacao esteServ = new Alocacao();
	private Comboitem com;
	
		
		
		 public void onClick$celula_a(ForwardEvent e) {
			 
			 Button b = (Button)e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);	
		      servt=(Alocacao) li.getValue();
		      Map<String, String> h = new HashMap<>();
		      h.put("1", servt.getId_Alocacao()+"");
		      Executions.createComponents("actualizarHospedagem.zul", null, h);	
		      bl_servico.detach();
		      bl_servico.setVisible(false);
		 }
		
		 public void onClick$celula_r(ForwardEvent e) {

			 
			 Button b = (Button) e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);
			 Map<String, String> h1 = new HashMap<>();	
			 h1.put("1",((Alocacao)li.getValue()).getId_Alocacao()+"");
			 Executions.createComponents("checkout.zul", null, h1);
			 bl_servico.detach();
		      bl_servico.setVisible(false);
		 }
		 
		 public List<Alocacao> getAlocacoes(){
			 return listaA;
		 }
		 
		 public void onClick$btn_pesquisar(ForwardEvent e){	
		
			    String arg0 = txt_pesquisar.getText();
			    
			    if (arg0 !="") {
			    	
			    Session sess = s.getSession();
			    Transaction tx = sess.beginTransaction();
			    Hospede hospede = (Hospede)sess.createQuery("from Hospede where "
			    		+ "nome=?").setParameter(0, arg0).uniqueResult();
			    
			    Alocacao servico =  (Alocacao)sess.createQuery("from Alocacao where "
			    		+ "id_hospede =? ").setParameter(0, hospede.getId_hospede()).uniqueResult();
			    tx.commit();
			    if (servico != null) {
			    	
			    	 btn_canc.setVisible(true);
			    for (int i =0; i < lista_servicos.getItemCount(); i++) {
					 lista_servicos.removeItemAt(i);
				 }
			        
			    Listitem lstit=new Listitem();
				Listcell lstcl= new Listcell(String.valueOf(servico.getId_Alocacao()));
				lstit.appendChild(lstcl);
			
				 lstcl= new Listcell(String.valueOf(servico.getHospede().getId_hospede()));
				 lstit.appendChild(lstcl);
				 
				 lstcl= new Listcell(servico.getHospede().getOcupante().getNome());
				 lstit.appendChild(lstcl);
				 
				 lstcl= new Listcell(servico.getHospede().getOcupante().getQuarto().getCategoria().getDesignacao());
				 lstit.appendChild(lstcl);
					
				 lstcl= new Listcell(servico.getHospede().getOcupante().getQuarto().getDesignacao());
				 lstit.appendChild(lstcl);
				 
				lstcl= new Listcell(String.valueOf(servico.getHospede().getOcupante().getData_checkin()));
				lstit.appendChild(lstcl);
				
				lstcl= new Listcell(String.valueOf(servico.getHospede().getOcupante().getDataCheckOut()));
				lstit.appendChild(lstcl);
				
				
				lstcl = new Listcell();
				b1 = new Button();
				b1.setLabel("Actualizar");		
				b1.addEventListener("onClick", new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						 Listitem li = ((Listitem)b1.getParent().getParent());
						 li.setSelected(true);
						 Alocacao o = (Alocacao) li.getValue();
					     
					      Map<String, String> h = new HashMap<>();
					      h.put("1", o.getId_Alocacao()+"");
					      Executions.createComponents("actualizarHospedagem.zul", null, h);	
					}
				});
				lstcl.appendChild(b1);
				lstit.appendChild(lstcl);
				
				lstcl = new Listcell();
				b2 = new Button();
				b2.setLabel("Check out");
				
			  b2.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					 Listitem li = (Listitem)b2.getParent().getParent();
					 li.setSelected(true);
					 Map<String, String> h1 = new HashMap<>();	
					 h1.put("serv",((Alocacao)li.getValue()).getId_Alocacao()+"");
					 Executions.createComponents("checkout.zul", null, h1);
				}
			});
				lstcl.appendChild(b2);
				lstit.appendChild(lstcl);
					
					lstit.setValue(servico);
				   
					lista_servicos.appendChild(lstit);
			    	//lista_servicos.removeItemAt(0);
			        
			    }
			    else{
			    	Clients.showNotification(arg0+" não encontrado por favor certifique se está bem escrito!","error",null,"top_begin",3000);
			    }
			    }
			  
			    	
		 }
		 
		 public void limparLista() {
			 for (int i =0; i < lista_servicos.getItemCount(); i++) {
				 lista_servicos.removeItemAt(i);
			 }
		 }
		 
		 public boolean existe(Alocacao s){
			 boolean a = false;
			 
			 for (int i = 0; i < lista_servicos.getItemCount(); i++) {
			     if (lista_servicos.getChildren().get(i).equals(s)) {
			    	 a=true; break;
			     }
			 }
			 return a;
		 }
		
		 public void onClick$btn_canc(ForwardEvent e){
			 btn_canc.setVisible(false);
			    Executions.sendRedirect("");
	      }

	}


