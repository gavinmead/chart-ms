package io.gmi.chart.builder;

import com.google.common.io.Files;
import io.gmi.chart.requests.ChartRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by gmead on 9/26/14.
 */
class MergeContentDelegate extends ChartBuilderDelegate {

  private static final Logger log = LoggerFactory.getLogger(MergeContentDelegate.class);

  private static final String MERGE_FILE_FORMAT = "%s-merged.html";

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    String mainTemplate = context.getContextMapValue(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY, String.class);

    File mainTemplateFile = new File(mainTemplate);
    if(!mainTemplateFile.exists()) {
      throw new IllegalStateException("File " + mainTemplate + " does not exist");
    }

    try {
      Document mainHtml = Jsoup.parse(mainTemplateFile, "utf-8");
      injectJSApplication(context, mainHtml);
      injectRequestTemplate(context, mainHtml);
      File savedFile = new File(context.getConfiguration().WORKING_DIRECTORY_PATH(), String.format(MERGE_FILE_FORMAT,context.getContextId()));
      Files.write(mainHtml.html().getBytes(), savedFile);
      context.getContextMap().put(ChartBuilderContext.FINAL_HTML_FILE_KEY, savedFile.getPath());
    } catch (IOException e) {
      throw new RuntimeException("An error occurred while trying process " + mainTemplate, e);
    }

  }

  protected void injectJSApplication(ChartBuilderContext context, Document mainHtml) {
    Element appScriptElement = mainHtml.getElementById("appScript");
    if(appScriptElement == null)
      throw new IllegalStateException("Cannot inject application into template.  Template " + context.getContextMapValue(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY, String.class)
          + " does have an appScript element." );
    appScriptElement.html(context.getContextMapValue(ChartBuilderContext.JS_APPLICATION_KEY, String.class));
  }

  protected void injectRequestTemplate(ChartBuilderContext context, Document mainHtml) {
    mainHtml.body().html(context.getContextMapValue(ChartBuilderContext.REQUEST_TEMPLATE_CONTENT_KEY, String.class));
  }

}
