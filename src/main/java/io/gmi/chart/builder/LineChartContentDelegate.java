package io.gmi.chart.builder;

import com.google.common.base.Preconditions;
import io.gmi.chart.requests.ChartRequest;
import io.gmi.chart.requests.LineChartRequest;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 9/25/14.
 */
class LineChartContentDelegate extends AppContentDelegate {

  private static final Logger log = LoggerFactory.getLogger(LineChartContentDelegate.class);
  private static final String TEMPLATE = "line-chart-content.tpl.vm";

  LineChartContentDelegate(VelocityEngine velocityEngine) {
    super(velocityEngine);
  }

  @Override
  protected String getAppContent(ChartBuilderContext context, ChartRequest request) {
    Preconditions.checkArgument(request instanceof LineChartRequest, "request must a LineChartRequest");
    LineChartRequest lineChartRequest = (LineChartRequest) request;
    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("seriesList", lineChartRequest.getData());
    return VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), TEMPLATE, "utf-8", contentMap);
  }
}
