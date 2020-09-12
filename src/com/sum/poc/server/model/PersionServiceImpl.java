package com.sum.poc.server.model;

import com.sum.poc.server.model.Persion;
import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Locations;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.Jobs;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.PersionDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("persionService")  
public class PersionServiceImpl implements PersionService {

	private static transient Logger log = Logger
			.getLogger(PersionServiceImpl.class);
	@Autowired
	private PersionDAO persionDAO;

	@Transactional
	public <E>Persion getPersion(E searchValue, String searchAttribute) throws Exception {
		try {

			Persion persion = persionDAO.get(searchValue, searchAttribute);
			return persion;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Persion> listPersion(String start, String limit) {
		try {

			ListData<Persion> list = new ListData<Persion>();
			list.setT(persionDAO.list(start, limit));
			list.setCount(persionDAO.getPersionCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

