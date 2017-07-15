package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;

public class GerirQuartoController extends GenericForwardComposer {
    private Combobox cbx_categoria;
    private Listbox lst_quarto;
    private Button btn_addQuarto;
    private Window win_reg_quarto;
    private Textbox txt_design;
    private Textbox txt_categoria;
    private Textbox txt_cap;
    private Textbox txt_endereco;
    private Vbox vbx_picQuarto;
    private Button btn_cancel;
    private Button btn_add_quarto;
    private QuartoDao room = new QuartoDao();
    Button b1;
    Button b2;
    private Label lb_add;
    
    
    public void onSelect$cbx_categoria(ForwardEvent e){
    	boolean t = false;
    	  try {
    			while (true) {
    				if (lst_quarto.getItemAtIndex(0) == null) {
    					 t = true; break;
    				}
    				else
    				     lst_quarto.removeItemAt(0);	     
    			}
    		    }catch(IndexOutOfBoundsException rt){
    		    	
    		    }
    	
    	  if (t) {
    	List <Quarto> li = getQuartos();
    	if (cbx_categoria.getValue().equalsIgnoreCase("todos")) {
    		for(int i=0; i<li.size(); i++) {
    			 addQuartoList(li.get(i));
    		}
    		btn_addQuarto.setVisible(false);
    		lb_add.setVisible(false);
    	} else {
    	
    	for(int i=0; i<li.size(); i++) {
    		if(((Quarto)(li.get(i))).getCategoria().getDesignacao().equalsIgnoreCase(cbx_categoria.getValue())) {
    		  addQuartoList(li.get(i));
    	    }
    	}
    	
    	btn_addQuarto.setVisible(true);	
    	lb_add.setVisible(true);
    	}
    	}
    	}	
	
	
       
      public void onClick$btn_addQuarto(ForwardEvent e){
    	  win_reg_quarto.setVisible(true);
    	  win_reg_quarto.doHighlighted();
    	  
      }
    
      public void onClick$btn_add_quarto(ForwardEvent e){
    	  Quarto quarto = new Quarto();
	  		quarto.setDesignacao(txt_design.getText());
	  		quarto.setCategoria((Categoria)cbx_categoria.getSelectedItem().getValue());
	  		quarto.setOcupantes(Integer.parseInt(txt_cap.getValue()));
	  		quarto.setLocalizacao(txt_endereco.getText());
	  		// quarto.setImagem(vbx_picQuarto.get);
	  		room.create(quarto);
	  		addQuartoList(quarto);
	  		Clients.showNotification("Registo efectuado com sucesso");
	  		win_reg_quarto.detach(); 
      }
      
      public void onClick$btn_cancel(ForwardEvent e){
    	  win_reg_quarto.setVisible(false);
    	
      }
      
      private void addQuartoList(Quarto quarto){
  		Listitem lstit = new Listitem();
  		
  		Listcell lstcl = new Listcell(String.valueOf(quarto.getId_Quarto()));
  		lstit.appendChild(lstcl);
  		
  	    lstcl = new Listcell(quarto.getDesignacao());
  		lstit.appendChild(lstcl);

  		lstcl = new Listcell(quarto.getCategoria().getDesignacao());
  		lstit.appendChild(lstcl);
  		
  		lstcl = new Listcell(quarto.getLocalizacao());
  		lstit.appendChild(lstcl);

  		lstcl = new Listcell(String.valueOf(quarto.getOcupantes()));
  		lstit.appendChild(lstcl);
  		
  		
  		
  		lstcl = new Listcell();
  		b1 = new Button();
  		b1.setImage("/widgets/Imagens/actualizar.png");
  		b1.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				 Listitem li = ((Listitem)b1.getParent().getParent());
				 li.setSelected(true);
				 Quarto quarto = (Quarto) li.getValue();
			      Map<String, Quarto> h = new HashMap<>();
			      h.put("1",quarto);
			      Executions.createComponents("/adicionarQuarto.zul", null, h);	
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
					 Quarto quarto = (Quarto) li.getValue();
					 Map<String, Quarto> h1 = new HashMap<>();	
					 h1.put("serv",li.getValue());
					 Executions.createComponents("/confirmacaoQuarto.zul", null, h1);
					 
				}
			});
  		lstcl.appendChild(b2);
  		lstit.appendChild(lstcl);		

  		
  		lstit.setValue(quarto);
  		lst_quarto.appendChild(lstit);
  		
  		lstit.addForward("onClick","", "onClick$linha",quarto);
  		
  	}
      

 	 public void onClick$celula_a(ForwardEvent e) {
 		 
 		 Button b = (Button)e.getOrigin().getTarget();
 		 Listitem li = ((Listitem)b.getParent().getParent());
 		 li.setSelected(true);	
 	      Quarto servt=(Quarto) li.getValue();
 	      Map<String, Quarto> h = new HashMap<>();
 	      h.put("1", servt);
 	      Executions.createComponents("adicionarQuarto.zul", null, h);		
 	      
 	 }
 	
 	 public void onClick$celula_r(ForwardEvent e) {

 		 Button b = (Button) e.getOrigin().getTarget();
 		 Listitem li = ((Listitem)b.getParent().getParent());
 		 li.setSelected(true);
 		 Map<String, Quarto> h1 = new HashMap<>();	
 		 h1.put("serv",li.getValue());
 		 Executions.createComponents("confirmacaoQuarto.zul", null, h1);
 	  }
      
      public void clearField(){
		   txt_design.setText(" ");
		   txt_categoria.setText(" ");
		   txt_cap.setValue(" ");
		   txt_endereco.setText(" ");
	  }
      
      public List<Quarto> getQuartos(){
  		return new QuartoDao().findAll();
  	  }
      
      public List<Categoria> getCategorias(){
    	return new CategoriaDao().findAll();
      }
  	
}
