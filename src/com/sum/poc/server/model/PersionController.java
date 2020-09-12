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
public class PersionController {

	@Autowired
	private PersionService persionService;

	@RequestMapping(value = "/data/persion/getPersion", method = RequestMethod.GET)
	public @ResponseBody
	Response<Persion> getPersion(Persion persion) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Persion.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Persion.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(persion, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Persion persionLocal = null;
		try{
			persionLocal = persionService.getPersion(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (persionLocal != null) {
			Response<Persion> persionResponse = new Response<Persion>();
			persionResponse.setSuccess(true);
			persionResponse.setT(persionLocal);
			return persionResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/persion/listPersion", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Persion>> listPersion(HttpServletRequest request) {
		
		ListData<Persion> persion = persionService.listPersion(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Persion>> persionListResponse = new Response<ListData<Persion>>();
		persionListResponse.setSuccess(true);
		persionListResponse.setT(persion);
		
		return persionListResponse;
		
	}
}
