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
public class UserAuthenticationController {

	@Autowired
	private UserAuthenticationService userauthenticationService;

	@RequestMapping(value = "/data/userauthentication/getUserAuthentication", method = RequestMethod.GET)
	public @ResponseBody
	Response<UserAuthentication> getUserAuthentication(UserAuthentication userauthentication) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = UserAuthentication.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = UserAuthentication.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(userauthentication, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		UserAuthentication userauthenticationLocal = null;
		try{
			userauthenticationLocal = userauthenticationService.getUserAuthentication(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (userauthenticationLocal != null) {
			Response<UserAuthentication> userauthenticationResponse = new Response<UserAuthentication>();
			userauthenticationResponse.setSuccess(true);
			userauthenticationResponse.setT(userauthenticationLocal);
			return userauthenticationResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/userauthentication/listUserAuthentication", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<UserAuthentication>> listUserAuthentication(HttpServletRequest request) {
		
		ListData<UserAuthentication> userauthentication = userauthenticationService.listUserAuthentication(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<UserAuthentication>> userauthenticationListResponse = new Response<ListData<UserAuthentication>>();
		userauthenticationListResponse.setSuccess(true);
		userauthenticationListResponse.setT(userauthentication);
		
		return userauthenticationListResponse;
		
	}
}
