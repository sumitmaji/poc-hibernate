package com.sum.poc.server.model;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

public interface JobHistoryIdService {
	public <E>JobHistoryId getJobHistoryId(E searchValue, String searchAttribute) throws Exception;
	public ListData<JobHistoryId> listJobHistoryId(String start, String limit) ;
}
