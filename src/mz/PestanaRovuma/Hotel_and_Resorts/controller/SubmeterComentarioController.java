package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ComentarioDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.HospedeDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Comentario;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Hospede;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;

   public class SubmeterComentarioController extends GenericForwardComposer{
	     private Textbox txt_codHospde;
	     private Radiogroup rdo_area;
	     private Listbox list_alocacao;
		 private Radiogroup rdo_avaliacao;
		 private Textbox txt_comentario;
		 private Button btn_submeter;
		 private Radio rdo_servico;
		 private Radio rdo_hospedagem;		
		 private Radio rdo_hosp_bom;
		 private Radio rdo_hosp_mau;
		 private Radio rdo_hosp_excelente;
		 private Listbox lista_sugestacao;
		 private Comentario com;
		 Alocacao alocation;
		 Hospede hospede;
		 Button b2; 
	     Button b1;
		 private ComentarioDao comment = new ComentarioDao();
		 private HospedeDao hosp = new HospedeDao();
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
				    	 //Clients.showNotification("WWWWW: "+alocation.getId_Alocacao());
				    	}else{
				    		  Clients.showNotification("Verificado a existencia da sua hospedagem");
				    		    rdo_servico.setDisabled(false);
					    		rdo_hospedagem.setDisabled(false);
					    		rdo_hosp_bom.setDisabled(false);
					    		rdo_hosp_mau.setDisabled(false);
					    		rdo_hosp_excelente.setDisabled(false);
					    		txt_comentario.setDisabled(false);
					    		btn_submeter.setDisabled(false);
					    		hospede = alocacao.getHospede();				    							    		
				    	}
				   }else
				    {
				    	Clients.showNotification("O codigo introduzido nao pertence a nenhuma hospedagem");
				    }

				}
				else
					Clients.showNotification("Introduza o código da hospedagem!");	
		 }
			 
		 public void onClick$btn_submeter(Event e){
			     Comentario coment = new Comentario();
			     coment.setArea_sugestao(rdo_area.getSelectedItem().getValue());
			     coment.setClassificacao(rdo_avaliacao.getSelectedItem().getValue());
			     coment.setComentario(txt_comentario.getText());
			     coment.setHospede(hospede);
			     comment.create(coment);
			     clear();
			 Clients.showNotification("Sugestão submetida...");
		 }
		 
		 private void addComenatarioList(Comentario coment){
				Listitem lstit = new Listitem();
				Listcell lstcl = new Listcell(String.valueOf(coment.getId_Comentario()));
				lstit.appendChild(lstcl);
				
				lstcl = new Listcell(coment.getHospede().getOcupante().getNome());
				lstit.appendChild(lstcl);

				lstcl = new Listcell(coment.getArea_sugestao());
				lstit.appendChild(lstcl);

				lstcl = new Listcell(coment.getClassificacao());
				lstit.appendChild(lstcl);
				
				lstcl = new Listcell(String.valueOf(coment.getData_da_submissao()));
				lstit.appendChild(lstcl);

				lstcl = new Listcell(coment.getComentario());
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
				     Comentario o = (Comentario) li.getValue();
					 Map<String, Comentario> h1 = new HashMap<>();	
					 h1.put("serv",li.getValue());
					 Executions.createComponents("removerComentario.zul", null, h1);
					 
				}
			});
				lstcl.appendChild(b2);
				lstit.appendChild(lstcl);
				
				lstit.setValue(coment);
				lista_sugestacao.appendChild(lstit);

				lstit.addForward("onClick","", "onClick$linha",coment);
				
			}
		 
         
        public void onClick$celula_r(ForwardEvent e) {
			 
			 Button b = (Button) e.getOrigin().getTarget();
			 Listitem li = ((Listitem)b.getParent().getParent());
			 li.setSelected(true);
			 Map<String, Comentario > h1 = new HashMap<>();	
			 h1.put("serv",li.getValue());
			 Executions.createComponents("removerComentario.zul", null, h1);
		 }
		 
		 public void clear(){
			 txt_codHospde.setText(" ");
			 rdo_area.setSelectedItem(null);
			 rdo_avaliacao.setSelectedItem(null);
			 txt_comentario.setText(" ");
		 }
		 
		 public List<Hospede> getHospedes(){
			  return new HospedeDao().findAll();
		 }
		 
		 public List<Comentario> getComentarios(){
			  return new ComentarioDao().findAll();
		 }
		 
		 public List<Quarto> getQuartos(){
			 return new QuartoDao().findAll();
		 }

   }
