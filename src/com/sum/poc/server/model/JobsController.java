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
public class JobsController {

	@Autowired
	private JobsService jobsService;

	@RequestMapping(value = "/data/jobs/getJobs", method = RequestMethod.GET)
	public @ResponseBody
	Response<Jobs> getJobs(Jobs jobs) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = Jobs.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = Jobs.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(jobs, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		Jobs jobsLocal = null;
		try{
			jobsLocal = jobsService.getJobs(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (jobsLocal != null) {
			Response<Jobs> jobsResponse = new Response<Jobs>();
			jobsResponse.setSuccess(true);
			jobsResponse.setT(jobsLocal);
			return jobsResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/jobs/listJobs", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<Jobs>> listJobs(HttpServletRequest request) {
		
		ListData<Jobs> jobs = jobsService.listJobs(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<Jobs>> jobsListResponse = new Response<ListData<Jobs>>();
		jobsListResponse.setSuccess(true);
		jobsListResponse.setT(jobs);
		
		return jobsListResponse;
		
	}
}
