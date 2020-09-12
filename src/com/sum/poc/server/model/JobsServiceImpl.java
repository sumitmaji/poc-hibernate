package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.Jobs;
import com.sum.poc.server.model.EmpDetailsView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.JobsDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("jobsService")  
public class JobsServiceImpl implements JobsService {

	private static transient Logger log = Logger
			.getLogger(JobsServiceImpl.class);
	@Autowired
	private JobsDAO jobsDAO;

	@Transactional
	public <E>Jobs getJobs(E searchValue, String searchAttribute) throws Exception {
		try {

			Jobs jobs = jobsDAO.get(searchValue, searchAttribute);
			return jobs;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Jobs> listJobs(String start, String limit) {
		try {

			ListData<Jobs> list = new ListData<Jobs>();
			list.setT(jobsDAO.list(start, limit));
			list.setCount(jobsDAO.getJobsCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

