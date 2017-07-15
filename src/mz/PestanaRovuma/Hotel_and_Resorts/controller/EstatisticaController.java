package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.*;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao_has_Servico;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;

public class EstatisticaController extends GenericForwardComposer {
	private  Listbox lista_reserva;
	private Listbox lista_hospedagem;
	private Listbox lista_servico;
	private Listbox lista_categoria;
	private Button btn_exemplo;
	private Combobox cbx_objecto;
	private AlocacaoDao alocacaoDAO= new AlocacaoDao();
	private Label lb_valor;
	private Label lb_text;
	private Label lb_catValor;
	private Label lb_catText;
	
	private Datebox db_inicio;
	private Datebox db_final;
	
	private Operacao operacao = new Operacao();
	
	
	
	
	
	public void onChange$cbx_objecto(){
		
		try{
			while (true){
				if (lista_reserva.getItemAtIndex(0)==null || lista_hospedagem.getItemAtIndex(0)==null  || lista_categoria.getItemAtIndex(0)==null || lista_categoria.getItemAtIndex(0)==null)
					break;
				else {
						lista_reserva.removeItemAt(0);
						lista_hospedagem.removeItemAt(0);
						lista_servico.removeItemAt(0);
						lista_categoria.removeItemAt(0);
					}
				}
			}catch (IndexOutOfBoundsException e) {

			}catch (NullPointerException re) {
				
			}
	}
	
public void onChanging$cbx_objecto(){
		
		try{
			while (true){
				if (lista_reserva.getItemAtIndex(0)==null || lista_hospedagem.getItemAtIndex(0)==null  || lista_servico.getItemAtIndex(0)==null || lista_categoria.getItemAtIndex(0)==null)
					break;
				else {
						lista_reserva.removeItemAt(0);
						lista_hospedagem.removeItemAt(0);
						lista_servico.removeItemAt(0);
						lista_categoria.removeItemAt(0);
					}
				}
			}catch (IndexOutOfBoundsException e) {

			}catch (NullPointerException re) {
				
			}
	}
	
	
	public void onSelect$cbx_objecto(){
		
		if (cbx_objecto.getValue().equalsIgnoreCase("Reservas")  ) {
			lista_reserva.setVisible(true);
			lista_hospedagem.setVisible(false);
			lb_valor.setVisible(false);
			lb_text.setVisible(false);
			lista_categoria.setVisible(false);
			lista_servico.setVisible(false);
			lb_catValor.setVisible(false);
			lb_catText.setVisible(false);
//            for (int i =0; i < getReservas().size(); i++) {
//		    	addReservaList(getReservas().get(i));}
//			
			
		} else if (cbx_objecto.getValue().equalsIgnoreCase("Hospedagens" )) {
			lista_reserva.setVisible(false);
			lista_hospedagem.setVisible(true);
			lb_valor.setVisible(false);
			lb_text.setVisible(false);
			lista_servico.setVisible(false);
			lb_catValor.setVisible(false);
			lb_catText.setVisible(false);
			lista_categoria.setVisible(false);
			for (int i =0; i < getHospedagens().size(); i++) {
		    	addHospedagemList(getHospedagens().get(i));
		    	}	
		
		}else if (cbx_objecto.getValue().equalsIgnoreCase("Servicos do hotel" )) {
			lista_reserva.setVisible(false);
			lista_hospedagem.setVisible(false);
			lista_servico.setVisible(true);
			lista_categoria.setVisible(false);
			lb_valor.setVisible(true);
			lb_text.setVisible(true);
			lb_catValor.setVisible(false);
			lb_catText.setVisible(false);
			
			addServicoList();
			
		}else if (cbx_objecto.getValue().equalsIgnoreCase("Categoria de quarto" )) {
			lista_reserva.setVisible(false);
			lista_hospedagem.setVisible(false);
			lista_servico.setVisible(false);
			lb_valor.setVisible(false);
			lb_text.setVisible(false);
			lista_categoria.setVisible(true);
			//addCategoriaList();
			for (int i=0;i<getHospedagens().size();i++){
				adicionaListaCategoria( getHospedagens().get(i));
			}
		}
		
		
		cbx_objecto.setValue(null);
	}

	
	
////	public void addReservaList(Reserva reserva){
////	    
////	    
////		    Listitem lstit=new Listitem();
////			Listcell lstcl= new Listcell(String.valueOf(reserva.getId_Reserva()));
////			lstit.appendChild(lstcl);
////		
////			 lstcl= new Listcell(reserva.getCliente().getNome());
////			 lstit.appendChild(lstcl);
////				
////			lstcl= new Listcell(reserva.getQuarto().getCategoria().getDesignacao());
////			lstit.appendChild(lstcl);
////			
////			lstcl= new Listcell(reserva.getQuarto().getDesignacao());
////			lstit.appendChild(lstcl);
////			
////			lstcl= new Listcell(String.valueOf(reserva.getData_checkin()));
////			lstit.appendChild(lstcl);
////			lstcl= new Listcell(String.valueOf(reserva.getData_checkout()));
////			lstit.appendChild(lstcl);
////			lstcl= new Listcell(String.valueOf(reserva.getQuarto().getCategoria().getPreco()));
////			lstit.appendChild(lstcl);
////				
////			lstit.setValue(reserva);
////			
////			lista_reserva.appendChild(lstit);
////	    	 
////		}
//	
	
	public void addServicoList(){
		 List<Alocacao_has_Servico> q = new Alocacao_has_ServicoDao().findAll();
		 
		 List <Alocacao_has_Servico> qRes = new ArrayList();
		 Map <Alocacao_has_Servico,String> map = new HashMap();
		 float valor=0;
		
		 for (int i =0; i<q.size(); i++) {
			 Alocacao_has_Servico quarto =(Alocacao_has_Servico) q.get(i);
			 if (quarto.isConfirmaPagamento()==false) {	
				
				  
				     Session sess = new Alocacao_has_ServicoDao().getSession();
				    Transaction tx = sess.beginTransaction();
				   
				   List<Alocacao_has_Servico> lista = sess.createQuery("from Alocacao_has_Servico where"
				    		+ "  id_Servico=? and confirmaPagamento=?").setParameter(0, quarto.getServico().getCodigo()).setParameter(1, false).list();
				    tx.commit();
				    int t = lista.size();
				   
				 map.put(quarto, t+"");
			    // qRes = lista;
				 qRes.add(quarto);
			 }
		 }
		 
		
		 if (qRes.size() != 0) {
			 
		 
		 
		       for (int j = 0; j <qRes.size(); j++) {
		    	
		    		  
		    	 boolean meteOuNao = true;
		    	for (int h =0; h <lista_servico.getItemCount() && lista_servico.getItemCount()!= 0; h++) {
		    		
		    		if (qRes.get(j).getServico().getCodigo() == 
		    				((Alocacao_has_Servico)lista_servico.getItemAtIndex(h).getValue()).getServico().getCodigo() )  {
		    			   
		    			meteOuNao = false; break;
		    		}
		    		
		    	}
		   	
				
		 if (meteOuNao) {
			Listitem lstit = new Listitem();
			
			Listcell lstcl = new Listcell(String.valueOf(qRes.get(j).getServico().getCodigo()));
			lstit.appendChild(lstcl);
			lstcl = new Listcell(qRes.get(j).getServico().getDesignacao());
			lstit.appendChild(lstcl);
			lstcl= new Listcell(String.valueOf(map.get(qRes.get(j))));
			lstit.appendChild(lstcl);
			lstcl= new Listcell(String.valueOf(qRes.get(j).getServico().getPreco()*Float.parseFloat(map.get(qRes.get(j)))));
			lstit.appendChild(lstcl);
			lstit.setValue(qRes.get(j));
			lista_servico.appendChild(lstit);
			valor+=qRes.get(j).getServico().getPreco()*Float.parseFloat(map.get(qRes.get(j)));
		 }
			
	    	
	    }
		  lb_valor.setValue(valor+"");    
		 }
		       
 }
	



public void adicionaListaAlocacao(Alocacao alocacao) {
	Map <Alocacao,String> map = new HashMap();

    Listitem lstit=new Listitem();
	Listcell lstcl= new Listcell(String.valueOf(alocacao.getId_Alocacao()));
	lstit.appendChild(lstcl);

	 lstcl= new Listcell(String.valueOf(alocacao.getReserva().getId_Reserva()));
	 lstit.appendChild(lstcl);
		
	lstcl= new Listcell(alocacao.getHospede().getOcupante().getQuarto().getCategoria().getDesignacao());
	lstit.appendChild(lstcl);
	
	lstcl= new Listcell(alocacao.getHospede().getOcupante().getQuarto().getDesignacao());
	lstit.appendChild(lstcl);
	
	lstcl= new Listcell(alocacao.getHospede().getOcupante().getNome());
	lstit.appendChild(lstcl);
	lstcl= new Listcell(String.valueOf(alocacao.getHospede().getOcupante().getData_checkin()));
	lstit.appendChild(lstcl);
	lstcl= new Listcell(String.valueOf(alocacao.getHospede().getOcupante().getDataCheckOut()));
	lstit.appendChild(lstcl);
		
	lstit.setValue(alocacao);
	
	lista_hospedagem.appendChild(lstit);
	
	
}

	
	
	public void addHospedagemList(Alocacao alocacao){
        
		if (db_inicio.getValue() != null && db_final.getValue() != null) {
		   if (filtra(alocacao.getHospede().getOcupante().getData_checkin())) {
		
		  adicionaListaAlocacao(alocacao);
		       }
		}else if (db_inicio.getValue() != null && db_final.getValue() ==null ) {
			if (filtraDataInicio(alocacao.getHospede().getOcupante().getData_checkin())) {
				adicionaListaAlocacao(alocacao);		
			}
			
		}else if (db_final.getValue() != null && db_inicio.getValue() == null) {
			if ( filtraDataFinal(alocacao.getHospede().getOcupante().getData_checkin())) {
				adicionaListaAlocacao(alocacao);
			}
		}else {
			adicionaListaAlocacao(alocacao);
			
		}
	}
	public void adicionaListaCategoria(Alocacao alocacao){
		if (db_inicio.getValue() != null && db_final.getValue() != null) {
			   if (filtra(alocacao.getHospede().getOcupante().getData_checkin())) {
			
			  addCategoriaList();
			       }
			}else if (db_inicio.getValue() != null && db_final.getValue() ==null ) {
				if (filtraDataInicio(alocacao.getHospede().getOcupante().getData_checkin())) {
					addCategoriaList();		
				}
				
			}else if (db_final.getValue() != null && db_inicio.getValue() == null) {
				if ( filtraDataFinal(alocacao.getHospede().getOcupante().getData_checkin())) {
					addCategoriaList();
				}
			}else {
				addCategoriaList();
				
			}
		
	}
	
	public void addCategoriaList(){
		List<Alocacao> q = new AlocacaoDao().findAll();
		List<Reserva> r = new ReservaDao().findAll();
		 
		 List <Alocacao> qRes = new ArrayList();
		 Map <Alocacao,String> map = new HashMap();
		 float valor=0;
		
		 for (int i =0; i<q.size(); i++) {
			 Alocacao aloc =(Alocacao) q.get(i);
			 Reserva reserva =(Reserva) r.get(i);
			 if (aloc.getReserva().getId_Reserva()==reserva.getId_Reserva()) {	
				
				  
				    Session sess = new Alocacao_has_ServicoDao().getSession();
				    Transaction tx = sess.beginTransaction();
				   
				   List<Alocacao> lista = sess.createQuery("from Alocacao where"
				    		+ "  id_Reserva=? ").setParameter(0, reserva.getId_Reserva()).list();
				    tx.commit();
				    int t = lista.size();
				   
				 map.put(aloc, t+"");
			    // qRes = lista;
				 qRes.add(aloc);
			 }
			 if (qRes.size() != 0) {
				 
			       for (int j = 0; j <qRes.size(); j++) {
			    		  
			    	boolean meteOuNao = true;
			    	for (int h =0; h <lista_categoria.getItemCount() && lista_categoria.getItemCount()!= 0; h++) {
			    		
			    		if (qRes.get(j).getReserva().getId_Reserva() == 
			    				((Alocacao_has_Servico)lista_servico.getItemAtIndex(h).getValue()).getServico().getCodigo() )  {
			    			   
			    			meteOuNao = false; break;
			    		}
			    		
			    	}
			   	
		if (meteOuNao){
		
		Listitem lstit = new Listitem();
		Listcell lstcl = new Listcell(String.valueOf(qRes.get(j).getHospede().getOcupante().getQuarto().getCategoria().getId_Categoria()));
		lstit.appendChild(lstcl);

		lstcl = new Listcell(qRes.get(j).getHospede().getOcupante().getQuarto().getCategoria().getDesignacao());
		lstit.appendChild(lstcl);

		lstcl = new Listcell(qRes.get(j).getHospede().getOcupante().getQuarto().getCategoria().getDescricao());
		lstit.appendChild(lstcl);

		lstcl = new Listcell(String.valueOf(qRes.get(j).getHospede().getOcupante().getQuarto().getCategoria().getPreco()));
		lstit.appendChild(lstcl);

		lstcl= new Listcell(String.valueOf(map.get(qRes.get(j))));
		lstit.appendChild(lstcl);
		lstcl= new Listcell(String.valueOf(qRes.get(j).getHospede().getOcupante().getQuarto().getCategoria().getPreco()*Double.parseDouble((map.get(qRes.get(j))))));
		lstit.appendChild(lstcl);
		
		lstit.setValue(qRes.get(j));
		lista_categoria.appendChild(lstit);
		valor+=qRes.get(j).getHospede().getOcupante().getQuarto().getCategoria().getPreco()*Double.parseDouble((map.get(qRes.get(j))));

		 }
	    }
		lb_catValor.setValue(valor+"");
	   }
     }
	}

	public List<Reserva> getReservas() {
		return new ReservaDao().findAll();
	}

	public List<Alocacao> getHospedagens() {
		return new AlocacaoDao().findAll();
	}
	
	
	
	
	public boolean filtra(Date data) {
		
		StringTokenizer st2 = new StringTokenizer(String.valueOf(data)," ");
        
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
		
		String dataBox1 = dia1+"-"+mesV1+"-"+ano1;
		
		
		StringTokenizer st3 = new StringTokenizer(String.valueOf(db_final.getValue())," ");
		   
		st3.nextToken();
		String mes2 = st3.nextToken();
		String dia2 = st3.nextToken();
		
		st3.nextToken();
		st3.nextToken();
		String ano2 = st3.nextToken();
		String mesV2 = "";
		switch (mes2) {
		case "Jan" : mesV2 = "01"; break;
		case "Feb" : mesV2 = "02"; break;
		case "Mar" : mesV2 = "03"; break;
		case "Apr" : mesV2 = "04"; break;
		case "May" : mesV2 = "05"; break;
		case "Jun" : mesV2 = "06"; break;
		case "Jul" : mesV2 = "07"; break;
		case "Aug" : mesV2 = "08"; break;
		case "Sep" : mesV2 = "09"; break;
		case "Oct" : mesV2 = "10"; break;
		case "Nov" : mesV2 = "11"; break;
		case "Dec" : mesV2 = "12"; break;
		   
		}
		
		String dataBox2 = dia2+"-"+mesV2+"-"+ano2;
		
	
           
		 int d1 = operacao.diferencaDatas("0-0-0",dataBox1, "-");
		 int d2 = operacao.diferencaDatas("0-0-0",dataBox2, "-");
		    
		StringTokenizer st1 = new StringTokenizer(String.valueOf(data),"-");
		
		String ano = st1.nextToken();
		String mes = st1.nextToken();
		String dia = st1.nextToken().substring(0,2);
			
		String dataIN = dia+"-"+mes+"-"+ano;
	
        int dR1= operacao.diferencaDatas("0-0-0",dataIN,"-");
        
        if (dR1 >=d1 && dR1 <=d2) {
        	return true;
        }else
        	return false;
	}
	
	
public boolean filtraDataFinal(Date data) {
		
		StringTokenizer st3 = new StringTokenizer(String.valueOf(db_final.getValue())," ");
		   
		st3.nextToken();
		String mes2 = st3.nextToken();
		String dia2 = st3.nextToken();
		
		st3.nextToken();
		st3.nextToken();
		String ano2 = st3.nextToken();
		String mesV2 = "";
		switch (mes2) {
		case "Jan" : mesV2 = "01"; break;
		case "Feb" : mesV2 = "02"; break;
		case "Mar" : mesV2 = "03"; break;
		case "Apr" : mesV2 = "04"; break;
		case "May" : mesV2 = "05"; break;
		case "Jun" : mesV2 = "06"; break;
		case "Jul" : mesV2 = "07"; break;
		case "Aug" : mesV2 = "08"; break;
		case "Sep" : mesV2 = "09"; break;
		case "Oct" : mesV2 = "10"; break;
		case "Nov" : mesV2 = "11"; break;
		case "Dec" : mesV2 = "12"; break;
		   
		}
		
		String dataBox2 = dia2+"-"+mesV2+"-"+ano2;
		
		int d2 =operacao.diferencaDatas("0-0-0",dataBox2, "-");
		    
		StringTokenizer st1 = new StringTokenizer(String.valueOf(data),"-");
		
		String ano = st1.nextToken();
		String mes = st1.nextToken();
		String dia = st1.nextToken().substring(0,2);
			
		String dataIN = dia+"-"+mes+"-"+ano;
	
        int dR1= operacao.diferencaDatas("0-0-0",dataIN,"-");
        
        if (dR1 <=d2) {
        	return true;
        }else
        	return false;
	}

public boolean filtraDataInicio(Date data) {
	
	StringTokenizer st2 = new StringTokenizer(String.valueOf(db_inicio.getValue())," ");
    
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
	
	String dataBox1 = dia1+"-"+mesV1+"-"+ano1;
       
	 int d1 = operacao.diferencaDatas("0-0-0",dataBox1, "-");
	    
	StringTokenizer st1 = new StringTokenizer(String.valueOf(data),"-");
	
	String ano = st1.nextToken();
	String mes = st1.nextToken();
	String dia = st1.nextToken().substring(0,2);
		
	String dataIN = dia+"-"+mes+"-"+ano;

    int dR1= operacao.diferencaDatas("0-0-0",dataIN,"-");
    
    if (dR1 >=d1) {
    	return true;
    }else
    	return false;
}
}
