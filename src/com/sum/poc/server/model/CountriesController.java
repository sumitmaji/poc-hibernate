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
public class CountriesController {

	@Autowired
	private CountriesService countriesService;

	@RequestMapping(value = "/data/countries/getCountries", method = RequestMethod.GET)
	public @ResponseBody
	Response<Countries> getCountries(Countries countries) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Countries.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Countries.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(countries, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Countries countriesLocal = null;
		try{
			countriesLocal = countriesService.getCountries(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (countriesLocal != null) {
			Response<Countries> countriesResponse = new Response<Countries>();
			countriesResponse.setSuccess(true);
			countriesResponse.setT(countriesLocal);
			return countriesResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/countries/listCountries", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Countries>> listCountries(HttpServletRequest request) {
		
		ListData<Countries> countries = countriesService.listCountries(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Countries>> countriesListResponse = new Response<ListData<Countries>>();
		countriesListResponse.setSuccess(true);
		countriesListResponse.setT(countries);
		
		return countriesListResponse;
		
	}
}
