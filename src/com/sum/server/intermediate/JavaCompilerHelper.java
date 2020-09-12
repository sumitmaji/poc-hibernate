package com.sum.server.intermediate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.EndpointReference;

public class JavaCompilerHelper extends Executor{

	private static final char DOT = '.';

	private static final char SLASH = '/';

	private static final String JAVA_SUFFIX = ".java";


	protected JavaCompilerHelper(String rootPackage) {
		super(rootPackage);
	}

	boolean compile(String[] args, OutputStream out) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try {
			/* try to use the new compiler */
			Class comSunToolsJavacMainClass = cl
					.loadClass("com.sun.tools.javac.Main");
			try {
				Method compileMethod = comSunToolsJavacMainClass.getMethod(
						"compile", compileMethodSignature);
				Object result = compileMethod.invoke(null, args,
						new PrintWriter(out));
				return result instanceof Integer && (Integer) result == 0;
			} catch (NoSuchMethodException e2) {
				e2.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final Class[] compileMethodSignature = { String[].class,
			PrintWriter.class };
	
	public boolean compileGeneratedClasses() throws FileNotFoundException {
		PrintStream out = new PrintStream(new File("F:/log.txt"));
		List<String> sourceFiles = new ArrayList<String>();
		
		File scannedDir = new File(SOURCEDIRECTORY + scannedPath);

		for (File f : scannedDir.listFiles()) {
			//if (f.exists() && f.getName().endsWith(".java")) {
				sourceFiles.addAll(find(f,rootPackage));
			//}
		}

		if (sourceFiles.size() > 0) {
			String classpathString = createClasspathString();
			boolean bootCP = useBootClasspath(EndpointReference.class)
					|| useBootClasspath(XmlSeeAlso.class);
			String[] args = new String[4  + 1 +1
					+ sourceFiles.size()];
			args[0] = "-d";
			args[1] = CLASSDIRECTORY;
			args[2] = "-classpath";
			args[3] = classpathString;
			int baseIndex = 4;
			if (bootCP) {
				args[baseIndex++] = "-Xbootclasspath/p:"
						+ getJarFile(EndpointReference.class)
						+ File.pathSeparator
						+ getJarFile(XmlSeeAlso.class);
			}

			args[baseIndex++] = "-g";
			for (int i = 0; i < sourceFiles.size(); ++i) {
				args[baseIndex + i] = sourceFiles.get(i);
			}

				StringBuffer argstr = new StringBuffer();
				for (String arg : args) {
					argstr.append(arg).append(" ");
				}
				System.out.println("javac " + argstr.toString());

			return compile(args, out);
		}
		// there are no files to compile, so return true?
		return true;
	}

	private String createClasspathString() {
		String classpathStr = System.getProperty("java.class.path");
		File libDir = new File(LIBDIRECTORY);
		
		for (File f : libDir.listFiles()) {
			classpathStr = classpathStr + File.pathSeparator + f;
		}
		return classpathStr;
	}

	public URL toJarUrl(URL res) throws ClassNotFoundException,
			MalformedURLException {
		String url = res.toExternalForm();
		if (!url.startsWith("jar:"))
			throw new ClassNotFoundException("Loaded outside a jar " + url);
		url = url.substring(4); // cut off jar:
		url = url.substring(0, url.lastIndexOf('!')); // cut off everything
														// after '!'
		return new URL(url);
	}

	File getJarFile(Class clazz) {
		URL url = null;
		try {
			url = toJarUrl(clazz.getResource('/'
					+ clazz.getName().replace('.', '/') + ".class"));
			return new File(url.toURI());
		} catch (ClassNotFoundException e) {
			// if we can't figure out where JAXB/JAX-WS API are, we couldn't
			// have been executing this code.
			throw new Error(e);
		} catch (MalformedURLException e) {
			// if we can't figure out where JAXB/JAX-WS API are, we couldn't
			// have been executing this code.
			throw new Error(e);
		} catch (URISyntaxException e) {
			// url.toURI() is picky and doesn't like ' ' in URL, so this is the
			// fallback
			return new File(url.getPath());
		}
	}

	private boolean useBootClasspath(Class clazz) {
		try {
			toJarUrl(clazz.getResource('/' + clazz.getName().replace('.', '/')
					+ ".class"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected void execute() throws Exception {
		compileGeneratedClasses();
	}


	private List<String> find(File file, String scannedPackage) {
		List<String> sourceFiles = new ArrayList<String>();
		String resource = scannedPackage + DOT + file.getName();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				sourceFiles.addAll(find(child, resource));
			}
		} else if (file.exists() && file.getName().endsWith(".java")) {
				sourceFiles.add(file.getAbsolutePath());
		}
		return sourceFiles;
	}
}
