package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.Alocacao_has_ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao_has_Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Hospede;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;


	public class CheckoutController extends GenericForwardComposer {
		
		private Textbox txt_codAlocacao;
		private Button btn_ok;
		private Button btn_checkout;
		private Button btn_cancelar;
		private Listbox list_alocacao;
		private Button btn_sim;
		private Button btn_nao;
		
		private Label lb_valor;
		private Label lab_valPagar;
		private Label lab_mzn;
		private Checkbox cbx_pagar;
		private Label lab_titleService;
		private Grid gd_serv;
		
		private Listitem lstit_vazio;
		private Window wd_confirma;
		private Borderlayout bl_actualizar;
		private Listbox list_servicos;
		int codigo = 0;
		AlocacaoDao alocacaoDao = new AlocacaoDao();
	    Alocacao alocacacao;
		float valor;
		List <Alocacao_has_Servico>yt = new ArrayList();

		
		private void addHospedagemList(Alocacao alocacao) {
			
			Listitem lstit = new Listitem();
			
			Listcell lstcl = new Listcell(String.valueOf(alocacao.getId_Alocacao()));
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alocacao.getHospede().getOcupante().getNome());
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alocacao.getHospede().getOcupante().getQuarto().getCategoria().getDesignacao());
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alocacao.getHospede().getOcupante().getQuarto().getDesignacao());
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alocacao.getHospede().getOcupante().getData_checkin().toString());
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(alocacao.getHospede().getOcupante().getDataCheckOut().toString());
			lstit.appendChild(lstcl);
			lstit_vazio = lstit;
		    lstit_vazio.setValue(alocacao);
		    
			list_alocacao.removeItemAt(0);	
			list_alocacao.appendChild(lstit_vazio);
	    
		}
		
		
		
		public void limpar(){
			
			txt_codAlocacao.setText("");
		
		
	        Listitem lstit = new Listitem();
			
			Listcell lstcl = new Listcell("----");
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell("----");
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell("----");
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell("----");
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell("----");
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell("----");
			lstit.appendChild(lstcl);
			lstit_vazio = lstit;
		    lstit_vazio.setValue("----");
		    
			list_alocacao.removeItemAt(0);	
			list_alocacao.appendChild(lstit_vazio);
			
			
		}
		
		
		
		public void onClick$btn_ok(Event e) {

			
			if (list_alocacao.getItemCount() == 1 ) 
			if (txt_codAlocacao.getText() != "" )  {
				 codigo = Integer.parseInt(txt_codAlocacao.getText());
			  
			    Session sess = alocacaoDao.getSession();
			    Transaction tx = sess.beginTransaction();
			    Alocacao alocacao =  (Alocacao)sess.createQuery("from Alocacao where"
			    		+ " id_Alocacao=?").setParameter(0, codigo).uniqueResult();
			    tx.commit();
			   
			    if (alocacao != null) {
			    	if (!alocacao.isAlocado()) {
			    	alocacacao = alocacao;
			    
					
			        valor =  addServicosList();
					addHospedagemList(alocacao);
					lb_valor.setValue(valor+"");
					if (!lb_valor.isVisible())
						btn_checkout.setVisible(true);
			    	}else
			    		Clients.showNotification("A hospedagem j� passou de checkout!");
			   }else
			    {
			    	Clients.showNotification("O codigo introduzido nao pertence a nenhuma hospedagem");
			    }

			}
			else
				Clients.showNotification("Introduza o c�digo da hospedagem!");	
		}
		
			
   public float addServicosList(){
			float val = 0;
	 List<Alocacao_has_Servico> q = new Alocacao_has_ServicoDao().findAll();
	 if (q != null) {
	 List <Alocacao_has_Servico> qRes = new ArrayList();
	 Map <Alocacao_has_Servico,String> map = new HashMap();
	
	
	 for (int i =0; i<q.size(); i++) {
		 Alocacao_has_Servico quarto =(Alocacao_has_Servico) q.get(i);
		 if (quarto.getAlocacao().getId_Alocacao() == codigo && quarto.isConfirmaPagamento()==false) {	
			
			  
			     Session sess = alocacaoDao.getSession();
			    Transaction tx = sess.beginTransaction();
			   
			   List<Alocacao_has_Servico> lista = sess.createQuery("from Alocacao_has_Servico where"
			    		+ " id_Alocacao=? and id_Servico=? and confirmaPagamento=?").setParameter(0, codigo).setParameter(1, quarto.getServico().getCodigo()).setParameter(2, false).list();
			    tx.commit();
			    int t = lista.size();
			   
			 map.put(quarto, t+"");
		    // qRes = lista;
			 qRes.add(quarto);
		 }
	 }
	 
	
	 if (qRes.size() != 0) {
		 
			lb_valor.setVisible(true);
			lab_valPagar.setVisible(true);
			lab_mzn.setVisible(true);
			cbx_pagar.setVisible(true);
	    	lab_titleService.setVisible(true);
	    	gd_serv.setVisible(true);
	 //Calcula Preco total
	 for (int m = 0; m < qRes.size(); m++) {
		 val += qRes.get(m).getServico().getPreco();
	 }
	 
	 
	       for (int j = 0; j <qRes.size(); j++) {
	    	
	    		  
	    	 boolean meteOuNao = true;
	    	for (int h =0; h <list_servicos.getItemCount() && list_servicos.getItemCount()!= 0; h++) {
	    		
	    		if (qRes.get(j).getAlocacao().getId_Alocacao() == 
	    				((Alocacao_has_Servico)list_servicos.getItemAtIndex(h).getValue()).getAlocacao().getId_Alocacao() 
	    				&& qRes.get(j).getServico().getCodigo() == 
	    				((Alocacao_has_Servico)list_servicos.getItemAtIndex(h).getValue()).getServico().getCodigo() )  {
	    			   
	    			meteOuNao = false; break;
	    		}
	    		
	    	}
	    	
			
	    	if (meteOuNao) {
	    		
	        Listitem lstit = new Listitem();
			
			Listcell lstcl = new Listcell(String.valueOf(qRes.get(j).getServico().getCodigo()));
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(String.valueOf(qRes.get(j).getServico().getDesignacao()));
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(String.valueOf(qRes.get(j).getServico().getPreco()));
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(String.valueOf(map.get(qRes.get(j))));
			lstit.appendChild(lstcl);
			
			lstcl = new Listcell(String.valueOf(qRes.get(j).getData_de_uso()));
			lstit.appendChild(lstcl);
			
		    lstit.setValue(qRes.get(j));
			list_servicos.appendChild(lstit);
	    
	    	}
	       
	       }
	   
	       
		}else 
			btn_checkout.setDisabled(false);
	 }
	 return val;
   }
   
   public void onCheck$cbx_pagar (Event e ) {
	   
	   if (cbx_pagar.isChecked())  {
		   btn_checkout.setDisabled(false);
		   lb_valor.setValue("0"); 
		   list_alocacao.setDisabled(true);
	   }
	   else {
		   btn_checkout.setDisabled(true);
		   lb_valor.setValue(valor+""); 
		   list_alocacao.setDisabled(false);
	   }
   }
   
   
   public void onClick$btn_checkout(Event e) {
	      wd_confirma.setVisible(true);
		  wd_confirma.doHighlighted();	
   }
   
   public void onClick$btn_cancelar(Event e) {
		//Encaminhar para pagina principal do
		//bl_actualizar.setVisible(false);
	}
   
   public void onClick$btn_nao(Event e) {
		  wd_confirma.setVisible(false);
		  wd_confirma.detach();
	}
	
	public void onClick$btn_sim(Event e){
		wd_confirma.setVisible(false);
		wd_confirma.detach();
		deletaHospedagem();
	}
	
	
	public void deletaHospedagem () {
		Listitem lstit = list_alocacao.getItemAtIndex(0);
		Alocacao alocacao = (Alocacao)lstit.getValue();
		
        int codigo = alocacao.getId_Alocacao();
		 List<Alocacao_has_Servico> q = new Alocacao_has_ServicoDao().findAll();
		 
		 List <Alocacao_has_Servico> qRes = new ArrayList();	
		
		 for (int i =0; i<q.size(); i++) {
			 Alocacao_has_Servico quarto =(Alocacao_has_Servico) q.get(i);
			 if (quarto.getAlocacao().getId_Alocacao() ==codigo && quarto.isConfirmaPagamento() == false) {	
				
				 qRes.add(quarto);
			 }
		}
	    
		 for (int i =0; i < qRes.size(); i++) {
			 Alocacao_has_Servico bu =(Alocacao_has_Servico) qRes.get(i);
			 bu.setConfirmaPagamento(true);
			 new Alocacao_has_ServicoDao().update(bu);
		 }
		 //Torna a hospedagem alocada e termina....
		 alocacao.setAlocado(true);
		 new AlocacaoDao().update(alocacao);
		
		Clients.showNotification("Checkout efectuado com sucesso!!");
		btn_checkout.setDisabled(true);
		limpar();
		
    	lb_valor.setVisible(false);
		lab_valPagar.setVisible(false);
		lab_mzn.setVisible(false);
		cbx_pagar.setVisible(false);
    	lab_titleService.setVisible(false);
    	gd_serv.setVisible(false);
		
	}
   
   
}
