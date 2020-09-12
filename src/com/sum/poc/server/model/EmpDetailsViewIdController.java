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
public class EmpDetailsViewIdController {

	@Autowired
	private EmpDetailsViewIdService empdetailsviewidService;

	@RequestMapping(value = "/data/empdetailsviewid/getEmpDetailsViewId", method = RequestMethod.GET)
	public @ResponseBody
	Response<EmpDetailsViewId> getEmpDetailsViewId(EmpDetailsViewId empdetailsviewid) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = EmpDetailsViewId.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = EmpDetailsViewId.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(empdetailsviewid, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		EmpDetailsViewId empdetailsviewidLocal = null;
		try{
			empdetailsviewidLocal = empdetailsviewidService.getEmpDetailsViewId(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (empdetailsviewidLocal != null) {
			Response<EmpDetailsViewId> empdetailsviewidResponse = new Response<EmpDetailsViewId>();
			empdetailsviewidResponse.setSuccess(true);
			empdetailsviewidResponse.setT(empdetailsviewidLocal);
			return empdetailsviewidResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/empdetailsviewid/listEmpDetailsViewId", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<EmpDetailsViewId>> listEmpDetailsViewId(HttpServletRequest request) {
		
		ListData<EmpDetailsViewId> empdetailsviewid = empdetailsviewidService.listEmpDetailsViewId(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<EmpDetailsViewId>> empdetailsviewidListResponse = new Response<ListData<EmpDetailsViewId>>();
		empdetailsviewidListResponse.setSuccess(true);
		empdetailsviewidListResponse.setT(empdetailsviewid);
		
		return empdetailsviewidListResponse;
		
	}
}
