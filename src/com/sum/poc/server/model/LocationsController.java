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
public class LocationsController {

	@Autowired
	private LocationsService locationsService;

	@RequestMapping(value = "/data/locations/getLocations", method = RequestMethod.GET)
	public @ResponseBody
	Response<Locations> getLocations(Locations locations) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Locations.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Locations.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(locations, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Locations locationsLocal = null;
		try{
			locationsLocal = locationsService.getLocations(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (locationsLocal != null) {
			Response<Locations> locationsResponse = new Response<Locations>();
			locationsResponse.setSuccess(true);
			locationsResponse.setT(locationsLocal);
			return locationsResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/locations/listLocations", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Locations>> listLocations(HttpServletRequest request) {
		
		ListData<Locations> locations = locationsService.listLocations(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Locations>> locationsListResponse = new Response<ListData<Locations>>();
		locationsListResponse.setSuccess(true);
		locationsListResponse.setT(locations);
		
		return locationsListResponse;
		
	}
}
