package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;

		public class Registar_quarto_controller {
		private Combobox cbx_categoria;
		   private Window win_reg_quarto;
		   private Textbox txt_design, txt_desc, txt_categoria,txt_ocupantes, txt_endereco;
		   private Button btn_add_quarto,btn_cancelQ;
		   private Listbox lst_quarto;
		   private QuartoDao room = new QuartoDao();
		   private CategoriaDao category = new CategoriaDao();
		   private Button edit, elim;
		   
		   
		   public void onClick$btn_add_quarto(Event e){
					Quarto quarto = new Quarto();
					quarto.setDesignacao(txt_design.getText());
					quarto.setCategoria((Categoria)cbx_categoria.getSelectedItem().getValue());
					quarto.setOcupantes(Integer.parseInt(txt_ocupantes.getValue()));
					quarto.setLocalizacao(txt_endereco.getText());
					room.create(quarto);
					addQuartoList(quarto);
					limparCampos();
					Clients.showNotification("Registo efectuado com sucesso");
					win_reg_quarto.detach(); 
			   
		   }
		   
		   public void limparCampos(){
			   txt_design.setText(" ");
			   txt_categoria.setText(" ");
			   txt_ocupantes.setValue(" ");
			   txt_endereco.setText(" ");
			   
		}
		   
		  
		
		   private void addQuartoList(Quarto quarto){
				Listitem lstit = new Listitem();
				Listcell lstcl = new Listcell(quarto.getDesignacao());
				lstit.appendChild(lstcl);

				lstcl = new Listcell(quarto.getCategoria().toString());
				lstit.appendChild(lstcl);

				lstcl = new Listcell(String.valueOf(quarto.getOcupantes()));
				lstit.appendChild(lstcl);

				lstcl = new Listcell(quarto.getLocalizacao());
				lstit.appendChild(lstcl);		
				
				lstit.setValue(quarto);
				lst_quarto.appendChild(lstit);
			
				
				lstit.addForward("onClick","", "onClick$linha",quarto);
				
			}
		   
		   
		}
