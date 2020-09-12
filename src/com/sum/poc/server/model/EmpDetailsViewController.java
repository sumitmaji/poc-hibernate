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
public class EmpDetailsViewController {

	@Autowired
	private EmpDetailsViewService empdetailsviewService;

	@RequestMapping(value = "/data/empdetailsview/getEmpDetailsView", method = RequestMethod.GET)
	public @ResponseBody
	Response<EmpDetailsView> getEmpDetailsView(EmpDetailsView empdetailsview) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = EmpDetailsView.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = EmpDetailsView.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(empdetailsview, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		EmpDetailsView empdetailsviewLocal = null;
		try{
			empdetailsviewLocal = empdetailsviewService.getEmpDetailsView(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (empdetailsviewLocal != null) {
			Response<EmpDetailsView> empdetailsviewResponse = new Response<EmpDetailsView>();
			empdetailsviewResponse.setSuccess(true);
			empdetailsviewResponse.setT(empdetailsviewLocal);
			return empdetailsviewResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/empdetailsview/listEmpDetailsView", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<EmpDetailsView>> listEmpDetailsView(HttpServletRequest request) {
		
		ListData<EmpDetailsView> empdetailsview = empdetailsviewService.listEmpDetailsView(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<EmpDetailsView>> empdetailsviewListResponse = new Response<ListData<EmpDetailsView>>();
		empdetailsviewListResponse.setSuccess(true);
		empdetailsviewListResponse.setT(empdetailsview);
		
		return empdetailsviewListResponse;
		
	}
}
