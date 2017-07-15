package mz.PestanaRovuma.Hotel_and_Resorts.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.zkoss.util.Dates;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CartaoCreditoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ClienteDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.OcupanteDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.PaisDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.ReservaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.Reserva_QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.mail.Pestana;
import mz.PestanaRovuma.Hotel_and_Resorts.model.CartaoCredito;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Cliente;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Ocupante;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Pais;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva_Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.relatorio.Executar;
import mz.PestanaRovuma.Hotel_and_Resorts.relatorio.ReservaRelatorio;
import net.sf.jasperreports.engine.JRException;

public class Reserva_form1Controller extends GenericForwardComposer{
	private Cliente cliente =new Cliente();
	private ClienteDao clienteDao=new ClienteDao();
	private Quarto qt;
	private CartaoCredito cartaoCred = new CartaoCredito();
	private CartaoCreditoDao cartaDao =new CartaoCreditoDao();
	private Reserva reserva = new Reserva();
	private List<ReservaRelatorio> lista= new ArrayList<ReservaRelatorio>();
	private ReservaRelatorio rela;
	private Reserva_Quarto reserva_quarto= new Reserva_Quarto();
	//private List<CartaoCredito> cartao = new ArrayList<CartaoCredito>();
	//private List<Categoria> listcat = new ArrayList<Categoria>();
	private QuartoDao quartoDao = new QuartoDao();
	private List<Quarto> quartosCat = new ArrayList<Quarto>();
	private List<Ocupante> list_ocupante = new ArrayList<Ocupante>();
	private Combobox c;
	 private Datebox data_checkOut_Ocup;
	 private Datebox data_checkIn_Ocup;
	private ReservaDao reservaDao = new ReservaDao();
	private String proposito="laser";
	//Formulario de reserva 1
	private Datebox date_CheckIn, date_CheckOut;
	private Checkbox chck_Standard, chck_Executivo, chck_Apartmnt;
	private Combobox cmb_Standard, cmb_Executivo, cmb_Apartmnt, cmb_titulo;
	private Textbox txt_nome, txt_apelido,txt_end_Elct, txt_conf_end_Elct;
	private Button btn_primeiro, btn_segundo, btn_terceiro, btn_Voltar0, btn_continuar0;
	private Window janela_quartos;
	private Listbox lst_quart;
	private Grid minha_Grelha;
	private Div divLista,dadoOcupantes,divGarantiaReserva,divprimeira, divNorte,divGarantirReserva,divEfectReserva;
	
    //Formulario de reserva 2
	private Label nomeCliente, email_Cliente,lb_quarto,lb_descricacao,lb_loc;
	private Combobox cmb_pais,cmb_tipoCartao;
	private Textbox txt_celular,txt_numCartao, txt_cod_cartao;
	private Button btn_primeiro1, btn_segundo1, btn_terceiro1,btn_continuar1,btn_Voltar1,btn_semCartao;
	private Datebox date_data_Expira;
	
	//Formulario da reserva 3
	private Textbox txt_nomeOcupante, txt_pedido;
	private Radio rd_laser,rd_trabalho;
	private Button btn_Voltar2, btn_reservar0;
	private Button btn_ocupante;
	
	
	private Categoria cat;
	
	private Operacao operacao = new Operacao();
	
	
	public void onChange$data_checkIn_Ocup() {
		if ( data_checkOut_Ocup.getValue() != null && data_checkIn_Ocup.getValue() != null  ) {
			StringTokenizer st2 = new StringTokenizer(String.valueOf(data_checkIn_Ocup.getValue())," ");
	        
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
			
			
			StringTokenizer st3 = new StringTokenizer(String.valueOf(data_checkOut_Ocup.getValue())," ");
			   
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
			
			if (verificaColisao(dataBox1, dataBox2)) {
				Clients.showNotification("Quarto Indisponivel na data indicada, por favor seleccione outra!");
			}else{
				btn_ocupante.setDisabled(false);
			}
		}
	}
	
	public void onChange$data_checkOut_Ocup() {
if ( data_checkOut_Ocup.getValue() != null && data_checkIn_Ocup.getValue() != null  ) {
	
		StringTokenizer st2 = new StringTokenizer(String.valueOf(data_checkIn_Ocup.getValue())," ");
        
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
		
		
		StringTokenizer st3 = new StringTokenizer(String.valueOf(data_checkOut_Ocup.getValue())," ");
		   
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
		
		if (verificaColisao(dataBox1, dataBox2)) {
			Clients.showNotification("Quarto Indisponivel na data indicada, por favor seleccione outra!");
		}else{
			btn_ocupante.setDisabled(false);
		}
	
		}
	}
	
		
	
	

	public List<Reserva> getReservas() {
		return new ReservaDao().findAll();
	}
	
	
	
	// COLISAO PARA RESERVA
	

	public boolean verificaColisao(String dataInicial, String dataFinal) {
		boolean resultado = false;
		  List  <Ocupante>ocupantes = getOcupantes();
		  
		    int d1 = operacao.diferencaDatas("0-0-0",dataInicial, "-");
		    int d2 = operacao.diferencaDatas("0-0-0",dataFinal, "-");
		  
		  for (int i =0; i < ocupantes.size(); i++) {
		    	
		    	Ocupante ocupante = (Ocupante)ocupantes.get(i);
		    	
				StringTokenizer st1 = new StringTokenizer(String.valueOf(ocupante.getData_checkin()),"-");
				StringTokenizer st2 = new StringTokenizer(String.valueOf(ocupante.getDataCheckOut()),"-");
				
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
		  	 
		  	  if (ocupante.getQuarto().getDesignacao().equalsIgnoreCase(lb_quarto.getValue())) {
		  		 
		  	    	 if ((d1 >=dR1 && d2 <= dR2 && d1<d2) )    {
		  	    		
				          resultado = true; break;
					  }
		  	  }
				}
  
	return resultado;	
	}
	
	public void onCheck$chck_Standard(Event e){
    List <Quarto> list_quarto;
    esvaziaListBox();
   
			   for(int i=0; i< getCategorias().size(); i++){
				   cat=getCategorias().get(i);
				   if(chck_Standard.getValue().toString().equals(cat.getDesignacao())){
					 //  listcat.add(cat);
					     
					   
					   list_quarto= quartoDao.retornaQuartos(cat);
					      for(Quarto aQuarto: list_quarto){
				  
					         addQuartoListBox(aQuarto);
					         
					      }
		   
					
					   
			     
				   }
			   
			     minha_Grelha.setVisible(false);
		         divLista.setVisible(true);
		}
	}
	
	public List<Ocupante> getOcupantes () {
		return new OcupanteDao().findAll();
	}
	
public void onCheck$chck_Apartmnt(Event e){
		
	List <Quarto> list_quarto;
	esvaziaListBox();
    if(date_CheckIn.getValue().equals(null))
		
		Clients.showNotification("Preencher correctamente as datas");
    else{
			   for(int i=0; i< getCategorias().size(); i++){
				   cat=getCategorias().get(i);
				   if(chck_Apartmnt.getValue().toString().equals(cat.getDesignacao())){
					  // listcat.add(cat);
					     
					   
					   list_quarto= quartoDao.retornaQuartos(cat);
					      for(Quarto aQuarto: list_quarto){
				  
					         addQuartoListBox(aQuarto);
					         
					      }
					      
					}
				
				   }
			   
			     minha_Grelha.setVisible(false);
		         divLista.setVisible(true);
		}
		
	}
public void onCheck$chck_Executivo(Event e){
	
	List <Quarto> list_quarto;
	esvaziaListBox();
   
  
			   for(int i=0; i< getCategorias().size(); i++){
				   cat=getCategorias().get(i);
				   if(chck_Executivo.getValue().toString().equals(cat.getDesignacao())){
					   //listcat.add(cat);
					   
					   list_quarto= quartoDao.retornaQuartos(cat);
					      for(Quarto aQuarto: list_quarto){
				  
					         addQuartoListBox(aQuarto);
					         
					      }
					     
					}
				
				   
			   
			     minha_Grelha.setVisible(false);
		         divLista.setVisible(true);
		}
		
	
	
}
	public void onSelect$lst_quart(Event e) {
		
		qt=lst_quart.getSelectedItem().getValue();
		lb_quarto.setValue(qt.getDesignacao());
		lb_loc.setValue(qt.getLocalizacao());
		quartosCat.add(qt);	                          //Adiciona o quarto selecionado na lista
		for(int i=0; i<quartosCat.size(); i++)
			
		divLista.setVisible(false);
		minha_Grelha.setVisible(false);
		dadoOcupantes.setVisible(true);
		
	}
	
	public void onClick$btn_ocupante(Event e){
		
		 if(data_checkOut_Ocup.getValue().before(data_checkIn_Ocup.getValue()) || data_checkOut_Ocup.getValue().equals(data_checkIn_Ocup.getValue()))
			 Clients.showNotification("A data de Check out deve ser depois da data Check");
		 else{
		Ocupante ocupante = new Ocupante();
		//List <Ocupante> listaOcup = new ArrayList<Ocupante>();
		ocupante.setNome(txt_nomeOcupante.getText());
		ocupante.setData_checkin(data_checkIn_Ocup.getValue());
		ocupante.setProposito(proposito);
		ocupante.setPedido(txt_pedido.getText());
        ocupante.setDataCheckOut(data_checkOut_Ocup.getValue());
        
		ocupante.setQuarto(qt);
		list_ocupante.add(ocupante);                            //Adiciona o ocupante na lista de ocupantes
		dadoOcupantes.setVisible(false);
		lst_quart.removeChild(lst_quart.getSelectedItem());
		
		minha_Grelha.setVisible(false);
		divLista.setVisible(true);
		btn_reservar0.setDisabled(false);
		//reservaDao.create(reserva);
		 }
	}
	

		
	public void onClick$btn_reservar0(Event e){
		   btn_continuar0.setDisabled(false);
		   btn_Voltar0.setDisabled(false);
		   divLista.setVisible(false);
		   dadoOcupantes.setVisible(false);
		   minha_Grelha.setVisible(true);
	}
	public void onClick$btn_continuar0(Event e){
		
			    divprimeira.setVisible(false);
			    btn_primeiro.setDisabled(true);
			    btn_segundo.setDisabled(false);
			    btn_terceiro.setDisabled(true);
			    //divEfectReserva.setVisible(false);
			    divGarantirReserva.setVisible(true);
			    divGarantiaReserva.setVisible(true);
	
		
	}
	
	public void onClick$btn_continuar1(Event e){
		List<ReservaRelatorio> lista= new ArrayList<ReservaRelatorio>();
		Executar exec =new Executar();
		Pestana pestana = new Pestana();
		Reserva_QuartoDao reDao = new Reserva_QuartoDao(); 
		OcupanteDao ocupDao = new OcupanteDao();
		cliente.setApelido(txt_apelido.getText());
		cliente.setNome(txt_nome.getText());
	    cliente.setEmail(txt_conf_end_Elct.getText());
		cliente.setContacto(Long.parseLong(txt_celular.getText()));
		cliente.setPais((Pais)cmb_pais.getSelectedItem().getValue());
        cartaoCred.setId_numeroDaConta(Long.parseLong(txt_numCartao.getText()));
        cartaoCred.setTipo(cmb_tipoCartao.getText());
		cartaoCred.setDataExpiracao(date_data_Expira.getValue());
		cartaoCred.setSenha(txt_cod_cartao.getText());
		
	if (cliente.getNome().equals("") || cliente.getApelido().equals("") || cliente.getEmail().equals("")||txt_conf_end_Elct.getText().equals("")) 
			Clients.showNotification("Preencha os campos dos dados pessoais");
		
	    else if(cartaoCred.getTipo().equals(null))
			Clients.showNotification("Porfavor seleccione o tipo de cartao");
		else if(cartaoCred.getDataExpiracao().equals(null))
			Clients.showNotification("Preencha o campo data expira porfavor");
		else if(cartaoCred.getSenha().equals(null))
			Clients.showNotification("A senha do cartao e necessaria");
		else 
		{
			cartaoCred.setCliente(cliente);
			reserva.setCliente(cliente);
			clienteDao.create(cliente);
			reservaDao.create(reserva);
			cartaDao.create(cartaoCred);

			
			
			
				for(Ocupante ocup: list_ocupante){
				       rela= new ReservaRelatorio();
					   reserva_quarto.setQuarto(ocup.getQuarto());
					   reserva_quarto.setReserva(reserva);
					  // reserva_quarto.setDataCheckOut(ocup.getDataCheckOut());
					   ocup.setReserva(reserva_quarto);
					   rela.setNome(ocup.getNome());
					   rela.setId_quarto(ocup.getQuarto().getId_Quarto());
					   rela.setData_checkin(ocup.getData_checkin());
					   rela.setData_checkout(ocup.getDataCheckOut());
					   rela.setId_reserva(reserva.getId_Reserva());
					   rela.setProposito(ocup.getProposito());
					   lista.add(rela);
					   reDao.create(reserva_quarto);
					   ocupDao.create(ocup);
					   
				   }
				
			}
				
			
				try {
					exec.imprirNotas(lista);
				} catch (JRException e1) {
					
					e1.printStackTrace();
				}

		
			Clients.showNotification("Caro "+cliente.getNome()+" A sua reserva foi efetuada com sucesso\n verifique o seu email.");
			pestana.enviar(cliente.getEmail(), reserva);
			
			Executions.sendRedirect("/index.zul");
		}		

	
	   public void onSelect$cmb_pais(Event e){
		   txt_celular.setText("");
		   	Pais p;
		   for(int i=0; i< getPaises().size(); i++){
			   p= getPaises().get(i);
			   if(cmb_pais.getSelectedItem().getValue().toString().equals(p.getNome())){
		         txt_celular.setText(""+p.getPrefixo());
		         cliente.setPais(p);
		       break;
			   }
	     }   
	   }
	public void esvaziaListBox(){
		for(int inicio=0; inicio<lst_quart.getItemCount(); inicio++){
			lst_quart.removeItemAt(inicio);
		}
	}

	

   public void onClick$rd_laser(Event e){
	  
	   rd_trabalho.setSelected(false);
	   proposito="laser";
	  
   }
   public void onClick$rd_trabalho(Event e){
	  
		   rd_laser.setSelected(false);
		   proposito=rd_trabalho.getValue().toString();
	   }
  
	public String getNomeCliente(){
		return cliente.getNome();
}
	
  public List<Categoria> getCategorias(){
		return new CategoriaDao().findAll();
	}
	
private void addQuartoListBox(Quarto quarto){
	//dataBox =new Datebox();
	c = new Combobox();
	Listitem lstit = new Listitem();
	Listcell lstcl = new Listcell(String.valueOf(quarto.getId_Quarto()));
	
		lstcl = new Listcell(quarto.getDesignacao());
		lstit.appendChild(lstcl);

		lstcl = new Listcell();
		for(int i=1; i<=quarto.getOcupantes();i++){
			c.appendItem(String.valueOf(i));
		   }
		lstcl.appendChild(c);
		lstit.appendChild(lstcl);

		lstcl = new Listcell(quarto.getLocalizacao());
		lstit.appendChild(lstcl);

		lstit.setValue(quarto);
        
		lst_quart.appendChild(lstit);
		
   }
	public List<Pais> getPaises(){
		return new PaisDao().findAll();
	}
	
	public List<Quarto> getQuartos(){
		return new QuartoDao().findAll();
	}

}
