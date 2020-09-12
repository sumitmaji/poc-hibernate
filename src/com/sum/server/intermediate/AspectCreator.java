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
class AspectCreator extends Executor {

    protected AspectCreator(String rootPackage) {
        super(rootPackage);
    }

    @Override
    protected void execute() throws Exception {

        new File(SOURCEDIRECTORY+scannedPath+"/server/aspects").mkdirs();

        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATEDIRECTORY));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        String temaplateName = "GlobalBusinessExceptionHandler";
        Template template = cfg.getTemplate(temaplateName
                + ".ftl");

        String fileName = SOURCEDIRECTORY
                + scannedPath + "/server/aspects/" + "GlobalBusinessExceptionHandler"+ ".java";

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


    }
}
