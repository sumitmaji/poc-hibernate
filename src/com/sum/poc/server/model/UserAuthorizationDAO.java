package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.UserAuthorization;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Locations;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.Jobs;
import com.sum.poc.server.model.Persion;
import com.sum.poc.server.model.Regions;
import com.sum.poc.server.model.JobHistory;
import com.sum.poc.server.model.UserAuthentication;
import com.sum.poc.server.model.JobHistoryId;
import com.sum.poc.server.model.EmpDetailsView;
import com.sum.poc.server.exception.BusinessException;
import java.util.List;

public interface UserAuthorizationDAO{
	public <E>UserAuthorization get(E searchValue, String searchAttribute) throws Exception;
	public List<UserAuthorization> list(String start, String limit) throws BusinessException;
	public Integer getUserAuthorizationCount() throws BusinessException;
}