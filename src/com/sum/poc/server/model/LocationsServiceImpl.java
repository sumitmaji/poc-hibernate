package com.sum.poc.server.model;

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
import com.sum.poc.server.model.LocationsDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("locationsService")  
public class LocationsServiceImpl implements LocationsService {

	private static transient Logger log = Logger
			.getLogger(LocationsServiceImpl.class);
	@Autowired
	private LocationsDAO locationsDAO;

	@Transactional
	public <E>Locations getLocations(E searchValue, String searchAttribute) throws Exception {
		try {

			Locations locations = locationsDAO.get(searchValue, searchAttribute);
			return locations;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Locations> listLocations(String start, String limit) {
		try {

			ListData<Locations> list = new ListData<Locations>();
			list.setT(locationsDAO.list(start, limit));
			list.setCount(locationsDAO.getLocationsCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

