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

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;



public class gerirServicoController extends GenericForwardComposer{
	private Textbox txt_designacao, txt_pesquisar;
	private Textbox txt_descricao;
	private Button btn_car;
	private Button  btn_canc;
	private Textbox txt_preco;
	private Button btn_addService;
	private Button btn_actualizar, btn_pesquisar;
	private Button btn_remover;
	private Button btn_add;
	private Button btn_cancelar;
	private Listbox lista_servicos;
	ServicoDao s = new ServicoDao();
	private Window win_addService;
	private Servico servt;
	private Button btn_carregar;
	private List<Servico> listaA= new ServicoDao().findAll();
	Button b2; 
	Button b1;
	
	boolean confirma=false;
	Servico esteServ = new Servico();
	private Comboitem com;
	private Div bl_servico;
	
	
	
		public void onClick$btn_add(Event e){
			
			win_addService.setVisible(true);
			win_addService.doHighlighted();
			 
			}
		
		public void onClick$btn_addService(Event e){
		    
			
			
			Servico se =new Servico();
			se.setDesignacao(txt_designacao.getText());
			se.setDescricao(txt_descricao.getText());
			se.setPreco(Integer.parseInt(txt_preco.getText()));
			    
			s.create(se);
			    Session sess = s.getSession();
			    Transaction tx = sess.beginTransaction();
			    Servico servico =  (Servico)sess.createQuery("from Servico where"
			    		+ " designacao=?").setParameter(0, se.getDesignacao()).uniqueResult();
			    tx.commit();
			     
			    Listitem lstit=new Listitem();
				Listcell lstcl= new Listcell(String.valueOf(servico.getCodigo()));
				lstit.appendChild(lstcl);
			
				 lstcl= new Listcell(servico.getDesignacao());
				 lstit.appendChild(lstcl);
					
				lstcl= new Listcell(servico.getDescricao());
				lstit.appendChild(lstcl);
				
				lstcl= new Listcell(String.valueOf(servico.getPreco()));
				lstit.appendChild(lstcl);
					
				lstcl = new Listcell();
				b1 = new Button();
				b1.setImage("/widgets/Imagens/actualizar.png");			
				b1.addEventListener("onClick", new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						 Listitem li = ((Listitem)b1.getParent().getParent());
						 li.setSelected(true);
						 Servico o = (Servico) li.getValue();
					     
					      Map<String, Servico> h = new HashMap<>();
					      h.put("1", o);
					      Executions.createComponents("adicionarServico.zul", null, h);	
					}
				});
				lstcl.appendChild(b1);
				lstit.appendChild(lstcl);
				
				lstcl = new Listcell();
				b2 = new Button();
				b2.setImage("/widgets/Imagens/remove.png");	
				
			  b2.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					 
					 Listitem li = ((Listitem)b2.getParent().getParent());
					 li.setSelected(true);
					 Servico o = (Servico) li.getValue();
					 Map<String, Servico> h1 = new HashMap<>();	
					 h1.put("serv",li.getValue());
					 Executions.createComponents("confirmacao.zul", null, h1);
				}
			});
				lstcl.appendChild(b2);
				lstit.appendChild(lstcl);
				lstit.setValue(servico);
				
				lista_servicos.appendChild(lstit);
		
		    	
		    	Clients.showNotification("Servico adicionado com sucesso!");
		    	 win_addService.setVisible(false);
		    	 txt_designacao.setText("");
					txt_descricao.setText("");
					txt_preco.setText("");
			}
		
	
		
		 public void onClick$btn_cancelar(){
			
			 win_addService.detach();
		     
		 }
		 
		 public void onClick$celula_a(ForwardEvent e) {
			 
			 Button b = (Button)e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);	
		      servt=(Servico) li.getValue();
		      Map<String, Servico> h = new HashMap<>();
		      h.put("1", servt);
		      Executions.createComponents("adicionarServico.zul", null, h);		
		      
		 }
		
		 public void onClick$celula_r(ForwardEvent e) {

			 
			 Button b = (Button) e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);
			 Map<String, Servico> h1 = new HashMap<>();	
			 h1.put("serv",li.getValue());
			 Executions.createComponents("confirmacao.zul", null, h1);
		 }
		 
		 public List<Servico> getServicos(){
			 return listaA;
		 }
		 
		 public void onClick$btn_pesquisar(ForwardEvent e){	
			// Clients.showNotification(" removido com sucesso!");
			    String arg0 = txt_pesquisar.getText();
			    
			    if (arg0 !="") {
			   
			
			    
			    Session sess = s.getSession();
			    Transaction tx = sess.beginTransaction();
			    Servico servico =  (Servico)sess.createQuery("from Servico where"
			    		+ " designacao=?").setParameter(0, arg0).uniqueResult();
			    tx.commit();
			    if (servico != null) {
			    	Clients.showNotification("Entrou");
			    	 btn_canc.setVisible(true);
			    	 try{
			 			while (true){
			 				if (lista_servicos.getItemAtIndex(0)==null )
			 					break;
			 				else {
			 						lista_servicos.removeItemAt(0);
			 					}
			 				}
			 			}catch (IndexOutOfBoundsException er) {

			 			}
			        
			    Listitem lstit=new Listitem();
				Listcell lstcl= new Listcell(String.valueOf(servico.getCodigo()));
				lstit.appendChild(lstcl);
			
				 lstcl= new Listcell(servico.getDesignacao());
				 lstit.appendChild(lstcl);
					
				lstcl= new Listcell(servico.getDescricao());
				lstit.appendChild(lstcl);
				
				lstcl= new Listcell(String.valueOf(servico.getPreco()));
				lstit.appendChild(lstcl);
				
				
				
				lstcl = new Listcell();
				b1 = new Button();
				b1.setImage("/widgets/Imagens/actualizar.png");			
				b1.addEventListener("onClick", new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						 Listitem li = ((Listitem)b1.getParent().getParent());
						 li.setSelected(true);
						 Servico o = (Servico) li.getValue();
					     
					      Map<String, Servico> h = new HashMap<>();
					      h.put("1", o);
					      Executions.createComponents("adicionarServico.zul", null, h);	
					}
				});
				lstcl.appendChild(b1);
				lstit.appendChild(lstcl);
				
				lstcl = new Listcell();
				b2 = new Button();
				b2.setImage("/widgets/Imagens/remove.png");	
				
			  b2.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					 Listitem li = (Listitem)b2.getParent().getParent();
					 li.setSelected(true);
					 Map<String, Servico> h1 = new HashMap<>();	
					 h1.put("serv",li.getValue());
					 Executions.createComponents("confirmacao.zul", null, h1);
				}
			});
				lstcl.appendChild(b2);
				lstit.appendChild(lstcl);
					
					lstit.setValue(servico);
				   
					lista_servicos.appendChild(lstit);
			    	
			        
			    }
			    else{
			    	Clients.showNotification(arg0+" nao encontrado por favor certifique se esta bem escrito!","error",null,"top_begin",3000);
			    }
			    }
			  
			    	
		 }
		 
		 
		 
		 public boolean existe(Servico s) {
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


