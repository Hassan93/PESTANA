package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.AlocacaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.HospedeDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.OcupanteDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ReservaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.Reserva_QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Alocacao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Hospede;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Ocupante;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva_Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Servico;

public class checkInController extends GenericForwardComposer {
	private Listbox lista_ocupante;
	private Button btn_ok;
	private Textbox txt_codigo;
	private ReservaDao reservaDao = new ReservaDao();
	private Listitem lista_reserva;
	private Label lb_nome;
	private Label lb_categoria;
	private Label lb_quarto;
	private Label lb_quantPessoas;
	private Label lb_dataCheckIn;
	private Label lb_dataCheckOut;
	private Label lb_quantDias;
	private Label lb_horaSaida;
	private Label lb_preco;
	private Label lb_quantQuartos;
	private Label lb_custo;
	private double valorActual;
	private Groupbox gb_informacao;
	private Groupbox gb_valorTotal;
	private Groupbox gb_pagamento;
	private HospedeDao hospedeDao = new HospedeDao();
	private Button btn_confirmar;
	private List<Ocupante> reservar = new ArrayList();
	private AlocacaoDao alocacaoDao = new AlocacaoDao();
	private OcupanteDao ocupanteDao = new OcupanteDao();
	private Reserva_QuartoDao reservaQuartoDao = new Reserva_QuartoDao();
	
	private Reserva_Quarto rq ;
	
	
	
	
	public void onClick$btn_ok(Event e) {

		int codigo = 0;
		valorActual= 0;
		//if (list_alocacao.getItemCount() == 1 ) 
		if (txt_codigo.getText() != "" )  {
			 codigo = Integer.parseInt(txt_codigo.getText());
		  
		    Session sess = reservaDao.getSession();
		    Transaction tx = sess.beginTransaction();
		    
		    Reserva reserva =  (Reserva)sess.createQuery("from Reserva where"
		    		+ " id_Reserva=?").setParameter(0, codigo).uniqueResult();
	    	tx.commit();	

		    
	    	Session se = reservaQuartoDao.getSession();
		    Transaction ts= se.beginTransaction();

		    List<Reserva_Quarto> rq =  (List<Reserva_Quarto>)se.createQuery("from Reserva_Quarto where"
		    		+ " id_Reserva=?").setParameter(0, codigo).list();
		    ts.commit();
		    
		    
		    
		   
	    	
	    	    
	    	    if (reserva != null) {
			    	if(reserva.isAlocado()==false){
			    	
					addReservaList(reserva);
					 for(int i=0; i<rq.size();i++){
					    	Session ses = ocupanteDao.getSession();
						    Transaction t = ses.beginTransaction();
					    	Ocupante ocupante =  (Ocupante)ses.createQuery("from Ocupante where"
					    		+ " id_Reserva_Quarto=?").setParameter(0, rq.get(i).getId()).uniqueResult();
							t.commit();

					addOcupanteList(ocupante);
					reservar.add(ocupante);
				
			    	}
					 lb_custo.setValue(String.valueOf(valorActual));
					 lb_quantQuartos.setValue(String.valueOf(rq.size()));
					gb_informacao.setVisible(true);
					lista_ocupante.setVisible(true);
			    	gb_valorTotal.setVisible(true);
			    	gb_pagamento.setVisible(true);
			    }
			   }else
			    {
			    	Clients.showNotification("O codigo introduzido nao pertence a nenhuma reserva");
			    }
	    	   

		}
		else{
			Clients.showNotification("Introduza o código da reserva!");	

		}
		
	}
	
	
	
	private void addReservaList(Reserva  reserva){
	
		
		lb_nome.setValue(reserva.getCliente().getNome()+"  "+reserva.getCliente().getApelido());
		
	}
	
	public void addOcupanteList(Ocupante ocupante){
	
		Session sess = reservaQuartoDao.getSession();
	    Transaction tx = sess.beginTransaction();
	    Reserva_Quarto rq =  (Reserva_Quarto)sess.createQuery("from Reserva_Quarto where"
	    		+ " id=?").setParameter(0, ocupante.getReserva().getId()).uniqueResult();
	    tx.commit();
		
				
		String d1 = ocupante.getData_checkin().toString();
		StringTokenizer st1 = new StringTokenizer(d1,"-");
		String d2 =ocupante.getDataCheckOut().toString();
		StringTokenizer st2 = new StringTokenizer(d2,"-");
		String ano = st1.nextToken();
		String mes = st1.nextToken();
		String dia = st1.nextToken().substring(0,2);
		
		String ano2 = st2.nextToken();
		String mes2 = st2.nextToken();
		String dia2 = st2.nextToken().substring(0,2);
		String dataOut = dia+"-"+mes+"-"+ano;
		String dataBox = dia2+"-"+mes2+"-"+ano2;
		int quantDias = new Operacao().diferencaDatas(dataOut,dataBox, "-");
		
		Listitem lstit = new Listitem();
		
		Listcell lstcl = new Listcell(ocupante.getNome());
		lstit.appendChild(lstcl);
		lstcl = new Listcell(rq.getQuarto().getCategoria().getDesignacao());
		lstit.appendChild(lstcl);
		lstcl= new Listcell(rq.getQuarto().getDesignacao());
		lstit.appendChild(lstcl);
		lstcl= new Listcell(String.valueOf(dataBox));
		lstit.appendChild(lstcl);
		lstcl= new Listcell(String.valueOf(dataOut));
		lstit.appendChild(lstcl);
		lstcl= new Listcell(String.valueOf(rq.getQuarto().getCategoria().getPreco()));
		lstit.appendChild(lstcl);
		lstcl= new Listcell(String.valueOf(quantDias));
		lstit.appendChild(lstcl);
		lstcl= new Listcell(String.valueOf(rq.getQuarto().getCategoria().getPreco()*quantDias));
		lstit.appendChild(lstcl);
		lstit.setValue(rq);
		lista_ocupante.appendChild(lstit);
		
		
		
		calcularValorPagar(rq.getQuarto().getCategoria().getPreco()*quantDias);
	//	lb_custo.setValue(String.valueOf(valorTotal));

		
	}
	
	
	public  double calcularValorPagar(Double valor) {
		
		valorActual+=valor;

		return valorActual;
	}
	
	public void onClick$btn_confirmar(){
		 int code = Integer.parseInt(txt_codigo.getText());

			Hospede hospede =new Hospede();
			 Session sese = reservaDao.getSession();
			    Transaction t = sese.beginTransaction();
			    Reserva reserva =  (Reserva)sese.createQuery("from Reserva where"
			    		+ " id_Reserva=?").setParameter(0, code).uniqueResult();
			    t.commit();
			for (int i=0; i<reservar.size();i++){
			hospede.setOcupante(reservar.get(i));
		
			hospedeDao.create(hospede);
			    Session sess = hospedeDao.getSession();
			    Transaction tx = sess.beginTransaction();
			    Hospede hosp =  (Hospede)sess.createQuery("from Hospede where"
			    		+ " id_ocupante=?").setParameter(0, reservar.get(i)).uniqueResult();
			    tx.commit();
			   
			Alocacao alocacao = new Alocacao();
			  
			   
			
			alocacao.setReserva(reserva);
			alocacao.setHospede(hospede);
			alocacao.setAlocado(true);
			alocacaoDao.create(alocacao);
	}
			reserva.setAlocado(true);

				Clients.showNotification("Hospede adicionado com sucesso");
				
			
	}

	
	


}
