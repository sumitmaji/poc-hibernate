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
public class JobHistoryController {

	@Autowired
	private JobHistoryService jobhistoryService;

	@RequestMapping(value = "/data/jobhistory/getJobHistory", method = RequestMethod.GET)
	public @ResponseBody
	Response<JobHistory> getJobHistory(JobHistory jobhistory) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = JobHistory.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = JobHistory.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(jobhistory, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		JobHistory jobhistoryLocal = null;
		try{
			jobhistoryLocal = jobhistoryService.getJobHistory(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (jobhistoryLocal != null) {
			Response<JobHistory> jobhistoryResponse = new Response<JobHistory>();
			jobhistoryResponse.setSuccess(true);
			jobhistoryResponse.setT(jobhistoryLocal);
			return jobhistoryResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/jobhistory/listJobHistory", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<JobHistory>> listJobHistory(HttpServletRequest request) {
		
		ListData<JobHistory> jobhistory = jobhistoryService.listJobHistory(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<JobHistory>> jobhistoryListResponse = new Response<ListData<JobHistory>>();
		jobhistoryListResponse.setSuccess(true);
		jobhistoryListResponse.setT(jobhistory);
		
		return jobhistoryListResponse;
		
	}
}
