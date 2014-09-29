package io.gmi.chart.builder;

import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.requests.ChartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 9/24/14.
 */
public class ScriptStylesDelegate extends ChartBuilderDelegate {

  private static final Logger log = LoggerFactory.getLogger(ScriptStylesDelegate.class);

  private static final String FORMAT = "file://%s";

  @Autowired
  ChartMSConfiguration configuration;

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    Map<String, String> resourceMap = new HashMap<>();
    resourceMap.put("nvd3css", String.format(FORMAT, context.getConfiguration().NVD3_CSS_WORKING_PATH()));
    resourceMap.put("bootstrap", String.format(FORMAT, context.getConfiguration().BOOSTRAP_CSS_WORKING_PATH()));
    resourceMap.put("es5shim", String.format(FORMAT, context.getConfiguration().ES5_SHIM_SCRIPT_WORKING_PATH()));
    resourceMap.put("angularjs", String.format(FORMAT, context.getConfiguration().ANGULAR_SCRIPT_WORKING_PATH()));
    resourceMap.put("d3", String.format(FORMAT, context.getConfiguration().D3_SCRIPT_WORKING_PATH()));
    resourceMap.put("nvd3", String.format(FORMAT, context.getConfiguration().NVD3_SCRIPT_WORKING_PATH()));
    resourceMap.put("angularNvd3", String.format(FORMAT, context.getConfiguration().ANGULAR_NVD3_SCRIPT_WORKING_PATH()));
    context.getContextMap().put(ChartBuilderContext.RESOURCES_KEY, resourceMap);
  }

}
