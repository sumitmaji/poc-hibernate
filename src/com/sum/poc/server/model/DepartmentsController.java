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
public class DepartmentsController {

	@Autowired
	private DepartmentsService departmentsService;

	@RequestMapping(value = "/data/departments/getDepartments", method = RequestMethod.GET)
	public @ResponseBody
	Response<Departments> getDepartments(Departments departments) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Departments.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Departments.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(departments, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Departments departmentsLocal = null;
		try{
			departmentsLocal = departmentsService.getDepartments(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (departmentsLocal != null) {
			Response<Departments> departmentsResponse = new Response<Departments>();
			departmentsResponse.setSuccess(true);
			departmentsResponse.setT(departmentsLocal);
			return departmentsResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/departments/listDepartments", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Departments>> listDepartments(HttpServletRequest request) {
		
		ListData<Departments> departments = departmentsService.listDepartments(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Departments>> departmentsListResponse = new Response<ListData<Departments>>();
		departmentsListResponse.setSuccess(true);
		departmentsListResponse.setT(departments);
		
		return departmentsListResponse;
		
	}
}
