package com.sum.poc.server.model;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.model.EmpDetailsViewId;
import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;

public interface EmpDetailsViewIdService {
	public <E>EmpDetailsViewId getEmpDetailsViewId(E searchValue, String searchAttribute) throws Exception;
	public ListData<EmpDetailsViewId> listEmpDetailsViewId(String start, String limit) ;
}
