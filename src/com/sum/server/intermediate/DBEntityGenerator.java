package com.sum.server.intermediate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class DBEntityGenerator extends Executor {

	protected DBEntityGenerator(String rootPackage) {
		super(rootPackage);
	}

	String password="sumit";
	String url="jdbc:oracle:thin:@192.168.1.100:1521:XE";
	String userName="HR";
	String schema="HR";
	String hibernateConfig;
	private static String HIBERNATECFGTEMPLATENAME = "Hibernate";
	private static String SPRINGHIBERTEMPLATENAME = "SpringHibernate";
	private static String APPLICATOINPLATENAME = "web-application-config";
	private static String HIBERNATEBUILDFILETEMPLATENAME  ="Dbbuild";
	private static String DATASOURCETEMPLATENAME  ="DataSource";
	
	protected void execute() throws Exception {
		// create the directory for db build file
		File buildFile = new File(DBBUILDFILEDIRECTORY + moduleName + ".xml");
		if (buildFile.exists()) {
			Project p = new Project();
			p.setUserProperty("ant.file", buildFile.getAbsolutePath());
			DefaultLogger consoleLogger = new DefaultLogger();
			consoleLogger.setErrorPrintStream(System.err);
			consoleLogger.setOutputPrintStream(System.out);
			consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
			p.addBuildListener(consoleLogger);

			try {
				p.fireBuildStarted();
				p.init();
				ProjectHelper helper = ProjectHelper.getProjectHelper();
				p.addReference("ant.projectHelper", helper);
				helper.parse(p, buildFile);
				p.executeTarget(p.getDefaultTarget());
				p.fireBuildFinished(null);
			} catch (BuildException e) {
				p.fireBuildFinished(e);
			}
		} else {
			createFiles();
		}
	}
	
	private void createFiles() throws Exception
	{

		new File(DBBUILDFILEDIRECTORY).mkdirs();
		new File(DATABASEDIRECTORY).mkdirs();
		new File(CONFIGDIRECTORY).mkdirs();
		
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATEDIRECTORY));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Template template = cfg.getTemplate(HIBERNATECFGTEMPLATENAME
				+ ".ftl");
		
		
		Properties prop = new Properties();
		InputStream input1 = null;
		try{
			input1 = new FileInputStream(PROPERTIESDIRECTORY+"database.properties");
			prop.load(input1);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}finally{
			if(input1 != null)
				input1.close();
		}
		
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("userName", prop.get("jdbc.username"));
		input.put("password", prop.get("jdbc.password"));
		input.put("schema", prop.get("jdbc.schema"));
		input.put("url", prop.get("jdbc.url"));

		String fileName = DBBUILDFILEDIRECTORY + moduleName + ".cfg.xml";

		FileWriter fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
		
		hibernateConfig = DBBUILDFILEDIRECTORY+moduleName+".cfg.xml";
		input = new HashMap<String, Object>();
		input.put("rootPackage", rootPackage);
		input.put("hibernateConfig", hibernateConfig);
		input.put("sourceDirectory", SOURCEDIRECTORY);
		
		template = cfg.getTemplate(HIBERNATEBUILDFILETEMPLATENAME
				+ ".ftl");
		fileName = DBBUILDFILEDIRECTORY + moduleName + ".xml";
		
		fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
		
		input = new HashMap<String, Object>();
		input.put("rootPackage", rootPackage);
		
		template = cfg.getTemplate(SPRINGHIBERTEMPLATENAME
				+ ".ftl");
		fileName = DATABASEDIRECTORY+"Hibernate" + ".xml";
		
		fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
	
		input = new HashMap<String, Object>();
		input.put("rootPackage", rootPackage);
		
		template = cfg.getTemplate(APPLICATOINPLATENAME
				+ ".ftl");
		fileName = CONFIGDIRECTORY+"web-application-config" + ".xml";
		
		fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
/*		
		input = new HashMap<String, Object>();
		input.put("dataBaseProp", "/WEB-INF/resources/properties/database.properties");
		
		template = cfg.getTemplate(DATASOURCETEMPLATENAME
				+ ".ftl");
		fileName = CONFIGDIRECTORY+"DataSource" + ".xml";
		
		fileWriter = new FileWriter(new File(fileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
		

	*/	
		
		
		execute();
	}
	
}
