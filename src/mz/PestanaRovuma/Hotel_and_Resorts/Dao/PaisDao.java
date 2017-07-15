package mz.PestanaRovuma.Hotel_and_Resorts.Dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import mz.PestanaRovuma.Hotel_and_Resorts.model.Pais;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class PaisDao extends GenericDAO<Pais> {
	
   public PaisDao(){
	   super(Pais.class);
   }

}
