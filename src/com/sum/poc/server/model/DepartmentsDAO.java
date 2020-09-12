package com.sum.poc.server.model;

import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.exception.BusinessException;
import java.util.List;

public interface DepartmentsDAO{
	public <E>Departments get(E searchValue, String searchAttribute) throws Exception;
	public List<Departments> list(String start, String limit) throws BusinessException;
	public Integer getDepartmentsCount() throws BusinessException;
}