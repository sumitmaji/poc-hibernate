package com.sum.poc.server.model;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

public interface EmpDetailsViewService {
	public <E>EmpDetailsView getEmpDetailsView(E searchValue, String searchAttribute) throws Exception;
	public ListData<EmpDetailsView> listEmpDetailsView(String start, String limit) ;
}
