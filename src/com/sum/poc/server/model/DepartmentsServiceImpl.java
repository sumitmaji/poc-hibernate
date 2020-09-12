package com.sum.poc.server.model;

import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.DepartmentsDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("departmentsService")  
public class DepartmentsServiceImpl implements DepartmentsService {

	private static transient Logger log = Logger
			.getLogger(DepartmentsServiceImpl.class);
	@Autowired
	private DepartmentsDAO departmentsDAO;

	@Transactional
	public <E>Departments getDepartments(E searchValue, String searchAttribute) throws Exception {
		try {

			Departments departments = departmentsDAO.get(searchValue, searchAttribute);
			return departments;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Departments> listDepartments(String start, String limit) {
		try {

			ListData<Departments> list = new ListData<Departments>();
			list.setT(departmentsDAO.list(start, limit));
			list.setCount(departmentsDAO.getDepartmentsCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

