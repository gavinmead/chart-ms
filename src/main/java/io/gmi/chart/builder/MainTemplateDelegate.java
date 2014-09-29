package io.gmi.chart.builder;

import com.google.common.io.Files;
import io.gmi.chart.Constants;
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
 * Delegate responsible for creating a base html file rendered with scripts/styles
 */
class MainTemplateDelegate extends ChartBuilderDelegate {

  private static final Logger log = LoggerFactory.getLogger(MainTemplateDelegate.class);
  private static final String TEMPLATE_PATH = "main.tpl.vm";

  private VelocityEngine velocityEngine;

  MainTemplateDelegate(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    Map<String, String> resources = context.getContextMapValue(ChartBuilderContext.RESOURCES_KEY, Map.class,
        this::handleMissingContextResource);
    Map<String, Object> templateMap = new HashMap<>();
    templateMap.putAll(resources);
    if(templateMap.containsKey(Constants.$BOOTSTRAP)) {
      templateMap.put("useBootstrap", Boolean.valueOf(true));
    } else
      templateMap.put("useBootstrap", Boolean.valueOf(false));

    log.info("useBootstrap value set to {}", templateMap.get("useBootstrap"));
    String mainTemplateString = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TEMPLATE_PATH, "utf-8", templateMap);
    String templatePath = context.getConfiguration().DYNAMIC_TEMPLATE_PATH;

    log.info("Saving main template html as {} in directory {}", context.getContextId() + ".vm", templatePath);
    File tempFile = new File(templatePath, context.getContextId() + ".vm");
    tempFile.deleteOnExit();
    try {
      Files.createParentDirs(tempFile);
      Files.write(mainTemplateString.getBytes(), tempFile);
      context.getContextMap().put(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY, tempFile.getPath());
    } catch (IOException ie) {
      throw new RuntimeException("Error creating main template.vm file", ie);
    }
  }

  private void handleMissingContextResource(String key) {
    throw new IllegalStateException("Missing key " + key + " from the context");
  }
}
