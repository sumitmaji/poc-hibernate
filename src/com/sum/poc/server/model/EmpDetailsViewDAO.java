package com.sum.poc.server.model;

import com.sum.poc.server.model.Countries;
import com.sum.poc.server.model.Departments;
import com.sum.poc.server.model.EmpDetailsView;
import com.sum.poc.server.exception.BusinessException;
import java.util.List;

public interface EmpDetailsViewDAO{
	public <E>EmpDetailsView get(E searchValue, String searchAttribute) throws Exception;
	public List<EmpDetailsView> list(String start, String limit) throws BusinessException;
	public Integer getEmpDetailsViewCount() throws BusinessException;
}