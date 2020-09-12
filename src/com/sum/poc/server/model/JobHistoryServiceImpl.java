package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.JobHistoryDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("jobhistoryService")  
public class JobHistoryServiceImpl implements JobHistoryService {

	private static transient Logger log = Logger
			.getLogger(JobHistoryServiceImpl.class);
	@Autowired
	private JobHistoryDAO jobhistoryDAO;

	@Transactional
	public <E>JobHistory getJobHistory(E searchValue, String searchAttribute) throws Exception {
		try {

			JobHistory jobhistory = jobhistoryDAO.get(searchValue, searchAttribute);
			return jobhistory;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<JobHistory> listJobHistory(String start, String limit) {
		try {

			ListData<JobHistory> list = new ListData<JobHistory>();
			list.setT(jobhistoryDAO.list(start, limit));
			list.setCount(jobhistoryDAO.getJobHistoryCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

