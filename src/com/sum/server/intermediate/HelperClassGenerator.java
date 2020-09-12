package com.sum.server.intermediate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class HelperClassGenerator extends Executor{

	protected HelperClassGenerator(String rootPackage) {
		super(rootPackage);
	}

	private Set<String> importList = new HashSet<String>();
	private String entityName;
	private String keyField;
	private String packageName;
	private String entity_variable;
	private Class clazz;
	private Configuration cfg;
	protected String templateName;
	List<Class<?>> classes = new ArrayList<Class<?>>();

	public void process(Class clazz, String templateName,
			List<Class<?>> classes, String moduleName) throws IOException {
		this.clazz = clazz;
		this.templateName = templateName;
		this.classes = classes;
		this.moduleName = moduleName;
		populateProperties();
		init();
		process();
	}

	public void init() throws IOException {
		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATEDIRECTORY));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	void process() throws IOException {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("importList", getImportList());
		input.put("package_name", getPackageName());
		input.put("entityName", getEntityName());
		input.put("entity_variable", getEntity_variable());
		input.put("entityList", getEntityList());
		input.put("module", moduleName);
		input.put("root_package_name", rootPackage);

		Template template = cfg.getTemplate(templateName + ".ftl");

		String fileName = SOURCEDIRECTORY
				+ scannedPath + "/server/model/" + getEntityName()
				+ templateName + ".java";

		FileWriter fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			fileWriter.close();
		}
	}

	void populateProperties() {
		if (moduleName == null) {
			this.entityName = clazz.getSimpleName();
			this.packageName = clazz.getPackage().getName();
			this.entity_variable = entityName.toLowerCase();
			importList.add(clazz.getName());
		} else {
			this.entityName = moduleName;
			this.packageName = clazz.getPackage().getName();
			for (Class clazz : classes) {
				entityList +=(clazz.getName() + ".class,");
			}
			
			if(entityList != null && entityList.lastIndexOf(",")!=-1)
				entityList = entityList.substring(0, entityList.lastIndexOf(","));  
		}

	}

	public Set<String> getImportList() {
		return importList;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getKeyField() {
		return keyField;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getEntity_variable() {
		return entity_variable;
	}

	private String entityList = "";

	private String getEntityList() {
		return entityList;
	}

	private static String DAO = "DAO";
	private static String DAOIMPL = "DAOImpl";
	private static String SERVICE = "Service";
	private static String SERVICEIMPL = "ServiceImpl";
	private static String ENTITY = "Entity";
	private static String CONTROLLER = "Controller";
	private static String HIBERNATECONTROLLERTEMPLATENAME = "HibernateEntityController";

	protected void execute() throws Exception {

		List<Class<?>> classes = ClassFinder
				.find(rootPackage+".server.model");
		
		for (Class clazz : classes) {
			process(clazz, DAO, null, null);
			process(clazz, DAOIMPL, null, null);
			process(clazz, SERVICE, null, null);
			process(clazz, SERVICEIMPL, null, null);
			process(clazz, CONTROLLER, null, null);
		}
		
		String[] ar = rootPackage.split("\\.");
		
		process(classes.get(0), ENTITY, classes, ar[ar.length-1].toUpperCase());
		
		HashMap<String, Object>input = new HashMap<String, Object>();
		input.put("rootPackage", rootPackage);
		
		Template template = cfg.getTemplate(HIBERNATECONTROLLERTEMPLATENAME
				+ ".ftl");
		String fileName = SOURCEDIRECTORY
		+ scannedPath + "/server/model/" + "HibernateEntityController" + ".java";;
		
		FileWriter fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
		
		
	}

}
