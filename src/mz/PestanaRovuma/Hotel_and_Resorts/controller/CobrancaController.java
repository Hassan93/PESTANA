package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import mz.PestanaRovuma.Hotel_and_Resorts.model.*;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.Alocacao_has_ServicoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.controller.*;

public class CobrancaController extends GenericForwardComposer {

	private Textbox txt_codAlocacao;
	private Button btn_ok;
	private Button btn_pagar;
	private Button btn_cancelar;
	private Listbox list_alocacao;
	
	private Label lb_valor;
	private Label lab_valPagar;
	private Label lab_mzn;
	private Label lab_titleService;
	private Grid gd_serv;
	private Checkbox chx_todos;
	
	private Listitem lstit_vazio;

	private Borderlayout bl_actualizar;
	private Listbox list_servicos;
	int codigo = 0;
	AlocacaoDao alocacaoDao = new AlocacaoDao();
    Alocacao alocacacao;
	float valor;
	List <Alocacao_has_Servico>yt = new ArrayList();
	
	private void addHospedagemList(Alocacao alocacao){
		
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
		    
				
		         addServicosList();
				addHospedagemList(alocacao);
				//lb_valor.setValue(valor+"");
				if (!lb_valor.isVisible())
					btn_pagar.setVisible(true);
		    	}else
		    		Clients.showNotification("A hospedagem já passou de checkout!");
		   }else
		    {
		    	Clients.showNotification("O codigo introduzido nao pertence a nenhuma hospedagem");
		    }

		}
		else
			Clients.showNotification("Introduza o código da hospedagem!");	
	}
	
	

public void addServicosList(){
	
	     try {
			while (true) {
				if (list_servicos.getItemAtIndex(0) == null)
					break;
				else
				     list_servicos.removeItemAt(0);	     
			}
			    
		    }catch(IndexOutOfBoundsException rt){
		    	
		    }
	
	
	int verd = 0;
 List<Alocacao_has_Servico> q = new Alocacao_has_ServicoDao().findAll();
 codigo = Integer.parseInt(txt_codAlocacao.getText());
 List <Alocacao_has_Servico> qRes = new ArrayList();
 
 for (int i =0; i<q.size(); i++) {
	 Alocacao_has_Servico quarto =(Alocacao_has_Servico) q.get(i);
	 if (quarto.getAlocacao().getId_Alocacao() ==  codigo) {	
		if ( quarto.isConfirmaPagamento() )
			verd++;
		 qRes.add(quarto);
	 }
 }
 


 if (qRes.size() != 0 ) {
	 if (verd == qRes.size())  {
		 Clients.showNotification("Sem nenhum serviço em dívida!");
	 }else {
		lb_valor.setVisible(true);
		lab_valPagar.setVisible(true);
		lab_mzn.setVisible(true);
    	lab_titleService.setVisible(true);
    	gd_serv.setVisible(true);
    	chx_todos.setVisible(true);

 
       for (int j = 0; j <qRes.size(); j++) {
    	
    		
        Listitem lstit = new Listitem();
		
        Listcell lstcl = new Listcell();
      if (!qRes.get(j).isConfirmaPagamento()) {
    	  Checkbox cbxv= new Checkbox();
    	  cbxv.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					 float val = 0;
					 List<Alocacao_has_Servico> q = new Alocacao_has_ServicoDao().findAll();
					 
					 for (int i =0; i<q.size(); i++) {
						 Alocacao_has_Servico quarto =(Alocacao_has_Servico) q.get(i);
						 Listitem lis = list_servicos.getItemAtIndex(i);
						 if (!quarto.isConfirmaPagamento() &&  ((Checkbox)((Listcell)lis.getChildren().get(0)).getFirstChild()).isChecked()) {		   
							 val += quarto.getServico().getPreco();
						 }
					 }
					 
					 lb_valor.setValue(val+"");
					 if (val>0)
					 btn_pagar.setDisabled(false);
					 else
						 btn_pagar.setDisabled(true);
				}
			});
        lstcl.appendChild(cbxv);
		lstit.appendChild(lstcl);
      }else{
    	  lstcl =new Listcell("----");
  		  lstit.appendChild(lstcl);    
      }
		lstcl = new Listcell(String.valueOf(qRes.get(j).getServico().getDesignacao()));
		lstit.appendChild(lstcl);
		
		lstcl = new Listcell(String.valueOf(qRes.get(j).getHora_de_uso()));
		lstit.appendChild(lstcl);
		
		lstcl = new Listcell(String.valueOf(qRes.get(j).getData_de_uso()));
		lstit.appendChild(lstcl);
		
		lstcl = new Listcell(String.valueOf(qRes.get(j).getServico().getPreco()));
		lstit.appendChild(lstcl);
		if (!qRes.get(j).isConfirmaPagamento())
		lstcl = new Listcell("NÃO PAGO");
		else lstcl = new Listcell("PAGO");
		lstcl.setStyle("font-weight:bold;");
		lstit.appendChild(lstcl);
		
		
		
	    lstit.setValue(qRes.get(j));
	    if (!qRes.get(j).isConfirmaPagamento()) 
	    	lstit.setStyle("font-weight:bold;");
	    else
	    	lstit.setStyle("text-color:red;");
		list_servicos.appendChild(lstit);
    
		
    	}  
	 }	
 }
}

public void onClick$btn_pagar(Event e) {
	pagamento();
}

public void onClick$btn_cancelar(Event e) {
	//Encaminhar para pagina principal do
	//bl_actualizar.setVisible(false);
}


public void pagamento () {
	
	 List<Alocacao_has_Servico> q = new Alocacao_has_ServicoDao().findAll();
	 
	 for (int i =0; i<q.size(); i++) {
		 Alocacao_has_Servico quarto =(Alocacao_has_Servico) q.get(i);
		 Listitem lis = list_servicos.getItemAtIndex(i);
		 if (!quarto.isConfirmaPagamento() &&  ((Checkbox)((Listcell)lis.getChildren().get(0)).getFirstChild()).isChecked()) {		   
			 quarto.setConfirmaPagamento(true);
			 new Alocacao_has_ServicoDao().update(quarto);
		 }
	 }
	 
	
	Clients.showNotification("Pagamento efectuado com sucesso!!");
	btn_pagar.setDisabled(true);
	limpar();
	
	lb_valor.setVisible(false);
	lab_valPagar.setVisible(false);
	lab_mzn.setVisible(false);
	lab_titleService.setVisible(false);
	gd_serv.setVisible(false);
	chx_todos.setVisible(false);
}



public void onCheck$chx_todos(Event e) {
	
	int val=0;
	
	if (chx_todos.isChecked()) {
	for (int i = 0; i < list_servicos.getItemCount(); i++) {
		 Listitem lis = list_servicos.getItemAtIndex(i);
		 Alocacao_has_Servico q =( Alocacao_has_Servico) lis.getValue();
		 if (!q.isConfirmaPagamento()) {
			 ((Checkbox)((Listcell)lis.getChildren().get(0)).getFirstChild()).setChecked(true);
			 val += q.getServico().getPreco();
			 
		 }
	}
	
	lb_valor.setValue(val+"");
	btn_pagar.setDisabled(false);
	}
	
	else if (!chx_todos.isChecked()) {
		for (int i = 0; i < list_servicos.getItemCount(); i++) {
			 Listitem lis = list_servicos.getItemAtIndex(i);
			 Alocacao_has_Servico q =( Alocacao_has_Servico) lis.getValue();
			 if (!q.isConfirmaPagamento()) {
				 ((Checkbox)((Listcell)lis.getChildren().get(0)).getFirstChild()).setChecked(false);;
				 val=0;
		}
		}
	lb_valor.setValue(val+"");
	btn_pagar.setDisabled(true);
	  }	
}

}
