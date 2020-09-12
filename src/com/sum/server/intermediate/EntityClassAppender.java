package com.sum.server.intermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlSeeAlso;

public class EntityClassAppender extends Executor{

	protected EntityClassAppender(String rootPackage) {
		super(rootPackage);
	}

	public static final char EOF = (char) 0; // end-of-file character
	
	public void execute(File file) throws Exception {
		BufferedReader br = null;
		FileWriter tempFile = null;
		if (file.exists()) {
			String tempFilePath = file.getAbsolutePath().replace(
					file.getName(), "temp");
			String entityName = file.getName().replace(".java", "")
					.toLowerCase();
			String actualName  = file.getName().replace(".java", "");
	
			br = new BufferedReader(new FileReader(file));
			try {
				tempFile = new FileWriter(new File(tempFilePath), true);
				String line = null;
				while ((line = br.readLine()) != null) {

					if (line.matches("( )*package()+.+")) {
						tempFile.write(line + "\n");
						if((line = br.readLine()) != null)
						{
							if(!line.equals("import "+rootPackage+".server.model"+".*;"))
							{
								tempFile.write("import "+rootPackage+".server.model"+".*;\n");
								tempFile.write(line+"\n");
								continue;								
							}
							else
							{
								tempFile.write(line+"\n");
								continue;
							}
						}
					}

					
					if (line.matches("( )*@XmlSeeAlso.*")) {
						tempFile.write(line + "\n");
						if((line = br.readLine()) != null)
 {
							if (!line.equals(moduleName.toUpperCase()
									+ "Entity.class")) {
								tempFile.write(moduleName.toUpperCase()
										+ "Entity.class" + "\n");
								tempFile.write(line + "\n");
								continue;
							} else {
								tempFile.write(line + "\n");
								continue;
							}
						}
					}

					tempFile.write(line + "\n");
				}

				tempFile.flush();

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}

			finally {
				if (tempFile != null) {
					tempFile.close();
				}
				if (br != null) {
					br.close();
				}
			}
			
			String filePath = file.getAbsolutePath();
			file.delete();
			File renameFile = new File(tempFilePath);
			renameFile.renameTo(new File(filePath));
		}
	}

	public void execute() throws Exception {
		File scannedDir = new File(SOURCEDIRECTORY+scannedPath+"/server/response");
		for (File file : scannedDir.listFiles()) {
				if(file.getName().equals("Entity.java"))
					execute(file);
		}
	}
}
