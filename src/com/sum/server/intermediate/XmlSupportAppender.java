package com.sum.server.intermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import com.sum.server.entity.EntityTab;
import com.sum.server.entity.EntityTabFactory;
import com.sum.server.entity.EntityTabStack;

public class XmlSupportAppender extends Executor{

	protected XmlSupportAppender(String rootPackage) {
		super(rootPackage);
	}

	public static final char EOF = (char) 0; // end-of-file character
	private static String imports = "import javax.xml.bind.annotation.XmlAccessType;\n"
			+ "import javax.xml.bind.annotation.XmlAccessorType;\n"
			+ "import javax.xml.bind.annotation.XmlRootElement;\n"
			+ "import javax.xml.bind.annotation.XmlTransient;\n"
			+ "import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;\n";

	public void execute(File file) throws Exception {
		BufferedReader br = null;
		FileWriter tempFile = null;
		if (file.exists()) {
			String tempFilePath = file.getAbsolutePath().replace(
					file.getName(), "temp");
			String entityName = file.getName().replace(".java", "")
					.toLowerCase();
			String actualName  = file.getName().replace(".java", "");
			String xmlEnable = "@XmlRootElement(name=\"" + entityName + "\")\n"
					+ "@XmlAccessorType(XmlAccessType.FIELD)\n";

			br = new BufferedReader(new FileReader(file));
			try {
				tempFile = new FileWriter(new File(tempFilePath), true);
				String line = null;
				boolean lineCrossed = false;
				while ((line = br.readLine()) != null) {

					if (line.matches("( )*package()+.+")) {
						tempFile.write(line + "\n");
						tempFile.write(imports);
						tempFile.write("import "+rootPackage+".server.adapters.DateAdapter;");
						continue;
					}

					if (line.matches("( )*public( )+class( )+.+")) {
						lineCrossed = true;
						String regex = "([ ]*public[ ]+class[ ]+"+actualName+")(.+)";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(line);
						if(matcher.matches()){
							line = matcher.group(1) + " extends "+rootPackage+".server.model."+moduleName.toUpperCase()+"Entity "+matcher.group(2); 
						}
						tempFile.write(xmlEnable);
						tempFile.write(line + "\n");
						continue;
					}

					/*
					 * if(line.matches(".*FetchType.LAZY.*")){ String newLIne =
					 * line.replace("FetchType.LAZY", "FetchType.EAGER");
					 * tempFile.write(newLIne + "\n"); continue; }
					 */
					if (line.matches(".*private.*")) {
						if (lineCrossed == true) {
							String regex = "([ ]*private[ ]+)([A-Za-z0-9]+)([ ]+.*)";
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(line);
							if(matcher.matches()){
								String className = matcher.group(2);
								if(classNames.contains(className)){
									tempFile.write("@XmlTransient\n");
									tempFile.write(line+"\n");
									continue;
								}
							}	
							
						}
					}
					
					if(line.matches(".*private.*Date.*;.*")){
						tempFile.write("@XmlJavaTypeAdapter(DateAdapter.class)\n");
						tempFile.write(line+"\n");
						continue;
					}
					
					if(line.matches(".*private( )+Set<.*")){
						tempFile.write("@XmlTransient\n");
						tempFile.write(line+"\n");
						continue;
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
	List<String> classNames =null;
	public void execute() throws Exception {
		EntityTabStack stack = EntityTabFactory.getInstance();
		EntityTab entityTab = stack.enter(moduleName);
		Set<Class> clazzes = entityTab.getMap().keySet();
		classNames = (List<String>)CollectionUtils.collect(clazzes, new BeanToPropertyValueTransformer("name"));
		CollectionUtils.transform(classNames, new Transformer() {
			
			public Object transform(Object arg0) {
				return ((String)arg0).substring(((String)arg0).lastIndexOf(".")+1);
			}
		});
		
		File scannedDir = new File(SOURCEDIRECTORY+scannedPath+"/server/model");
		for (File file : scannedDir.listFiles()) {
				execute(file);
		}
	}
}
