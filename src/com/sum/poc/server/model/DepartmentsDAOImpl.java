package com.sum.poc.server.model;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sum.poc.server.exception.BusinessException;
import java.util.List;
import org.hibernate.criterion.Projections;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;

@Repository
public class DepartmentsDAOImpl implements DepartmentsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static transient Logger log = Logger.getLogger(DepartmentsDAOImpl.class); 
	public <E>Departments get(E searchValue, String searchAttribute) throws BusinessException{
		Departments departments = null;

		try {
			Criteria departmentsCrit = sessionFactory.getCurrentSession().createCriteria(Departments.class);
			departmentsCrit.add(Restrictions.eq(searchAttribute, searchValue));
			departments = (Departments) departmentsCrit.uniqueResult();
			return departments;
		} catch (HibernateException ex) {
			throw new BusinessException("Could not get Country Data");
		}
	}
	
	public List<Departments> list(String start, String limit) throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					Departments.class);
			if (start != null) {
				crit.setFirstResult(Integer.parseInt(start));
			}
			if (limit != null) {
				crit.setMaxResults(Integer.parseInt(limit));
			}

			@SuppressWarnings("unchecked")
			List<Departments> list = crit.list();
			return list;
		} catch (HibernateException ex) {
			throw new BusinessException("Data no fetched");
		}
	}

	public Integer getDepartmentsCount() throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					Departments.class);
			crit.setProjection(Projections.rowCount());
			return (Integer) crit.uniqueResult();

		} catch (HibernateException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}
