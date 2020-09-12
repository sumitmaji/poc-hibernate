package com.sum.poc.server.model;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.commons.lang.WordUtils;

import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.response.ListData;
import com.sum.poc.server.response.Message;
import com.sum.poc.server.response.Response;
import com.sum.poc.shared.ModelColumn;

@Controller
public class EmployeesController {

	@Autowired
	private EmployeesService employeesService;

	@RequestMapping(value = "/data/employees/getEmployees", method = RequestMethod.GET)
	public @ResponseBody
	Response<Employees> getEmployees(Employees employees) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Employees.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Employees.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(employees, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Employees employeesLocal = null;
		try{
			employeesLocal = employeesService.getEmployees(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (employeesLocal != null) {
			Response<Employees> employeesResponse = new Response<Employees>();
			employeesResponse.setSuccess(true);
			employeesResponse.setT(employeesLocal);
			return employeesResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/employees/listEmployees", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Employees>> listEmployees(HttpServletRequest request) {
		
		ListData<Employees> employees = employeesService.listEmployees(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Employees>> employeesListResponse = new Response<ListData<Employees>>();
		employeesListResponse.setSuccess(true);
		employeesListResponse.setT(employees);
		
		return employeesListResponse;
		
	}
}
