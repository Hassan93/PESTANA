package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.*;
import mz.PestanaRovuma.Hotel_and_Resorts.model.*;


public class ActualizarHospedagemController extends GenericForwardComposer{

	private Textbox txt_codAlocacao;
	private Button btn_ok;
	private Button btn_actualizar;
	private Button btn_cancelar;
	private Listbox list_alocacao;
	private Datebox dbx_dataout;
	private Combobox cbx_categoria;
	private Combobox cbx_quarto;
	private Label lab_valor;
	private Listitem lstit_vazio;
	private Window wd_confirma;
	private Borderlayout bl_actualizar;
	Alocacao alocacacao;
	AlocacaoDao alocacaoDao = new AlocacaoDao();
    Operacao operacao = new Operacao();
	
	public void onSelect$cbx_categoria(Event e){
		
	//Sun Nov 15 18:48:09 FET 2015   
		
		String d1 = alocacacao.getHospede().getOcupante().getDataCheckOut().toString();
		StringTokenizer st1 = new StringTokenizer(d1,"-");
		String d2 = dbx_dataout.getValue().toString();
		StringTokenizer st2 = new StringTokenizer(d2," ");
		
		
		String ano = st1.nextToken();
		String mes = st1.nextToken();
		String dia = st1.nextToken().substring(0,2);

		
		st2.nextToken();
		String mes1 = st2.nextToken();
		String dia1 = st2.nextToken();
		
		st2.nextToken();
		st2.nextToken();
		String ano1 = st2.nextToken();
		String mesV1 = "";
		switch (mes1) {
		case "Jan" : mesV1 = "01"; break;
		case "Feb" : mesV1 = "02"; break;
		case "Mar" : mesV1 = "03"; break;
		case "Apr" : mesV1 = "04"; break;
		case "May" : mesV1 = "05"; break;
		case "Jun" : mesV1 = "06"; break;
		case "Jul" : mesV1 = "07"; break;
		case "Aug" : mesV1 = "08"; break;
		case "Sep" : mesV1 = "09"; break;
		case "Oct" : mesV1 = "10"; break;
		case "Nov" : mesV1 = "11"; break;
		case "Dec" : mesV1 = "12"; break;		   
		}
		
		String dataOut = dia+"-"+mes+"-"+ano;
		String dataBox = dia1+"-"+mesV1+"-"+ano1;
		
		int duracao = operacao.diferencaDatas(dataOut,dataBox, "-");
		 cbx_quarto.setDisabled(false);
		adicionaQuartosComValidacaoData(dataOut, dataBox);
		
		actualizaValorPagar(duracao);
	     
		
	}
	
public void adicionaQuartosComValidacaoData(String dataInicial, String dataFinal) {
		
        List  <Alocacao>reservas = getAlocacoes();
        List <Quarto> quartoss = new ArrayList();
	    
	    int d1 = operacao.diferencaDatas("0-0-0",dataInicial, "-");
	    int d2 = operacao.diferencaDatas("0-0-0",dataFinal, "-");
	    
	    List <Quarto> quartos =  getQuartos();
		
		for (int j = 0; j <quartos.size(); j++) {
			
				
	    
	    for (int i =0; i < reservas.size(); i++) {
	    	
	    	Alocacao reserva = (Alocacao)reservas.get(i);
	    	
			StringTokenizer st1 = new StringTokenizer(String.valueOf(reserva.getHospede().getOcupante().getData_checkin()),"-");
			StringTokenizer st2 = new StringTokenizer(String.valueOf(reserva.getHospede().getOcupante().getDataCheckOut()),"-");
			
			String ano = st1.nextToken();
			String mes = st1.nextToken();
			String dia = st1.nextToken().substring(0,2);
			
			String ano1 = st2.nextToken();
			String mes1 = st2.nextToken();
			String dia1 = st2.nextToken().substring(0,2);
			
			String dataIN = dia+"-"+mes+"-"+ano;
			String dataOUT = dia1+"-"+mes1+"-"+ano1;
			
	        int dR1= operacao.diferencaDatas("0-0-0",dataIN,"-");
	  	    int dR2= operacao.diferencaDatas("0-0-0",dataOUT,"-");
	  	    
	  	     if (((Quarto)quartos.get(j)).getId_Quarto() == reservas.get(i).getHospede().getOcupante().getQuarto().getId_Quarto())  {
	  	    	 if (!((d1 < dR1 && d2 <= dR1 && d2 > d1) || (d1 >= dR2 && d2 > d1))  )    {			  	    
			          quartoss.add(reservas.get(i).getHospede().getOcupante().getQuarto());	
				  }
	  	     } 
	      
				
			}
		}
			int valida = 0;
		for (int j = 0; j <quartos.size(); j++) {
		if (((Quarto)quartos.get(j)).getCategoria().getDesignacao().equalsIgnoreCase(cbx_categoria.getValue())  ) {
			for (int k =0; k<quartoss.size(); k++) {
				valida=1;
				if (quartoss.get(k).getId_Quarto() !=  ((Quarto)quartos.get(j)).getId_Quarto() ) {
					   Comboitem quarto = new Comboitem();
				       	quarto.setValue((Quarto)quartos.get(j));
				    	quarto.setLabel(((Quarto)quartos.get(j)).getDesignacao());
				     	cbx_quarto.appendChild(quarto);	
				}
			}
			if (valida == 0) {
				 Comboitem quarto = new Comboitem();
			       	quarto.setValue((Quarto)quartos.get(j));
			    	quarto.setLabel(((Quarto)quartos.get(j)).getDesignacao());
			     	cbx_quarto.appendChild(quarto);	
			}
	     
		}
	    }
	   
	}

    public List<Alocacao>getAlocacoes() {
    	return new AlocacaoDao().findAll();
    }
	
	public void onSelect$cbx_quarto (Event e) {
		btn_actualizar.setDisabled(false);
	}
	
	public void onChanging$cbx_categoria(Event e){
		cbx_quarto.setDisabled(false);		
	    
	    try {
		while (true) {
			if (cbx_quarto.getItemAtIndex(0) == null)
				break;
			else
			     cbx_quarto.removeItemAt(0);	     
		}
		    
	    }catch(IndexOutOfBoundsException rt){
	    	
	    }
	    
	}
	
	public void onChange$cbx_categoria(Event e){
		cbx_quarto.setDisabled(false);		
	    
	    int j =0;
	    try {
		while (true) {
			if (cbx_quarto.getItemAtIndex(0) == null)
				break;
			else
			     cbx_quarto.removeItemAt(0);	     
		  j++;
		}
	    }catch(IndexOutOfBoundsException rt){
	    	
	    }
	    
	}
	
	
	
	public void onChange$dbx_dataout(Event e){
		
		String d1 = alocacacao.getHospede().getOcupante().getDataCheckOut().toString();
		StringTokenizer st1 = new StringTokenizer(d1,"-");
		String d2 = dbx_dataout.getValue().toString();
		StringTokenizer st2 = new StringTokenizer(d2," ");
		
		
		String ano = st1.nextToken();
		String mes = st1.nextToken();
		String dia = st1.nextToken().substring(0,2);
	
		
		st2.nextToken();
		String mes1 = st2.nextToken();
		String dia1 = st2.nextToken();
		
		st2.nextToken();
		st2.nextToken();
		String ano1 = st2.nextToken();
		String mesV1 = "";
		switch (mes1) {
		case "Jan" : mesV1 = "01"; break;
		case "Feb" : mesV1 = "02"; break;
		case "Mar" : mesV1 = "03"; break;
		case "Apr" : mesV1 = "04"; break;
		case "May" : mesV1 = "05"; break;
		case "Jun" : mesV1 = "06"; break;
		case "Jul" : mesV1 = "07"; break;
		case "Aug" : mesV1 = "08"; break;
		case "Sep" : mesV1 = "09"; break;
		case "Oct" : mesV1 = "10"; break;
		case "Nov" : mesV1 = "11"; break;
		case "Dec" : mesV1 = "12"; break;
		   
		}
		
		String dataOut = dia+"-"+mes+"-"+ano;
		String dataBox = dia1+"-"+mesV1+"-"+ano1;
		
		
		int duracao = operacao.diferencaDatas(dataOut,dataBox, "-");
		
		if (duracao > 0) {
		
			
		cbx_categoria.setDisabled(false);
		}else
			Clients.showNotification("A nova data de saida deve ser superior da data actual de checkout!");
		}
	
	public void onClick$btn_ok(Event e) {

		int codigo = 0;
		if (list_alocacao.getItemCount() == 1 ) 
		if (txt_codAlocacao.getText() != "" )  {
			 codigo = Integer.parseInt(txt_codAlocacao.getText());
		  
		    Session sess = alocacaoDao.getSession();
		    Transaction tx = sess.beginTransaction();
		    Alocacao alocacao =  (Alocacao)sess.createQuery("from Alocacao where"
		    		+ " id_Alocacao=?").setParameter(0, codigo).uniqueResult();
		    tx.commit();
		   
		    if (alocacao != null ) {
		    	if (!alocacao.isAlocado()) {
		    	alocacacao = alocacao;
		    	dbx_dataout.setDisabled(false);

				addHospedagemList(alocacao);
		    	}else
		    		Clients.showNotification("A hospedagem indicada já passou do checkout!");
		   }else
		    {
		    	Clients.showNotification("O codigo introduzido nao pertence a nenhuma hospedagem");
		    }

		}
		else
			Clients.showNotification("Introduza o código da hospedagem!");	
	}
	
	public void onClick$btn_actualizar(Event e) {
		
		  wd_confirma.setVisible(true);
		  wd_confirma.doHighlighted();	  
		
	}
	
	public void onClick$btn_cancelar(Event e) {
		//Encaminhar para pagina principal do
		bl_actualizar.setVisible(false);
	}
	
	public void onClick$btn_nao(Event e) {
		  wd_confirma.setVisible(false);
	}
	
	public void onClick$btn_sim(Event e){
		wd_confirma.setVisible(false);
		actualizarHospedagem();
	}
	
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
	
	public void actualizarHospedagem() {
		
		Listitem lstit = list_alocacao.getItemAtIndex(0);
		Alocacao alocacao = lstit.getValue();
		AlocacaoDao alocacaoDao = new AlocacaoDao();
		
	
		Session sess = alocacaoDao.getSession();
	    Transaction tx = sess.beginTransaction();
	    Reserva_Quarto rq =  (Reserva_Quarto)sess.createQuery("from Reserva_Quarto where"
	    		+ " id_Quarto=? and id_Reserva=?").setParameter(0, alocacao.getHospede().getOcupante().getQuarto().getId_Quarto()).setParameter(1,alocacao.getReserva().getId_Reserva() ).uniqueResult();
	    tx.commit();
	    
	    Session ses = alocacaoDao.getSession();
	    Transaction t = ses.beginTransaction();
	    Ocupante ocupante = (Ocupante) ses.createQuery("from Ocupante where id_reserva_quarto=?").setParameter(0, rq.getId()).uniqueResult();
	    ocupante.setQuarto((Quarto)cbx_quarto.getSelectedItem().getValue());
	    ocupante.setDataCheckOut(dbx_dataout.getValue());;
	    new OcupanteDao().update(ocupante);
		new Reserva_QuartoDao().update(rq);
	
        cbx_categoria.removeItemAt(0);
		cbx_quarto.removeItemAt(0);
		limpar();
		Clients.showNotification("Hospedagem actualizada com sucesso!!");
		
	}
	
	public void limpar(){
		
		txt_codAlocacao.setText("");
		cbx_categoria.setText("");
		cbx_quarto.setText("");
		dbx_dataout.setText("");
		
		cbx_categoria.setDisabled(true);
		cbx_quarto.setDisabled(true);
		dbx_dataout.setDisabled(true);
		btn_actualizar.setDisabled(true);
		
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
	
	public void actualizaValorPagar(int duracao) {
		
	
		double valorActual = Double.parseDouble(lab_valor.getValue());
		Categoria categoria	= (Categoria)cbx_categoria.getSelectedItem().getValue();
		double preco = categoria.getPreco();	
		
		System.out.println("Dif: "+duracao);
		lab_valor.setValue((valorActual+preco*+duracao)+"");
	}
	
	
	
	public List<Categoria> getCategorias() {
		return new CategoriaDao().findAll();
	}
	
	public List<Quarto> getQuartos() {
		Categoria categoria= retornaCategoria();
		 List<Quarto> q = new QuartoDao().findAll();
		 List <Quarto> qRes = new ArrayList();
		 for (int i =0; i<q.size(); i++) {
			 Quarto quarto =(Quarto) q.get(i);
			 if (quarto.getCategoria().getDesignacao().equalsIgnoreCase(categoria.getDesignacao())) {		 
				 qRes.add(quarto);
			 }
		 }
		return qRes;
	}
	
	public Categoria retornaCategoria() {
	
		String categoria = (String)cbx_categoria.getValue();
		
		  Session sess = alocacaoDao.getSession();
		    Transaction tx = sess.beginTransaction();
		    Categoria cate =  (Categoria)sess.createQuery("from Categoria where"
		    		+ " designacao=?").setParameter(0, categoria).uniqueResult();
		    tx.commit();
		   
		return cate;
	}
	
	
}
