package com.sum.poc.server.model;

import com.sum.poc.server.model.Persion;
import com.sum.poc.server.model.Regions;
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
import com.sum.poc.server.model.RegionsDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("regionsService")  
public class RegionsServiceImpl implements RegionsService {

	private static transient Logger log = Logger
			.getLogger(RegionsServiceImpl.class);
	@Autowired
	private RegionsDAO regionsDAO;

	@Transactional
	public <E>Regions getRegions(E searchValue, String searchAttribute) throws Exception {
		try {

			Regions regions = regionsDAO.get(searchValue, searchAttribute);
			return regions;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Regions> listRegions(String start, String limit) {
		try {

			ListData<Regions> list = new ListData<Regions>();
			list.setT(regionsDAO.list(start, limit));
			list.setCount(regionsDAO.getRegionsCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

