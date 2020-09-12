package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.EmployeesDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("employeesService")  
public class EmployeesServiceImpl implements EmployeesService {

	private static transient Logger log = Logger
			.getLogger(EmployeesServiceImpl.class);
	@Autowired
	private EmployeesDAO employeesDAO;

	@Transactional
	public <E>Employees getEmployees(E searchValue, String searchAttribute) throws Exception {
		try {

			Employees employees = employeesDAO.get(searchValue, searchAttribute);
			return employees;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Employees> listEmployees(String start, String limit) {
		try {

			ListData<Employees> list = new ListData<Employees>();
			list.setT(employeesDAO.list(start, limit));
			list.setCount(employeesDAO.getEmployeesCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

