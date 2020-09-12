package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.EmpDetailsViewIdDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("empdetailsviewidService")  
public class EmpDetailsViewIdServiceImpl implements EmpDetailsViewIdService {

	private static transient Logger log = Logger
			.getLogger(EmpDetailsViewIdServiceImpl.class);
	@Autowired
	private EmpDetailsViewIdDAO empdetailsviewidDAO;

	@Transactional
	public <E>EmpDetailsViewId getEmpDetailsViewId(E searchValue, String searchAttribute) throws Exception {
		try {

			EmpDetailsViewId empdetailsviewid = empdetailsviewidDAO.get(searchValue, searchAttribute);
			return empdetailsviewid;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<EmpDetailsViewId> listEmpDetailsViewId(String start, String limit) {
		try {

			ListData<EmpDetailsViewId> list = new ListData<EmpDetailsViewId>();
			list.setT(empdetailsviewidDAO.list(start, limit));
			list.setCount(empdetailsviewidDAO.getEmpDetailsViewIdCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

