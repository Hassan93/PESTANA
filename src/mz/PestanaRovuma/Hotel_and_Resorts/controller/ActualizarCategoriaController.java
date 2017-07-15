package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ActualizarCategoriaController extends GenericForwardComposer {
	 private Comboitem combo;
	 private Button btn_add;
     private Listbox lista_categoria;
     private Window win_act_categoria;
     private Textbox txt_design;
     private Textbox txt_preco;
     private Textbox txt_quant_quarto;
     private Textbox txt_desc;
     private Button btn_cancel;
     private Button btn_add_cat;
     
	 public void onClick$btn_cancel(){
		 win_act_categoria.onClose();
}
 
  public void onClick$btn_add_cat(ForwardEvent e) {
	  
	  Categoria categoria = (Categoria)combo.getValue();
	
	    categoria.setDesignacao(txt_design.getText());
		categoria.setPreco(Float.parseFloat(txt_preco.getText()));
		categoria.setDescricao(txt_desc.getText());
		
		 List <Subpaginas>li = new SubpaginasDao().findAll();
		   	Subpaginas sub = new Subpaginas();
		    sub = li.get(0);
		    sub.setAreaGestor("c");
		    new SubpaginasDao().update(sub);
		win_act_categoria.onClose();
	    new CategoriaDao().update(categoria);
	    Executions.sendRedirect("/areaGestor.zul");
	    
 }
  

}
