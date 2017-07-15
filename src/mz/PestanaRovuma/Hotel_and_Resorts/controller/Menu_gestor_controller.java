package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;

public class Menu_gestor_controller extends GenericForwardComposer {
	private Menuitem mnut_corpo;
	private Menuitem mnut_servicos;
	private Menuitem mnut_promocao;
	private Menuitem mnut_categoria;
	private Menuitem mnut_quarto;
	private Menuitem mnut_estatistica;
	private Menuitem mnut_publicArt;
	private Menuitem mnut_comentario;
	private Include inc_corpo;
	private Include inc_gerirServico;
	private Include inc_gerirPromocao;
	private Include inc_gerirQuarto;
	private Include inc_publicaArtigo;
	private Include inc_lstcomentario;
	private Include inc_estatistica;
	private Include inc_gerircategoria;

	
	
	public void onClick$mnut_servicos(Event e){
		inc_corpo.setVisible(false);
		inc_lstcomentario.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_gerirQuarto.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_estatistica.setVisible(false);
		inc_gerircategoria.setVisible(false);
		inc_gerirServico.setVisible(true);
	}
	
	public void onClick$mnut_promocao(Event e){
		inc_corpo.setVisible(false);
		inc_lstcomentario.setVisible(false);
		inc_gerirQuarto.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_estatistica.setVisible(false);
		inc_gerircategoria.setVisible(false);
		inc_gerirServico.setVisible(false);
		inc_gerirPromocao.setVisible(true);
	}
	
	public void onClick$mnut_corpo(Event e){
		inc_lstcomentario.setVisible(false);
		inc_gerirQuarto.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_estatistica.setVisible(false);
		inc_gerirServico.setVisible(false);
		inc_gerircategoria.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_corpo.setVisible(true);
	}
	
	public void onClick$mnut_comentario(Event e){
		inc_corpo.setVisible(false);
		inc_gerirQuarto.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_estatistica.setVisible(false);
		inc_gerirServico.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_lstcomentario.setVisible(true);
	}
	
	public void onClick$mnut_publicArtigo(Event e){
		inc_corpo.setVisible(false);
		inc_lstcomentario.setVisible(false);
		inc_gerirQuarto.setVisible(false);		
		inc_estatistica.setVisible(false);
		inc_gerirServico.setVisible(false);
		inc_gerircategoria.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_publicaArtigo.setVisible(true);
	}
	
	public void onClick$mnut_quarto(Event e){
		inc_corpo.setVisible(false);
		inc_lstcomentario.setVisible(false);		
		inc_estatistica.setVisible(false);
		inc_gerirServico.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_gerircategoria.setVisible(false);
		inc_gerirQuarto.setVisible(true);	
	}
	
	public void onClick$mnut_estatistica(Event e){
		inc_corpo.setVisible(false);
		inc_lstcomentario.setVisible(false);		
		inc_gerirServico.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_gerirQuarto.setVisible(false);
		inc_gerircategoria.setVisible(false);
		inc_estatistica.setVisible(true);
	}
	
	public void onClick$mnut_categoria(Event e){
		inc_corpo.setVisible(false);
		inc_lstcomentario.setVisible(false);		
		inc_gerirServico.setVisible(false);
		inc_gerirPromocao.setVisible(false);
		inc_publicaArtigo.setVisible(false);
		inc_gerirQuarto.setVisible(false);
		inc_estatistica.setVisible(false);
		inc_gerircategoria.setVisible(true);
	}
	
	

}
