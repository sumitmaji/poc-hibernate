package com.sum.poc.server.model;

import com.sum.poc.server.model.Countries;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sum.poc.server.model.CountriesDAO;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;

@Service("countriesService")  
public class CountriesServiceImpl implements CountriesService {

	private static transient Logger log = Logger
			.getLogger(CountriesServiceImpl.class);
	@Autowired
	private CountriesDAO countriesDAO;

	@Transactional
	public <E>Countries getCountries(E searchValue, String searchAttribute) throws Exception {
		try {

			Countries countries = countriesDAO.get(searchValue, searchAttribute);
			return countries;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<Countries> listCountries(String start, String limit) {
		try {

			ListData<Countries> list = new ListData<Countries>();
			list.setT(countriesDAO.list(start, limit));
			list.setCount(countriesDAO.getCountriesCount());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

