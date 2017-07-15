package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import mz.PestanaRovuma.Hotel_and_Resorts.Dao.CategoriaDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;

public class Registar_categoria extends GenericForwardComposer {
	private Textbox txt_designacao;
	private Textbox txt_preco;
	private Button btn_car;
	private Textbox txt_desc;
	private Button btn_add;
	private CategoriaDao dao = new CategoriaDao();
	
	public void onClick$btn_add(){
		Categoria cat = new Categoria();
		//cat.setCodCategoria(00000);
		cat.setDesignacao(txt_designacao.getText());
		cat.setPreco(Float.parseFloat(txt_preco.getText()));
		cat.setDescricao(txt_desc.getText());
		dao.create(cat);
		limparCampo();
		
		Clients.showNotification("Categoria adicionada com sucesso");
	}
	
	public void limparCampo(){
		Categoria cat = new Categoria();
		//cat.setCodCategoria(00000);
		cat.setDesignacao("");
		cat.setPreco(0);
		cat.setDescricao("");
		
	}
		
		
	
    	
	
	

}
