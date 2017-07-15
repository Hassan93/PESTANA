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

public class Barner_direito_controller extends GenericForwardComposer {
	private Button btn_login, btn_login2;
	private Window win_login;
	private Textbox txt_user;
	private Textbox txt_password;
	private UsuarioDao dao = new UsuarioDao();
	
	
	public void onClick$btn_login(){
		win_login.setVisible(true);
		win_login.doHighlighted();
	}
	
	public void onClick$btn_login2(){
		
		Usuario util = dao.verificaUser(txt_user.getText(), txt_password.getText());
		
	    	if(util != null){
			Executions.getCurrent().getDesktop().getSession().setAttribute("UTIL", util);
			if(util.getPerfil().equals("administrador")){
			
			Executions.sendRedirect("/areaGestor.zul");
			btn_login.setLabel("lOg out");
			}
			else if (util.getPerfil().equals("recepcionista")){
				
				Executions.sendRedirect("/areaRecepcionista.zul");
				btn_login.setLabel("Log Out");				
			}
			
			else
				Clients.showNotification("Dados invalidos");	
		}
	}
    
	
	public List<Usuario> getUsuarios(){
	   return new UsuarioDao().findAll();
	}
	
}
