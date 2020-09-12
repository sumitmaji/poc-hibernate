package com.sum.poc.server.model;

import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.EmpDetailsViewDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("empdetailsviewService")  
public class EmpDetailsViewServiceImpl implements EmpDetailsViewService {

	private static transient Logger log = Logger
			.getLogger(EmpDetailsViewServiceImpl.class);
	@Autowired
	private EmpDetailsViewDAO empdetailsviewDAO;

	@Transactional
	public <E>EmpDetailsView getEmpDetailsView(E searchValue, String searchAttribute) throws Exception {
		try {

			EmpDetailsView empdetailsview = empdetailsviewDAO.get(searchValue, searchAttribute);
			return empdetailsview;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<EmpDetailsView> listEmpDetailsView(String start, String limit) {
		try {

			ListData<EmpDetailsView> list = new ListData<EmpDetailsView>();
			list.setT(empdetailsviewDAO.list(start, limit));
			list.setCount(empdetailsviewDAO.getEmpDetailsViewCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

