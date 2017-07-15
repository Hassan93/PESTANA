package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;

public class GerirCategoriaController extends GenericForwardComposer{
     private Button btn_add;
     private Image pics;
     private Listbox lista_categoria;
     private Window win_reg_categoria;
     private Textbox txt_design;
     private Textbox txt_preco;
     private Textbox txt_desc;
     private Button btn_cancel;
     private Button btn_add_cat;
     private CategoriaDao category = new CategoriaDao();
     Button b2; 
 	 Button b1;
     
     public void onClick$btn_add(ForwardEvent e){
    	 win_reg_categoria.setVisible(true);
    	 win_reg_categoria.doHighlighted();
     }
     
     public void onClick$btn_cancel(ForwardEvent e){
    	 win_reg_categoria.setVisible(false);
     }
     
     public void  onClick$btn_add_cat(Event e){
    	 Categoria cat = new Categoria();
		 cat.setDesignacao(txt_design.getText());
		 cat.setDescricao(txt_desc.getText());
		 cat.setPreco(Double.parseDouble(txt_preco.getValue()));
         category.create(cat);
         addCategoriaList(cat);
         clear();
         Clients.showNotification("Registo efectuado com sucesso");	
         win_reg_categoria.setVisible(false);
         
     }
     
     private void addCategoriaList(Categoria categoria){
 		Listitem lstit = new Listitem();
 		Listcell lstcl = new Listcell(String.valueOf(categoria.getId_Categoria()));
 		lstit.appendChild(lstcl);
 		
 		lstcl = new Listcell(categoria.getDesignacao());
 		lstit.appendChild(lstcl);

 		lstcl = new Listcell(String.valueOf(categoria.getPreco()));
 		lstit.appendChild(lstcl);

 		lstcl = new Listcell(categoria.getDescricao());
 		lstit.appendChild(lstcl);		
 		
 		lstit.setValue(categoria);
 		lista_categoria.appendChild(lstit);
 		
 		lstcl = new Listcell();
		b1 = new Button();
		b1.setImage("/widgets/Imagens/actualizar.png");	
		b1.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				 Listitem li = ((Listitem)b1.getParent().getParent());
				 li.setSelected(true);
				 Categoria cat = (Categoria) li.getValue();
			      Map<String, Categoria> h = new HashMap<>();
			      h.put("1", cat);
			      Executions.createComponents("/actualizarCategoria.zul", null, h);	
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
					 Categoria cat = (Categoria) li.getValue();
					 Map<String, Categoria> h1 = new HashMap<>();	
					 h1.put("serv",li.getValue());
					 Executions.createComponents("/eliminarCategoria.zul", null, h1);
					 
				}
			});
		lstcl.appendChild(b2);
		lstit.appendChild(lstcl);
 		
 	}
     
     public void onClick$celula_a(ForwardEvent e) {
		 
		 Button b = (Button)e.getOrigin().getTarget();
		 Listitem li = ((Listitem)b.getParent().getParent());
		 li.setSelected(true);	
	      Categoria servt=(Categoria) li.getValue();
	      Map<String, Categoria> h = new HashMap<>();
	      h.put("1", servt);
	      Executions.createComponents("/actualizarCategoria.zul", null, h);		
	      
	 }
	
	 public void onClick$celula_r(ForwardEvent e) {

		 
		 Button b = (Button) e.getOrigin().getTarget();
		 Listitem li = ((Listitem)b.getParent().getParent());
		 li.setSelected(true);
		 Map<String, Categoria> h1 = new HashMap<>();	
		 Categoria cat = (Categoria) li.getValue();
		 h1.put("serv",cat);
		 Executions.createComponents("/eliminarCategoria.zul", null, h1);
		 
	 }
     
     public void clear(){
 		txt_design.setText(" ");
 		txt_desc.setText(" ");
 		txt_preco.setValue(" ");
 			
 	}
     
    public List<Categoria> getCategorias(){
    	return new CategoriaDao().findAll();
    }
}
