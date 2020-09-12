package com.sum.poc.server.model;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.model.Countries;

public interface CountriesService {
	public <E>Countries getCountries(E searchValue, String searchAttribute) throws Exception;
	public ListData<Countries> listCountries(String start, String limit) ;
}
