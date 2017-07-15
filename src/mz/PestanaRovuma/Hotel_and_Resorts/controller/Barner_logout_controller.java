package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.PromocaoDao;
import mz.PestanaRovuma.Hotel_and_Resorts.Dao.UsuarioDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Promocao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Usuario;

public class Barner_logout_controller extends GenericForwardComposer {
	private Button btn_logout;
	private Window win_login;
	
	
	public void onClick$btn_logout(){
		
			Executions.getCurrent().getDesktop().getSession().invalidate();
			Executions.sendRedirect("/index.zul");  	 
		
	}
    
	
	
	
}
