package com.sum.poc.client;

import com.gwtext.client.data.Record;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class EmployeeDetail extends Panel{

	private EmployeeForm employeeForm = new EmployeeForm();
	private GenricGrid employeeGrid = new GenricGrid();
	public EmployeeDetail(){
		setLayout(new VerticalLayout());
		add(employeeGrid);
		add(employeeForm);
	}
	public EmployeeForm getEmployeeForm() {
		return employeeForm;
	}
	public void setEmployeeForm(EmployeeForm employeeForm) {
		this.employeeForm = employeeForm;
	}
	public GenricGrid getEmployeeGrid() {
		return employeeGrid;
	}
	public void setEmployeeGrid(GenricGrid employeeGrid) {
		this.employeeGrid = employeeGrid;
	}
}
