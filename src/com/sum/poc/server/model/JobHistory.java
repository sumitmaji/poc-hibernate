package com.sum.poc.server.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sum.poc.server.adapters.DateAdapter;// Generated Dec 4, 2016 8:08:44 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JobHistory generated by hbm2java
 */
@Entity
@Table(name="JOB_HISTORY"
)
@XmlRootElement(name="jobhistory")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobHistory extends com.sum.poc.server.model.POCEntity   implements java.io.Serializable {


@XmlTransient
     private JobHistoryId id;
@XmlTransient
     private Employees employees;
@XmlTransient
     private Jobs jobs;
@XmlTransient
     private Departments departments;
@XmlJavaTypeAdapter(DateAdapter.class)
     private Date endDate;

    public JobHistory() {
    }

	
    public JobHistory(JobHistoryId id, Employees employees, Jobs jobs, Date endDate) {
        this.id = id;
        this.employees = employees;
        this.jobs = jobs;
        this.endDate = endDate;
    }
    public JobHistory(JobHistoryId id, Employees employees, Jobs jobs, Departments departments, Date endDate) {
       this.id = id;
       this.employees = employees;
       this.jobs = jobs;
       this.departments = departments;
       this.endDate = endDate;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="employeeId", column=@Column(name="EMPLOYEE_ID", nullable=false, precision=6, scale=0) ), 
        @AttributeOverride(name="startDate", column=@Column(name="START_DATE", nullable=false, length=7) ) } )
    public JobHistoryId getId() {
        return this.id;
    }
    
    public void setId(JobHistoryId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EMPLOYEE_ID", nullable=false, insertable=false, updatable=false)
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_ID", nullable=false)
    public Jobs getJobs() {
        return this.jobs;
    }
    
    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DEPARTMENT_ID")
    public Departments getDepartments() {
        return this.departments;
    }
    
    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE", nullable=false, length=7)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }




}


