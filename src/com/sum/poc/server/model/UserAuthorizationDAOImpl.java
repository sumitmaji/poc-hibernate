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
import com.sum.poc.server.model.UserAuthorization;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Locations;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.Jobs;
import com.sum.poc.server.model.Persion;
import com.sum.poc.server.model.Regions;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.UserAuthentication;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.EmpDetailsView;

@Repository
public class UserAuthorizationDAOImpl implements UserAuthorizationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static transient Logger log = Logger.getLogger(UserAuthorizationDAOImpl.class); 
	public <E>UserAuthorization get(E searchValue, String searchAttribute) throws BusinessException{
		UserAuthorization userauthorization = null;

		try {
			Criteria userauthorizationCrit = sessionFactory.getCurrentSession().createCriteria(UserAuthorization.class);
			userauthorizationCrit.add(Restrictions.eq(searchAttribute, searchValue));
			userauthorization = (UserAuthorization) userauthorizationCrit.uniqueResult();
			return userauthorization;
		} catch (HibernateException ex) {
			throw new BusinessException("Could not get Country Data");
		}
	}
	
	public List<UserAuthorization> list(String start, String limit) throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					UserAuthorization.class);
			if (start != null) {
				crit.setFirstResult(Integer.parseInt(start));
			}
			if (limit != null) {
				crit.setMaxResults(Integer.parseInt(limit));
			}

			@SuppressWarnings("unchecked")
			List<UserAuthorization> list = crit.list();
			return list;
		} catch (HibernateException ex) {
			throw new BusinessException("Data no fetched");
		}
	}

	public Integer getUserAuthorizationCount() throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					UserAuthorization.class);
			crit.setProjection(Projections.rowCount());
			return (Integer) crit.uniqueResult();

		} catch (HibernateException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}
