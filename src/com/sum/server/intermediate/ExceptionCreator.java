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
class ExceptionCreator extends Executor {

    protected ExceptionCreator(String rootPackage) {
        super(rootPackage);
    }

    @Override
    protected void execute() throws Exception {

        new File(SOURCEDIRECTORY+scannedPath+"/server/exception").mkdirs();

        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATEDIRECTORY));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("package_name",rootPackage);
        String subPath = "/server/exception/";

        String temaplateName = "BusinessException";
        Template template = cfg.getTemplate(temaplateName
                + ".ftl");

        String fileName = SOURCEDIRECTORY
                + scannedPath + subPath + "BusinessException"+ ".java";


        FileWriter fileWriter = new FileWriter(new File(fileName));
        try {
            template.process(input, fileWriter);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fileWriter.close();
        }


        temaplateName = "BusinessValidationException";
        template = cfg.getTemplate(temaplateName
                + ".ftl");

        fileName = SOURCEDIRECTORY
                + scannedPath + subPath + "BusinessValidationException"+ ".java";


        fileWriter = new FileWriter(new File(fileName));
        try {
            template.process(input, fileWriter);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fileWriter.close();
        }


        temaplateName = "CustomGenericException";
        template = cfg.getTemplate(temaplateName
                + ".ftl");

        fileName = SOURCEDIRECTORY
                + scannedPath + subPath + "CustomGenericException"+ ".java";


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
