package io.gmi.chart.builder;

import io.gmi.chart.requests.ChartRequest;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 9/26/14.
 */
class InvokePhantomJsDelegate extends ChartBuilderDelegate {

  private static final Logger log = LoggerFactory.getLogger(InvokePhantomJsDelegate.class);

  private static final String SCRIPT_FILE = "render-image.js";


  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {

    CommandLine commandLine = new CommandLine("phantomjs");
    commandLine.addArgument("${script}");
    commandLine.addArgument("${clippingRectDiv}");
    commandLine.addArgument("${infile}");
    commandLine.addArgument("${outfile}");

    DefaultExecutor executor = new DefaultExecutor();
    executor.setExitValue(0);
    ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
    executor.setWatchdog(watchdog);
    Map argumentMap = createSubstitutionMap(context, request);
    commandLine.setSubstitutionMap(argumentMap);
    log.info("Submitting arguments {} to phantomjs", argumentMap);
    try {
      executor.execute(commandLine);
    } catch (IOException e) {
      throw new RuntimeException("An error occurred while running phantomjs", e);
    }
  }

  protected Map createSubstitutionMap(ChartBuilderContext context, ChartRequest request) {
    Map commandLineMap = new HashMap();
    commandLineMap.put("script", context.getConfiguration().WORKING_DIRECTORY_PATH() + File.separator + SCRIPT_FILE);
    commandLineMap.put("clippingRectDiv", request.getClippingDivId());
    commandLineMap.put("infile", context.getContextMapValue(ChartBuilderContext.FINAL_HTML_FILE_KEY, String.class));
    commandLineMap.put("outfile", context.getConfiguration().WORKING_DIRECTORY_PATH() + File.separator + context.getContextId() + ".png");
    return commandLineMap;
  }
}
