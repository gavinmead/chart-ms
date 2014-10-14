package io.gmi.chartms.service;

import io.gmi.chartms.config.AppConfig;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 10/14/14.
 */
@Component
class DefaultGenerateImageDelegate implements GenerateImageDelegate {

  private static final Logger log = LoggerFactory.getLogger(DefaultGenerateImageDelegate.class);

  @Autowired
  AppConfig appConfig;

  @Override
  public String generateImage(ChartCreationContext chartCreationContext) {
    CommandLine commandLine = new CommandLine("phantomjs");
    commandLine.addArgument("${script}");
    commandLine.addArgument("${clippingRectDiv}");
    commandLine.addArgument("${infile}");
    commandLine.addArgument("${outfile}");

    DefaultExecutor executor = new DefaultExecutor();
    executor.setExitValue(0);
    ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
    executor.setWatchdog(watchdog);
    Map argumentMap = createSubstitutionMap(chartCreationContext);
    commandLine.setSubstitutionMap(argumentMap);
    log.info("Submitting arguments {} to phantomjs", argumentMap);
    try {
      executor.execute(commandLine);
    } catch (IOException e) {
      throw new RuntimeException("An error occurred while running phantomjs", e);
    }
    return appConfig.getImageDir() + File.separator + chartCreationContext.getContextId() + ".png";
  }

  protected Map createSubstitutionMap(ChartCreationContext chartCreationContext) {
    Map commandLineMap = new HashMap();
    commandLineMap.put("script", appConfig.getRenderImageScript());
    commandLineMap.put("clippingRectDiv", chartCreationContext.getChartRequest().getClippingDiv());
    commandLineMap.put("infile", chartCreationContext.getContextMapValue(ChartCreationContext.HTML_DOCUMENT_KEY, String.class));
    commandLineMap.put("outfile", appConfig.getImageDir() + File.separator + chartCreationContext.getContextId() + ".png");
    return commandLineMap;
  }

}
