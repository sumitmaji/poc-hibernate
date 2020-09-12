package ${package_name};

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

import ${root_package_name}.server.exception.BusinessException;
import ${root_package_name}.server.response.ListData;
import ${root_package_name}.server.response.Message;
import ${root_package_name}.server.response.Response;
import ${root_package_name}.shared.ModelColumn;

@Controller
public class ${entityName}Controller {

	@Autowired
	private ${entityName}Service ${entity_variable}Service;

	@RequestMapping(value = "/data/${entity_variable}/get${entityName}", method = RequestMethod.GET)
	public @ResponseBody
	Response<${entityName}> get${entityName}(${entityName} ${entity_variable}) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = ${entityName}.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = ${entityName}.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(${entity_variable}, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		${entityName} ${entity_variable}Local = null;
		try{
			${entity_variable}Local = ${entity_variable}Service.get${entityName}(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (${entity_variable}Local != null) {
			Response<${entityName}> ${entity_variable}Response = new Response<${entityName}>();
			${entity_variable}Response.setSuccess(true);
			${entity_variable}Response.setT(${entity_variable}Local);
			return ${entity_variable}Response;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/${entity_variable}/list${entityName}", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<${entityName}>> list${entityName}(HttpServletRequest request) {
		
		ListData<${entityName}> ${entity_variable} = ${entity_variable}Service.list${entityName}(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<${entityName}>> ${entity_variable}ListResponse = new Response<ListData<${entityName}>>();
		${entity_variable}ListResponse.setSuccess(true);
		${entity_variable}ListResponse.setT(${entity_variable});
		
		return ${entity_variable}ListResponse;
		
	}
}
