package ${package_name};

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ${root_package_name}.server.exception.BusinessException;
import java.util.List;
import org.hibernate.criterion.Projections;
<#list importList as import>
import ${import};
</#list>

@Repository
public class ${entityName}DAOImpl implements ${entityName}DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static transient Logger log = Logger.getLogger(${entityName}DAOImpl.class); 
	public <E>${entityName} get(E searchValue, String searchAttribute) throws BusinessException{
		${entityName} ${entity_variable} = null;

		try {
			Criteria ${entity_variable}Crit = sessionFactory.getCurrentSession().createCriteria(${entityName}.class);
			${entity_variable}Crit.add(Restrictions.eq(searchAttribute, searchValue));
			${entity_variable} = (${entityName}) ${entity_variable}Crit.uniqueResult();
			return ${entity_variable};
		} catch (HibernateException ex) {
			throw new BusinessException("Could not get Country Data");
		}
	}
	
	public List<${entityName}> list(String start, String limit) throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					${entityName}.class);
			if (start != null) {
				crit.setFirstResult(Integer.parseInt(start));
			}
			if (limit != null) {
				crit.setMaxResults(Integer.parseInt(limit));
			}

			@SuppressWarnings("unchecked")
			List<${entityName}> list = crit.list();
			return list;
		} catch (HibernateException ex) {
			throw new BusinessException("Data no fetched");
		}
	}

	public Integer get${entityName}Count() throws BusinessException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(
					${entityName}.class);
			crit.setProjection(Projections.rowCount());
			return (Integer) crit.uniqueResult();

		} catch (HibernateException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}
