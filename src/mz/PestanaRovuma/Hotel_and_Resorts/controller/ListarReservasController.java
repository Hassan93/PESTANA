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

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ReservaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Cliente;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Hospede;



public class ListarReservasController extends GenericForwardComposer{
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
	
	ReservaDao s = new ReservaDao();
	
	private Reserva servt;
	private Button btn_carregar;
	private List<Reserva> listaA= new ReservaDao().findAll();
	Button b2; 
	Button b1;
	
	boolean confirma=false;
	Reserva esteServ = new Reserva();
	private Comboitem com;
	
		
		
		 public void onClick$celula_a(ForwardEvent e) {
			 
			 Button b = (Button)e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);	
		      servt=(Reserva) li.getValue();
		      Map<String, String> h = new HashMap<>();
		      h.put("1", servt.getId_Reserva()+"");
		      Executions.createComponents("checkin.zul", null, h);	
		      bl_servico.detach();
		      bl_servico.setVisible(false);
		 }
		
		 
		 public List<Reserva> getReservas(){
			 return listaA;
		 }
		 
		 public void onClick$btn_pesquisar(ForwardEvent e){	
		
			    String arg0 = txt_pesquisar.getText();
			    
			    if (arg0 !="") {
			    	
			    Session sess = s.getSession();
			    Transaction tx = sess.beginTransaction();
			    Cliente hospede = (Cliente)sess.createQuery("from Cliente where "
			    		+ "nome=?").setParameter(0, arg0).uniqueResult();
			    
			    Reserva servico =  (Reserva)sess.createQuery("from Reserva where "
			    		+ "id_cliente =? ").setParameter(0, hospede.getId_Cliente()).uniqueResult();
			    tx.commit();
			    if (servico != null) {
			    	
			    	 btn_canc.setVisible(true);
			    for (int i =0; i < lista_servicos.getItemCount(); i++) {
					 lista_servicos.removeItemAt(i);
				 }
			    
			    Listitem lstit = new Listitem();
				Listcell lstcl= new Listcell(String.valueOf(servico.getId_Reserva()));
				lstit.appendChild(lstcl);
				
				
				lstcl= new Listcell(String.valueOf(servico.getCliente().getId_Cliente()));
				 lstit.appendChild(lstcl);
				
				 lstcl= new Listcell(servico.getCliente().getNome());
				 lstit.appendChild(lstcl);
				
				lstcl= new Listcell(servico.getCliente().getApelido());
			    lstit.appendChild(lstcl);			
					
				lstit.setValue(servico);
				   
				lista_servicos.appendChild(lstit);
			        
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
		 
		 public boolean existe(Reserva s){
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


