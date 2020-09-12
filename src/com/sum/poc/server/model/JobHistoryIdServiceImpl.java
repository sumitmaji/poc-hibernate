package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.JobHistoryIdDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("jobhistoryidService")  
public class JobHistoryIdServiceImpl implements JobHistoryIdService {

	private static transient Logger log = Logger
			.getLogger(JobHistoryIdServiceImpl.class);
	@Autowired
	private JobHistoryIdDAO jobhistoryidDAO;

	@Transactional
	public <E>JobHistoryId getJobHistoryId(E searchValue, String searchAttribute) throws Exception {
		try {

			JobHistoryId jobhistoryid = jobhistoryidDAO.get(searchValue, searchAttribute);
			return jobhistoryid;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<JobHistoryId> listJobHistoryId(String start, String limit) {
		try {

			ListData<JobHistoryId> list = new ListData<JobHistoryId>();
			list.setT(jobhistoryidDAO.list(start, limit));
			list.setCount(jobhistoryidDAO.getJobHistoryIdCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

