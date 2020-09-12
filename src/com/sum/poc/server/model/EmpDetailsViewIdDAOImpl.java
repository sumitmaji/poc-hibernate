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
import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

@Repository
public class EmpDetailsViewIdDAOImpl implements EmpDetailsViewIdDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static transient Logger log = Logger.getLogger(EmpDetailsViewIdDAOImpl.class); 
	public <E>EmpDetailsViewId get(E searchValue, String searchAttribute) throws BusinessException{
		EmpDetailsViewId empdetailsviewid = null;

		try {
			Criteria empdetailsviewidCrit = sessionFactory.getCurrentSession().createCriteria(EmpDetailsViewId.class);
			empdetailsviewidCrit.add(Restrictions.eq(searchAttribute, searchValue));
			empdetailsviewid = (EmpDetailsViewId) empdetailsviewidCrit.uniqueResult();
			return empdetailsviewid;
		} catch (HibernateException ex) {
			throw new BusinessException("Could not get Country Data");
		}
	}
	
	public List<EmpDetailsViewId> list(String start, String limit) throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					EmpDetailsViewId.class);
			if (start != null) {
				crit.setFirstResult(Integer.parseInt(start));
			}
			if (limit != null) {
				crit.setMaxResults(Integer.parseInt(limit));
			}

			@SuppressWarnings("unchecked")
			List<EmpDetailsViewId> list = crit.list();
			return list;
		} catch (HibernateException ex) {
			throw new BusinessException("Data no fetched");
		}
	}

	public Integer getEmpDetailsViewIdCount() throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					EmpDetailsViewId.class);
			crit.setProjection(Projections.rowCount());
			return (Integer) crit.uniqueResult();

		} catch (HibernateException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}
