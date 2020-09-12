package com.sum.poc.server.model;

import com.sum.poc.server.model.Persion;
import com.sum.poc.server.model.Regions;
import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.UserAuthentication;
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
import com.sum.poc.server.model.UserAuthenticationDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("userauthenticationService")  
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private static transient Logger log = Logger
			.getLogger(UserAuthenticationServiceImpl.class);
	@Autowired
	private UserAuthenticationDAO userauthenticationDAO;

	@Transactional
	public <E>UserAuthentication getUserAuthentication(E searchValue, String searchAttribute) throws Exception {
		try {

			UserAuthentication userauthentication = userauthenticationDAO.get(searchValue, searchAttribute);
			return userauthentication;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<UserAuthentication> listUserAuthentication(String start, String limit) {
		try {

			ListData<UserAuthentication> list = new ListData<UserAuthentication>();
			list.setT(userauthenticationDAO.list(start, limit));
			list.setCount(userauthenticationDAO.getUserAuthenticationCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

