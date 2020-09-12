package com.sum.poc.server.model;

import com.sum.poc.server.model.Countries;
import com.sum.poc.server.exception.BusinessException;
import java.util.List;

public interface CountriesDAO{
	public <E>Countries get(E searchValue, String searchAttribute) throws Exception;
	public List<Countries> list(String start, String limit) throws BusinessException;
	public Integer getCountriesCount() throws BusinessException;
}