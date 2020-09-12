package com.sum.server.intermediate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by sumit on 3/12/16.
 */
class SharedCreator extends Executor {

    protected SharedCreator(String rootPackage) {
        super(rootPackage);
    }

    @Override
    protected void execute() throws Exception {

        new File(SOURCEDIRECTORY+scannedPath+"/shared").mkdirs();

        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATEDIRECTORY));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        String templateName = "ModelColumn";
        Template template = cfg.getTemplate(templateName
                + ".ftl");

        String fileName = SOURCEDIRECTORY
                + scannedPath + "/shared/" + "ModelColumn"+ ".java";

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("package_name",rootPackage);

        FileWriter fileWriter = new FileWriter(new File(fileName));
        try {
            template.process(input, fileWriter);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fileWriter.close();
        }


        templateName = "ServiceUrls";
        template = cfg.getTemplate(templateName
                + ".ftl");

        fileName = SOURCEDIRECTORY
                + scannedPath + "/shared/" + "ServiceUrls"+ ".java";


        fileWriter = new FileWriter(new File(fileName));
        try {
            template.process(input, fileWriter);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fileWriter.close();
        }


    }
}
