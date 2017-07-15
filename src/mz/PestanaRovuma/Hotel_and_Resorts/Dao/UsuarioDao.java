package mz.PestanaRovuma.Hotel_and_Resorts.Dao;

import mz.PestanaRovuma.Hotel_and_Resorts.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UsuarioDao extends GenericDAO<Usuario>{
	
	public UsuarioDao(){
		super(Usuario.class);
	}
    
	public Usuario verificaUser(String user, String senha)   {

		Session sess = getSession();
		Transaction tx = sess.beginTransaction();
		Criteria criteria = sess.createCriteria(getClazz());
		criteria.add(Restrictions.eq("username", user));
		criteria.add(Restrictions.eq("password", senha));
		Usuario util = (Usuario)criteria.uniqueResult();
		tx.commit();

		return util;
		}
}
