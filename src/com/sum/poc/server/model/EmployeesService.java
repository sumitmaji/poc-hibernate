package com.sum.poc.server.model;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Employees;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

public interface EmployeesService {
	public <E>Employees getEmployees(E searchValue, String searchAttribute) throws Exception;
	public ListData<Employees> listEmployees(String start, String limit) ;
}
