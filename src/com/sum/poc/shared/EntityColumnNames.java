package com.sum.poc.shared;

public interface EntityColumnNames {

	public static String TABLE_EMPLOYEE = "employees";
	public static String TABLE_JOB = "jobs";
	public static String TABLE_DEPARTMENT = "departments";
	public static String TABLE_LOCATION = "locations";
	public static String TABLE_COUNTRY = "countries";
	public static String TABLE_REGION = "regions";
	
	public static String TABLE_EMPLOYEE_ID="employeeId";
	public static String TABLE_EMPLOYEE_FIRSTNAME="firstName";
	public static String TABLE_EMPLOYEE_LASTNAME="lastName";
	public static String TABLE_EMPLOYEE_JOB = TABLE_JOB;
	public static String TABLE_EMPLOYEE_DEPARTMENT="deptId";
	public static String TABLE_EMPLOYEE_MANAGERID="managerId";
	public static String TABLE_EMPLOYEE_EMAILID="email";
	public static String TABLE_EMPLOYEE_SALARY="salary";
	public static String TABLE_EMPLOYEE_HIREDATE="hireDate";
	public static String TABLE_EMPLOYEE_COMMISIONPCT="commisionPct";
	
	public static String TABLE_DEPARTMENT_ID = "id";
	public static String TABLE_DEPARTMENT_NAME = "deptName";
	public static String TABLE_DEPARTMENT_MANAGERID = "managerid";
	public static String TABLE_DEPARTMENT_LOCATION = TABLE_LOCATION;
	
	
	public static String TABLE_JOB_ID = "id";
	public static String TABLE_JOB_TITLE = "jobTitle";
	public static String TABLE_JOB_MINSALARY = "minSalary";
	public static String TABLE_JOB_MAXSALARY = "maxSalary";
	
	public static String TABLE_LOCATION_ID = "id";
	public static String TABLE_LOCATION_ADDRESS = "streetAddress";
	public static String TABLE_LOCATION_POSTAL_CODE = "postalCode";
	public static String TABLE_LOCATION_CITY = "city";
	public static String TABLE_LOCATION_STATE_PROVINCE = "stateProvince";
	public static String TABLE_LOCATION_COUNTRY = TABLE_COUNTRY;

	public static String TABLE_COUNTRY_ID = "id";
	public static String TABLE_COUNTRY_NAME = "countryName";
	public static String TABLE_COUNTRY_REGION = TABLE_REGION;
	
	
	public static String TABLE_REGION_ID = "id";
	public static String TABLE_REGION_NAME = "regionName";
	
	
	
	
	
}
