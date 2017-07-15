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




public class gerirPromocaoController extends GenericForwardComposer{
	
	private Textbox txt_pesquisar;
    private Button btn_pesquisar;
    private Button  btn_canc;
    private Button btn_add;
    private Listbox lista_servicos;
    private Window win_addService;
	private Textbox txt_descricao;
	private Combobox cbx_categoria;
	private Spinner sp_desconto;
	private Spinner sp_quant;
    private Datebox dbx_datain;
    private Datebox dbx_dataout;
    private Button btn_addPromo;
	
	PromocaoDao s = new PromocaoDao();
	CategoriaDao catDao = new CategoriaDao();
	private Promocao servt;
	private List<Promocao> listaA= new PromocaoDao().findAll();
	Button b2; 
	Button b1;
	
	
		public void onClick$btn_add(Event e){
			
			win_addService.setVisible(true);
			win_addService.doHighlighted();
			  
			}
		
		public void onClick$btn_addPromo(ForwardEvent e){
			
			Promocao se =new Promocao();
			se.setDescricao(txt_descricao.getText());
			se.setCategoria(cbx_categoria.getSelectedItem().getValue());
			se.setQuantidadeMinimaQuartos(Integer.parseInt(sp_quant.getText()));
			se.setReducaoPercentual(Integer.parseInt(sp_desconto.getText()));
			se.setData_inicial(dbx_datain.getValue());
			se.setData_final(dbx_dataout.getValue());
			se.setSituacao("publicada");
		
			    
			 s.create(se);
			    Session sess = s.getSession();
			    Transaction tx = sess.beginTransaction();
			    Promocao servico =  (Promocao)sess.createQuery("from Promocao where"
			    		+ " descricao=?").setParameter(0, se.getDescricao()).uniqueResult();
			    tx.commit();
			     
			    Listitem lstit=new Listitem();
				Listcell lstcl= new Listcell(String.valueOf(servico.getId_Promocao()));
				lstit.appendChild(lstcl);
			
				 lstcl= new Listcell(servico.getDescricao());
				 lstit.appendChild(lstcl);
				 
				 lstcl= new Listcell(servico.getCategoria().getDescricao());
				 lstcl.setValue(servico.getCategoria());
				 lstit.appendChild(lstcl);
					
				 lstcl= new Listcell(String.valueOf(servico.getCategoria().getPreco()));
				 lstit.appendChild(lstcl);
				 
				 
				lstcl= new Listcell(String.valueOf(servico.getQuantidadeMinimaQuartos()));
				lstit.appendChild(lstcl);
				
				lstcl= new Listcell(String.valueOf(servico.getReducaoPercentual()));
				lstit.appendChild(lstcl);
					
			     lstcl= new Listcell(String.valueOf(servico.getData_inicial()));
				 lstit.appendChild(lstcl);
					
				lstcl= new Listcell(String.valueOf(servico.getData_final()));
				lstit.appendChild(lstcl);
				
				lstcl = new Listcell();
				
				b1 = new Button();
				b1.setImage("/widgets/Imagens/actualizar.png");			
				b1.addEventListener("onClick", new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						 Listitem li = ((Listitem)b1.getParent().getParent());
						 li.setSelected(true);
						 Promocao o = (Promocao) li.getValue();
					      Map<String, Promocao> h = new HashMap<>();
					      h.put("1", o);
					      Executions.createComponents("adicionarPromocao.zul", null, h);	
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
					 Promocao o = (Promocao) li.getValue();
					 Map<String, Promocao> h1 = new HashMap<>();	
					 h1.put("serv",li.getValue());
					 Executions.createComponents("confirmacaoPromo.zul", null, h1);
					 
				}
			});
				lstcl.appendChild(b2);
				lstit.appendChild(lstcl);
				lstit.setValue(servico);
				
				lista_servicos.appendChild(lstit);
		    	Clients.showNotification("Promocao adicionada com sucesso!");
		    	 win_addService.setVisible(false);
		    	 
		    	    txt_descricao.setText("");
				    cbx_categoria.setValue("");
					sp_quant.setText("");
					sp_desconto.setText("");
					dbx_datain.setValue(null);
					dbx_dataout.setValue(null);
			}
		
	
		 public void onClick$btn_cancelar(){
			 win_addService.detach(); 
		 }
		 
		 
		 public void onClick$celula_a(ForwardEvent e) {
			 
			 Button b = (Button)e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);	
		      servt=(Promocao) li.getValue();
		      Map<String, Promocao> h = new HashMap<>();
		      h.put("1", servt);
		      Executions.createComponents("adicionarPromocao.zul", null, h);		  
		 }
		
		 public void onClick$celula_r(ForwardEvent e) {

			 
			 Button b = (Button) e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);
			 Map<String, Promocao> h1 = new HashMap<>();	
			 h1.put("serv",li.getValue());
			 Executions.createComponents("confirmacaoPromo.zul", null, h1);
		 }
		 
		 public List<Promocao> getPromocoes(){
			 return listaA;
		 }
		 
		 public List<Categoria> getCategorias(){
			 return catDao.findAll();
		 }
		 
		 public void limparLista() {
			 for (int i =0; i < lista_servicos.getItemCount(); i++) {
				 lista_servicos.removeItemAt(i);
			 }
		 }
		 
		
		
		 public void onClick$btn_canc(ForwardEvent e){
			 btn_canc.setVisible(false);
			    Executions.sendRedirect("");
	      }
		 
		 public void onClick$btn_pesquisar(ForwardEvent e) {
			 
			
		 }

	}


