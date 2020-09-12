package com.sum.server.intermediate;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public abstract class Executor {

	protected String rootPackage;
	private static String dirRootPath = "war/WEB-INF/";
	private static String resouceDirName = "resources";
	protected static String SOURCEDIRECTORY = "src/";
	protected static String LIBDIRECTORY = dirRootPath+"lib/";
	protected static String DBBUILDFILEDIRECTORY = dirRootPath+resouceDirName+"/dbbuild/";
	protected static String DATABASEDIRECTORY = dirRootPath+resouceDirName+"/spring/database/";
	protected static String PROPERTIESDIRECTORY = dirRootPath+resouceDirName+"/properties/";
	protected static String CLASSDIRECTORY = "classes/";
	protected static String TEMPLATEDIRECTORY = dirRootPath+resouceDirName+"/template/";
	protected static String CONFIGDIRECTORY = dirRootPath+resouceDirName+"/spring/config/";
	
	private static final char DOT = '.';
	private static final char SLASH = '/';
	protected String scannedPath;
	protected String moduleName = null;
	
	protected Executor(String rootPackage) {
		this.rootPackage = rootPackage;
		scannedPath = this.rootPackage.replace(DOT, SLASH);
		String[] ar = rootPackage.split("\\.");
		moduleName = ar[ar.length-1];
	}

	public void init(Properties properties) {
		SOURCEDIRECTORY = properties.getProperty("directory.source") != null ? properties
				.getProperty("directory.source") : "src/";
		CLASSDIRECTORY = properties.getProperty("directory.class") != null ? properties
				.getProperty("directory.class") : "classes/";
		LIBDIRECTORY = properties.getProperty("directory.library") != null ? properties
				.getProperty("directory.library") : dirRootPath+"lib/";
		DBBUILDFILEDIRECTORY = properties.getProperty("directory.build.file") != null ? properties
						.getProperty("directory.build.file") : dirRootPath+resouceDirName+"/dbbuild/";
		TEMPLATEDIRECTORY = properties.getProperty("directory.template") != null ? properties
								.getProperty("directory.template") : dirRootPath+resouceDirName+"/template/";
		PROPERTIESDIRECTORY = properties.getProperty("directory.properties") != null ? properties
						.getProperty("directory.properties") : dirRootPath+resouceDirName+"/properties/";
		DATABASEDIRECTORY = properties.getProperty("directory.database") != null ? properties
						.getProperty("directory.database") : dirRootPath+resouceDirName+"/spring/database/";

		CONFIGDIRECTORY = properties.getProperty("directory.config") != null ? properties
						.getProperty("directory.config") : dirRootPath+resouceDirName+"/spring/config/";

	}

	protected abstract void execute() throws Exception;
}
