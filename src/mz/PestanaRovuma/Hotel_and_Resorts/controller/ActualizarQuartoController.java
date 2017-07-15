package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.QuartoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.SubpaginasDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Subpaginas;

public class ActualizarQuartoController extends GenericForwardComposer{
    private Window win_act_quarto;
    private Textbox txt_design;
    private Textbox txt_cap;
    private Textbox txt_endereco;
    private Button btn_cancel;
    private Button btn_act_quarto;
    private Comboitem combo;
    
    
    public void onClick$btn_cancel(ForwardEvent e){
    	 win_act_quarto.onClose();
    }
    
    
    public void onClick$btn_act_quarto(ForwardEvent e){
    	Quarto quarto = (Quarto)combo.getValue();
	    quarto.setDesignacao(txt_design.getText());
		quarto.setOcupantes(Integer.parseInt(txt_cap.getText()));
		quarto.setLocalizacao(txt_endereco.getText());
		 List <Subpaginas>li = new SubpaginasDao().findAll();
		   	Subpaginas sub = new Subpaginas();
		    sub = li.get(0);
		    sub.setAreaGestor("q");
		    new SubpaginasDao().update(sub);
		
	    win_act_quarto.onClose();
	    new QuartoDao().update(quarto);
	    Executions.sendRedirect("/areaGestor.zul");
	    Clients.showNotification("Quarto Actualizado com sucesso!");
    }
    
}
