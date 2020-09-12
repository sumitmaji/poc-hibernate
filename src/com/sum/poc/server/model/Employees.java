package com.sum.poc.server.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sum.poc.server.adapters.DateAdapter;// Generated Dec 4, 2016 8:08:44 AM by Hibernate Tools 3.2.4.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Employees generated by hbm2java
 */
@Entity
@Table(name="EMPLOYEES"
    , uniqueConstraints = @UniqueConstraint(columnNames="EMAIL") 
)
@XmlRootElement(name="employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees extends com.sum.poc.server.model.POCEntity   implements java.io.Serializable {


     private int employeeId;
@XmlTransient
     private Jobs jobs;
@XmlTransient
     private Departments departments;
@XmlTransient
     private Employees employees;
     private String firstName;
     private String lastName;
     private String email;
     private String phoneNumber;
@XmlJavaTypeAdapter(DateAdapter.class)
     private Date hireDate;
     private BigDecimal salary;
     private BigDecimal commissionPct;
@XmlTransient
     private Set<Employees> employeeses = new HashSet<Employees>(0);
@XmlTransient
     private Set<Departments> departmentses = new HashSet<Departments>(0);
@XmlTransient
     private Set<JobHistory> jobHistories = new HashSet<JobHistory>(0);

    public Employees() {
    }

	
    public Employees(int employeeId, Jobs jobs, String lastName, String email, Date hireDate) {
        this.employeeId = employeeId;
        this.jobs = jobs;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
    }
    public Employees(int employeeId, Jobs jobs, Departments departments, Employees employees, String firstName, String lastName, String email, String phoneNumber, Date hireDate, BigDecimal salary, BigDecimal commissionPct, Set<Employees> employeeses, Set<Departments> departmentses, Set<JobHistory> jobHistories) {
       this.employeeId = employeeId;
       this.jobs = jobs;
       this.departments = departments;
       this.employees = employees;
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.phoneNumber = phoneNumber;
       this.hireDate = hireDate;
       this.salary = salary;
       this.commissionPct = commissionPct;
       this.employeeses = employeeses;
       this.departmentses = departmentses;
       this.jobHistories = jobHistories;
    }
   
     @Id 

    
    @Column(name="EMPLOYEE_ID", unique=true, nullable=false, precision=6, scale=0)
    public int getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MANAGER_ID")
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    
    @Column(name="FIRST_NAME", length=20)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="LAST_NAME", nullable=false, length=25)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="EMAIL", unique=true, nullable=false, length=25)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="PHONE_NUMBER", length=20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="HIRE_DATE", nullable=false, length=7)
    public Date getHireDate() {
        return this.hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    
    @Column(name="SALARY", precision=8)
    public BigDecimal getSalary() {
        return this.salary;
    }
    
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    
    @Column(name="COMMISSION_PCT", precision=2)
    public BigDecimal getCommissionPct() {
        return this.commissionPct;
    }
    
    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
    public Set<Employees> getEmployeeses() {
        return this.employeeses;
    }
    
    public void setEmployeeses(Set<Employees> employeeses) {
        this.employeeses = employeeses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
    public Set<Departments> getDepartmentses() {
        return this.departmentses;
    }
    
    public void setDepartmentses(Set<Departments> departmentses) {
        this.departmentses = departmentses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
    public Set<JobHistory> getJobHistories() {
        return this.jobHistories;
    }
    
    public void setJobHistories(Set<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }




}

