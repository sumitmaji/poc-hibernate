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
public class JobHistoryIdController {

	@Autowired
	private JobHistoryIdService jobhistoryidService;

	@RequestMapping(value = "/data/jobhistoryid/getJobHistoryId", method = RequestMethod.GET)
	public @ResponseBody
	Response<JobHistoryId> getJobHistoryId(JobHistoryId jobhistoryid) throws BusinessException {

		String searchAttribute = null;
		Object searchValue = null;		
		try {
			Field[] fields = JobHistoryId.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method method = JobHistoryId.class.getMethod(
						"get" + WordUtils.capitalize(field.getName()),
						new Class[] {});
				Object obj = method.invoke(jobhistoryid, new Object[] {});
				if (obj != null) {
					searchAttribute = field.getName();
					searchValue = obj;
					break;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		JobHistoryId jobhistoryidLocal = null;
		try{
			jobhistoryidLocal = jobhistoryidService.getJobHistoryId(searchValue, searchAttribute);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		if (jobhistoryidLocal != null) {
			Response<JobHistoryId> jobhistoryidResponse = new Response<JobHistoryId>();
			jobhistoryidResponse.setSuccess(true);
			jobhistoryidResponse.setT(jobhistoryidLocal);
			return jobhistoryidResponse;
		} else {
			throw new BusinessException(ModelColumn.MESSAGE_NO_DATA);
		}
	}

	@RequestMapping(value = "/data/jobhistoryid/listJobHistoryId", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<JobHistoryId>> listJobHistoryId(HttpServletRequest request) {
		
		ListData<JobHistoryId> jobhistoryid = jobhistoryidService.listJobHistoryId(request.getParameter("start"),
				request.getParameter("limit"));
		
		Response<ListData<JobHistoryId>> jobhistoryidListResponse = new Response<ListData<JobHistoryId>>();
		jobhistoryidListResponse.setSuccess(true);
		jobhistoryidListResponse.setT(jobhistoryid);
		
		return jobhistoryidListResponse;
		
	}
}
