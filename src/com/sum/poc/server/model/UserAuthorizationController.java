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
public class UserAuthorizationController {

	@Autowired
	private UserAuthorizationService userauthorizationService;

	@RequestMapping(value = "/data/userauthorization/getUserAuthorization", method = RequestMethod.GET)
	public @ResponseBody
	Response<UserAuthorization> getUserAuthorization(UserAuthorization userauthorization) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = UserAuthorization.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = UserAuthorization.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(userauthorization, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		UserAuthorization userauthorizationLocal = null;
		try{
			userauthorizationLocal = userauthorizationService.getUserAuthorization(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (userauthorizationLocal != null) {
			Response<UserAuthorization> userauthorizationResponse = new Response<UserAuthorization>();
			userauthorizationResponse.setSuccess(true);
			userauthorizationResponse.setT(userauthorizationLocal);
			return userauthorizationResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/userauthorization/listUserAuthorization", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<UserAuthorization>> listUserAuthorization(HttpServletRequest request) {
		
		ListData<UserAuthorization> userauthorization = userauthorizationService.listUserAuthorization(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<UserAuthorization>> userauthorizationListResponse = new Response<ListData<UserAuthorization>>();
		userauthorizationListResponse.setSuccess(true);
		userauthorizationListResponse.setT(userauthorization);
		
		return userauthorizationListResponse;
		
	}
}
