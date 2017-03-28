package com.infotel.gg.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.infotel.gg.dao.CustomerDAO;
import com.infotel.gg.exception.DAOException;
import com.infotel.gg.exception.ModelException;

import com.infotel.gg.model.Customer;

public class CustomerDAOHbn extends DAOHbn implements CustomerDAO{

	@Override
	public void create(Customer obj) throws DAOException {
		try {
			getSession().save(obj);
		} catch (Throwable t) {
			throw new DAOException("Impossible de creer l'element",t);
		}
		
	}

	@Override
	public Customer read(String i) throws ModelException {
		try {
			return getSession().find(Customer.class, i);
		} catch (Throwable t) {
			throw new DAOException("Impossible de lire l'element",t);
		}
	}

	@Override
	public void update(Customer obj) {
		try {
			getSession().saveOrUpdate(obj);
		} catch (Throwable t) {
			throw new DAOException("Impossible de mettre a jour l'element",t);
		}
		
	}

	@Override
	public void delete(Customer obj) throws DAOException {
		try {
			getSession().delete(obj);
		} catch (Throwable t) {
			throw new DAOException("Impossible de supprimer l'element",t);
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Customer> listAll() {
		List<Customer> result = null;
		String request = "SELECT cust FROM Customer cust";
		Session session = getSession();
		Query q = session.createQuery(request);	
		result = q.getResultList();
		return result;
	}

}