package io.gmi.chart.builder;

import com.google.common.io.Files;
import io.gmi.chart.requests.ChartRequest;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 9/25/14.
 */
public class RequestTemplateDelegate extends ChartBuilderDelegate {

  private static final Logger log = LoggerFactory.getLogger(RequestTemplateDelegate.class);
  private static final String TEMP_REQUEST_FILE_FORMAT = "%s-request.tpl.vm";
  VelocityEngine velocityEngine;

  public RequestTemplateDelegate(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    String templateFile = createTempTemplateFile(context, request);
    Map<String, Object> requestMap = mergeRequestContent(context, request);
    String requestTemplateContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFile, "utf-8", requestMap);
    context.getContextMap().put(ChartBuilderContext.REQUEST_TEMPLATE_CONTENT_KEY, requestTemplateContent);
  }

  protected Map<String, Object> mergeRequestContent(ChartBuilderContext context, ChartRequest chartRequest) {
    Map<String, Object> requestMap = new HashMap<>();
    requestMap.putAll(context.getContextMapValue(ChartBuilderContext.IMAGE_KEY, Map.class));
    requestMap.putAll(chartRequest.getTemplateContent());
    requestMap.put(chartRequest.getChartId(), context.getContextMapValue(ChartBuilderContext.CHART_TAG_KEY, String.class));
    return requestMap;
  }

  protected String createTempTemplateFile(ChartBuilderContext context, ChartRequest request) {
    String templateFile = String.format(TEMP_REQUEST_FILE_FORMAT, context.getContextId());
    File tempFile = new File(context.getConfiguration().DYNAMIC_TEMPLATE_PATH, templateFile);
    tempFile.deleteOnExit();
    try {
      Files.createParentDirs(tempFile);
      Files.write(request.getTemplate().getBytes(), tempFile);
    }catch(IOException ie) {
      throw new RuntimeException("Error creating the request template file", ie);
    }
    log.info("Creating temporary template file {} for the template on request {} ", tempFile.getPath(), context.getContextId());
    return templateFile;
  }
}
