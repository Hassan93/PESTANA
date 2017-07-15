package mz.PestanaRovuma.Hotel_and_Resorts.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mz.PestanaRovuma.Hotel_and_Resorts.model.Categoria;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Quarto;

public class QuartoDao extends GenericDAO<Quarto>{

	public QuartoDao(){
		super(Quarto.class);
	}
	
	public List<Quarto> retornaQuartos(Categoria cat){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Quarto where id_Categoria = ?");
		query.setParameter(0, cat.getId_Categoria());
		List list = query.list();
		tx.commit();
		return list;
	}
}
