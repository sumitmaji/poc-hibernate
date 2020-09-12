package com.sum.poc.client;

import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.XmlReader;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.Form;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FormListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.sum.poc.shared.ModelColumn;
import com.sum.poc.shared.ServiceUrls;

public class EmployeeForm extends Panel {

	private TextField firstNameTextField;
	private TextField idTextField;
	private TextField jobTitleField;
	private FormPanel formPanel;
	private Button saveButton;
	private ComboBox deptComboBox;
	private Store responseStore;
	private Store deptStore;

	public EmployeeForm() {

		setBorder(false);
		setPaddings(15);
		setLayout(new VerticalLayout());

		// setup error reader to process from submit response from server
		RecordDef errorRecordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("id"), new StringFieldDef("msg") });

		XmlReader errorReader = new XmlReader("field", errorRecordDef);
		errorReader.setSuccess("@success");

		// setup error reader to process from submit response from server
		RecordDef recordDef = new RecordDef(new FieldDef[] {/*
				new StringFieldDef(ModelColumn.EMPLOYEE_FIRST_NAME_GWT,ModelColumn.EMPLOYEE_FIRST_NAME_XML),
				new StringFieldDef(ModelColumn.EMPLOYEE_ID_GWT,ModelColumn.EMPLOYEE_ID_XML),
				new StringFieldDef(ModelColumn.EMPLOYEE_DEPARTMENT_NAME_GWT,ModelColumn.EMPLOYEE_DEPARTMENT_NAME_XML),
				new IntegerFieldDef(ModelColumn.EMPLOYEE_DEPARTMENT_ID_GWT,ModelColumn.EMPLOYEE_DEPARTMENT_ID_XML),
				new StringFieldDef(ModelColumn.EMPLOYEE_JOB_TITLE_GWT,ModelColumn.EMPLOYEE_JOB_TITLE_XML) */});

		XmlReader reader = new XmlReader(ModelColumn.EMPLOYEE_XML_READER,
				recordDef);
		reader.setSuccess("@success");

		// Form Panel
		formPanel = new FormPanel();
		formPanel.setWidth(600);
		formPanel.setPaddings(5, 5, 5, 0);
		formPanel.setLabelAlign(Position.TOP);
		formPanel.setReader(reader);
		formPanel.setErrorReader(errorReader);
		formPanel.addFormListener(new EmployeeFormListener());

		// Panel with column layout to added to cloumns
		Panel innerPanel = new Panel();
		innerPanel.setLayout(new ColumnLayout());

		// Column One
		Panel columnOne = new Panel();
		columnOne.setLayout(new FormLayout());

		firstNameTextField = new TextField("Employee First Name",
				ModelColumn.EMPLOYEE_FIRST_NAME_GWT);
		columnOne.add(firstNameTextField, new AnchorLayoutData("95%"));

		idTextField = new TextField("Employee Id", ModelColumn.EMPLOYEE_ID_GWT);
		columnOne.add(idTextField, new AnchorLayoutData("95%"));

		innerPanel.add(columnOne, new ColumnLayoutData(0.5));

		// Cloumn two
		Panel columnTwo = new Panel();
		columnTwo.setLayout(new FormLayout());

		registerDeptStore();
		getDeptStore().load();
		deptComboBox = new ComboBox("Department",ModelColumn.EMPLOYEE_DEPARTMENT_ID_GWT);
		deptComboBox.setForceSelection(true);  
        deptComboBox.setMinChars(1);  
        deptComboBox.setStore(getDeptStore());  
        deptComboBox.setDisplayField(ModelColumn.DEPARTMENT_NAME_XML);  
        deptComboBox.setValueField(ModelColumn.DEPARTMENT_ID_XML);
        deptComboBox.setMode(ComboBox.REMOTE);  
        deptComboBox.setTriggerAction(ComboBox.ALL);  
        deptComboBox.setEmptyText("Enter department");  
        deptComboBox.setLoadingText("Searching...");  
        deptComboBox.setTypeAhead(true);
		
		
		columnTwo.add(deptComboBox);
		
		jobTitleField = new TextField("Job Title",
				ModelColumn.EMPLOYEE_JOB_TITLE_GWT);
		columnTwo.add(jobTitleField, new AnchorLayoutData("95%"));

		innerPanel.add(columnTwo, new ColumnLayoutData(0.5));

		formPanel.add(innerPanel);

		// Buttons Panel
		Panel buttonsPanel = new Panel();
		buttonsPanel.setPaddings(10, 0, 10, 10);

		saveButton = new Button("Save");
		saveButton.addListener(new EmployeeSave());

		buttonsPanel.add(saveButton);
		add(formPanel);
		add(buttonsPanel);

		registerResponse();

	}

	private void registerResponse() {
		XmlReader responseReader = new XmlReader("message", new RecordDef(
				new FieldDef[] { new StringFieldDef("@text"), }));
		responseStore = new Store(responseReader);
		responseStore.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoad(Store store, Record[] records) {
				if (records.length > 0) {
					MessageBox.alert(records[0].getAsString("@text"));
				}
			}
		});

	}
	
	private void registerDeptStore(){
		HttpProxy proxy = new HttpProxy(ServiceUrls.GET_DEPARTMENT_LIST, Connection.GET);
		XmlReader deptReader = new XmlReader(ModelColumn.DEPARTMENT_XML_READER, new RecordDef(
				new FieldDef[] { new IntegerFieldDef(ModelColumn.DEPARTMENT_ID_XML), new StringFieldDef(ModelColumn.DEPARTMENT_NAME_XML)}));
		deptStore = new Store(proxy, deptReader);
	}
	

	public Store getDeptStore() {
		return deptStore;
	}

	public void setDeptStore(Store deptStore) {
		this.deptStore = deptStore;
	}

	public Store getResponseStore() {
		return responseStore;
	}

	public void setResponseStore(Store responseStore) {
		this.responseStore = responseStore;
	}

	public TextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(TextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public TextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(TextField idTextField) {
		this.idTextField = idTextField;
	}

	public TextField getJobTitleField() {
		return jobTitleField;
	}

	public void setJobTitleField(TextField jobTitleField) {
		this.jobTitleField = jobTitleField;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

	public ComboBox getDeptComboBox() {
		return deptComboBox;
	}

	public void setDeptComboBox(ComboBox deptComboBox) {
		this.deptComboBox = deptComboBox;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	class EmployeeSave extends ButtonListenerAdapter {

		@Override
		public void onClick(Button button, EventObject e) {

			UrlParam[] urlParams = new UrlParam[getFormPanel().getFields().length];
			UrlParam urlParam;
			int i = 0;
			for (Field field : getFormPanel().getFields()) {
				urlParam = new UrlParam(field.getName(),
						field.getValueAsString());
				urlParams[i] = urlParam;
				i++;
			}
			getFormPanel().getForm().submit(ServiceUrls.UPD_EMPLOYEE, null,
					Connection.GET, ModelColumn.MESSAGE_SAVE, false);
		}
	}

	class EmployeeFormListener extends FormListenerAdapter {

		public void onActionComplete(Form form, int httpStatus,
				String responseText) {
			if (responseText.indexOf("message") != -1)
				getResponseStore().loadXmlData(responseText.toString(), false);
		}

		public void onActionFailed(Form form, int httpStatus,
				String responseText) {
			if (responseText.indexOf("message") != -1)
				getResponseStore().loadXmlData(responseText.toString(), false);
		}
	}
}
