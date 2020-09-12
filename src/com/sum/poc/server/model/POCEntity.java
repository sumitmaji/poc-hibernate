package com.sum.poc.server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.sum.poc.server.model.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
com.sum.poc.server.model.Countries.class,com.sum.poc.server.model.Departments.class,com.sum.poc.server.model.EmpDetailsView.class,com.sum.poc.server.model.EmpDetailsViewId.class,com.sum.poc.server.model.Employees.class,com.sum.poc.server.model.JobHistory.class,com.sum.poc.server.model.JobHistoryId.class,com.sum.poc.server.model.Jobs.class,com.sum.poc.server.model.Locations.class,com.sum.poc.server.model.Persion.class,com.sum.poc.server.model.Regions.class,com.sum.poc.server.model.UserAuthentication.class,com.sum.poc.server.model.UserAuthorization.class
})
public abstract class POCEntity {

	public POCEntity(){
		super();
	}
}
