package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.Alocacao_has_ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.HospedeDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao_has_Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Comentario;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Hospede;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;

public class SolicitarServicosController extends GenericForwardComposer {
	  private Listbox lista_servicosSol;
	  private Textbox txt_codHospde;
	  private Button btn_login;
	  private Window win_login;
	  private Textbox txt_user, txt_password;
	  private Window win_solServico;
	  private Combobox cbx_servico;
	  private Datebox dtb_dataEntrega;
	  private Timebox dtb_horaEntrega;
	  private Button btn_solicitar;
	  private Alocacao alocation;
	  Hospede hospede;
	  Quarto quarto;
	  Button b2; 
	  Button b1;
	  private Alocacao_has_ServicoDao alsedao = new Alocacao_has_ServicoDao();
	  AlocacaoDao alocacaoDao = new AlocacaoDao();
	  
	  
	  public void onClick$btn_ok(ForwardEvent e){
			 int codigo = 0;
				if (txt_codHospde.getText() != "" )  {
					 codigo = Integer.parseInt(txt_codHospde.getText());				  
				      Session sess = alocacaoDao.getSession();
				      Transaction tx = sess.beginTransaction();
				      Alocacao alocacao =  (Alocacao)sess.createQuery("from Alocacao where"
				    		+ " id_Alocacao=?").setParameter(0, codigo).uniqueResult();
				    tx.commit();
				   
				    if (alocacao != null ) {
				    	if (!alocacao.isAlocado()) {				    		
				    	 alocation = alocacao;	
				    	  Clients.showNotification("WWWWW: "+alocation.getId_Alocacao());
				    	}else{
				    		  Clients.showNotification("Verificado a existencia da sua hospedagem");
					    		
					    							    		
				    	}
				   }else
				    {
				    	Clients.showNotification("O codigo introduzido nao pertence a nenhuma hospedagem");
				    }

				}
				else
					Clients.showNotification("Introduza o código da hospedagem!");	
		 }
		 
	  
	  
	  public void onClick$btn_solicitar(ForwardEvent e){
		  Alocacao_has_Servico alse = new Alocacao_has_Servico();
		     alse.setAlocacao(alocation);
		     alse.setServico((Servico)cbx_servico.getSelectedItem().getValue());
		     alse.setData_de_uso(dtb_dataEntrega.getValue());
		     alsedao.create(alse);
		     Clients.showNotification("Servico solicitado com sucesso");
		     clear();
		  
	  }
	  
	  private void addServicoSolicitadoList(Alocacao_has_Servico alse){
			Listitem lstit = new Listitem();
			Listcell lstcl = new Listcell(alse.getAlocacao().getHospede().getOcupante().getNome());
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alse.getServico().getDesignacao());
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alse.getAlocacao().getHospede().getOcupante().getQuarto().getDesignacao());
			lstit.appendChild(lstcl);		
						
			lstcl = new Listcell(String.valueOf(alse.getData_de_solicitacao()));
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(String.valueOf(alse.getData_de_uso()));
			lstit.appendChild(lstcl);			
			
			b1 = new Button();
			b1.setImage("/widgets/Imagens/actualizar.png");			
			b1.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					 Listitem li = ((Listitem)b1.getParent().getParent());
					 li.setSelected(true);
					 Alocacao_has_Servico alse= (Alocacao_has_Servico) li.getValue();
				      Map<String, Alocacao_has_Servico> h = new HashMap<>();
				      h.put("1", alse);
				      Executions.createComponents("Pagamento", null, h);
				      // a cobrança dos servicos solicitados é efectuado na checkout
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
			     Alocacao_has_Servico allse = (Alocacao_has_Servico) li.getValue();
				 Map<String, Alocacao_has_Servico> h1 = new HashMap<>();	
				 h1.put("serv",li.getValue());
				 Executions.createComponents("/removerServicoSolicitado.zul", null, h1);
				 
			}
		});
			lstcl.appendChild(b2);
			lstit.appendChild(lstcl);
			
			lstit.setValue(alse);
			lista_servicosSol.appendChild(lstit);

			lstit.addForward("onClick","", "onClick$linha",alse);
			
		}
	  
	  public void onClick$celula_a(ForwardEvent e) {
	 		 
	 		 Button b = (Button)e.getOrigin().getTarget();
	 		 Listitem li = ((Listitem)b.getParent().getParent());
	 		 li.setSelected(true);	
	 		 Alocacao_has_Servico  servt=(Alocacao_has_Servico ) li.getValue();
	 	      Map<String, Alocacao_has_Servico > h = new HashMap<>();
	 	      h.put("1", servt);
	 	      Executions.createComponents("Pagamento", null, h);		
	 	      
	 	 }
	 	
	 	 public void onClick$celula_r(ForwardEvent e) {

	 		 Button b = (Button) e.getOrigin().getTarget();
	 		 Listitem li = ((Listitem)b.getParent().getParent());
	 		 li.setSelected(true);
	 		 Map<String, Alocacao_has_Servico > h1 = new HashMap<>();	
	 		 h1.put("serv",li.getValue());
	 		 Executions.createComponents("/removerServicoSolicitado.zul", null, h1);
	 	  }
	  
	  public void clear(){
		  txt_codHospde.setText(" ");
		  cbx_servico.setSelectedItem(null);
		  dtb_dataEntrega.setValue(null);
	  }
	  
	  public List<Servico> getServicos(){
		 return new ServicoDao().findAll();
		  
	  }
	  public List<Alocacao> getAlocacoes(){
		  return new AlocacaoDao().findAll();
	  }
	  
	  public List<Hospede> getHospede(){
		  return new HospedeDao().findAll();
	  }
	  
	  public List<Quarto> getQuarto(){
		  return new QuartoDao().findAll();
	  }
	  
	  public List<Alocacao_has_Servico> getAlocacao_has_servicos(){
		  return new Alocacao_has_ServicoDao().findAll();
	  }

}
