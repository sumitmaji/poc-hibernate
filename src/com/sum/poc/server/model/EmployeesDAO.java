package com.sum.poc.server.model;

import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;
import com.sum.poc.server.exception.BusinessException;
import java.util.List;

public interface EmployeesDAO{
	public <E>Employees get(E searchValue, String searchAttribute) throws Exception;
	public List<Employees> list(String start, String limit) throws BusinessException;
	public Integer getEmployeesCount() throws BusinessException;
}