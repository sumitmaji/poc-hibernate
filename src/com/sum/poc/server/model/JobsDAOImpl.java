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
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.Jobs;
import com.sum.poc.server.model.EmpDetailsView;

@Repository
public class JobsDAOImpl implements JobsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static transient Logger log = Logger.getLogger(JobsDAOImpl.class); 
	public <E>Jobs get(E searchValue, String searchAttribute) throws BusinessException{
		Jobs jobs = null;

		try {
			Criteria jobsCrit = sessionFactory.getCurrentSession().createCriteria(Jobs.class);
			jobsCrit.add(Restrictions.eq(searchAttribute, searchValue));
			jobs = (Jobs) jobsCrit.uniqueResult();
			return jobs;
		} catch (HibernateException ex) {
			throw new BusinessException("Could not get Country Data");
		}
	}
	
	public List<Jobs> list(String start, String limit) throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					Jobs.class);
			if (start != null) {
				crit.setFirstResult(Integer.parseInt(start));
			}
			if (limit != null) {
				crit.setMaxResults(Integer.parseInt(limit));
			}

			@SuppressWarnings("unchecked")
			List<Jobs> list = crit.list();
			return list;
		} catch (HibernateException ex) {
			throw new BusinessException("Data no fetched");
		}
	}

	public Integer getJobsCount() throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					Jobs.class);
			crit.setProjection(Projections.rowCount());
			return (Integer) crit.uniqueResult();

		} catch (HibernateException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}
