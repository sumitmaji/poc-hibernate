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
import com.sum.poc.server.exception.BusinessException;
import java.util.List;

public interface PersionDAO{
	public <E>Persion get(E searchValue, String searchAttribute) throws Exception;
	public List<Persion> list(String start, String limit) throws BusinessException;
	public Integer getPersionCount() throws BusinessException;
}