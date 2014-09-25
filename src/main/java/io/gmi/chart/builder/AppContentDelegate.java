package io.gmi.chart.builder;

import io.gmi.chart.requests.ChartRequest;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 9/25/14.
 */
abstract class AppContentDelegate extends ChartBuilderDelegate {

  private static final Logger log = LoggerFactory.getLogger(AppContentDelegate.class);

  private static final String TEMPLATE = "app-js.tpl.vm";

  private VelocityEngine velocityEngine;

  AppContentDelegate(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    Map<String, Object> appJsContentMap = new HashMap<>();
    appJsContentMap.put("chartOptions", request.getChartOptions().getOptions());
    String appContent = getAppContent(context, request);
    appJsContentMap.put("appContent", appContent);
    String jsApplication = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TEMPLATE, "utf-8", appJsContentMap);
    context.getContextMap().put(ChartBuilderContext.JS_APPLICATION_KEY, jsApplication);
    context.getContextMap().put(ChartBuilderContext.CHART_TAG_KEY, getChartTag());
  }

  protected abstract String getAppContent(ChartBuilderContext context, ChartRequest request);

  protected abstract String getChartTag();

  protected VelocityEngine getVelocityEngine() {
    return velocityEngine;
  }
}
