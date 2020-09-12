package com.sum.server.intermediate;

import java.util.Properties;

public class Client {
	
	public static void main(String args[]) throws Exception{
		String rootPackage = "com.sum.poc";
		String classDir = "war/WEb-INF/classes";
		//Create Adapters
		Executor executor = new AdapterCreator(rootPackage);
		executor.execute();

		//Create Response
		executor = new ResponseCreator(rootPackage);
		executor.execute();

		//Create Excpetion
		executor = new ExceptionCreator(rootPackage);
		executor.execute();

		//Create Aspects
		executor = new AspectCreator(rootPackage);
		executor.execute();

		//Create Shared
		executor = new SharedCreator(rootPackage);
		executor.execute();

		//Create DB objects
		executor = new DBEntityGenerator(rootPackage);
		executor.execute();

		//Complile the DB Objects
		executor = new JavaCompilerHelper(rootPackage);
		Properties p = new Properties();
		p.put("directory.class", classDir);
		executor.init(p);
		executor.execute();
		
		//Get Entity details
		executor = new EntityController(rootPackage);
		executor.execute();
		
		//Append xml support to DB Objects
		executor = new XmlSupportAppender(rootPackage);
		executor.execute();
		
		//Generate DAO, DAOImpl, Service, ServiceImpl
		executor = new HelperClassGenerator(rootPackage);
		executor.execute();

		executor = new EntityClassAppender(rootPackage);
		executor.execute();

		//Compile the classes
		executor = new JavaCompilerHelper(rootPackage);
		p = new Properties();
		p.put("directory.class", classDir);
		executor.init(p);
		executor.execute();
	}
}
