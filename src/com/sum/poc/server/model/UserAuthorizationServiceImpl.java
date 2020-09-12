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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.UserAuthorizationDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("userauthorizationService")  
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

	private static transient Logger log = Logger
			.getLogger(UserAuthorizationServiceImpl.class);
	@Autowired
	private UserAuthorizationDAO userauthorizationDAO;

	@Transactional
	public <E>UserAuthorization getUserAuthorization(E searchValue, String searchAttribute) throws Exception {
		try {

			UserAuthorization userauthorization = userauthorizationDAO.get(searchValue, searchAttribute);
			return userauthorization;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<UserAuthorization> listUserAuthorization(String start, String limit) {
		try {

			ListData<UserAuthorization> list = new ListData<UserAuthorization>();
			list.setT(userauthorizationDAO.list(start, limit));
			list.setCount(userauthorizationDAO.getUserAuthorizationCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

