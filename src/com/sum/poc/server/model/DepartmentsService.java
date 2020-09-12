package com.sum.poc.server.model;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;

public interface DepartmentsService {
	public <E>Departments getDepartments(E searchValue, String searchAttribute) throws Exception;
	public ListData<Departments> listDepartments(String start, String limit) ;
}
