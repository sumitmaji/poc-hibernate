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
public class RegionsController {

	@Autowired
	private RegionsService regionsService;

	@RequestMapping(value = "/data/regions/getRegions", method = RequestMethod.GET)
	public @ResponseBody
	Response<Regions> getRegions(Regions regions) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Regions.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Regions.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(regions, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Regions regionsLocal = null;
		try{
			regionsLocal = regionsService.getRegions(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (regionsLocal != null) {
			Response<Regions> regionsResponse = new Response<Regions>();
			regionsResponse.setSuccess(true);
			regionsResponse.setT(regionsLocal);
			return regionsResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/regions/listRegions", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Regions>> listRegions(HttpServletRequest request) {
		
		ListData<Regions> regions = regionsService.listRegions(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Regions>> regionsListResponse = new Response<ListData<Regions>>();
		regionsListResponse.setSuccess(true);
		regionsListResponse.setT(regions);
		
		return regionsListResponse;
		
	}
}
