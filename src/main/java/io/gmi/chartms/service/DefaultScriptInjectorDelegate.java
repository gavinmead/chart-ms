package io.gmi.chartms.service;

import com.google.common.annotations.VisibleForTesting;
import io.gmi.chartms.config.AppConfig;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by gmead on 10/14/14.
 */
@Component
class DefaultScriptInjectorDelegate implements ScriptInjectorDelegate, InitializingBean {

  private static final Logger log = LoggerFactory.getLogger(DefaultScriptInjectorDelegate.class);

  private static final String SCRIPT_SRC_FORMAT = "file://%s";

  private static final String SCRIPT =
      "$(function() {\n" +
          "$(\'#%s\').highcharts(%s);\n" +
      "});\n";


  @Autowired
  private AppConfig appConfig;

  private Element jquery;

  private Element highCharts;

  @Override
  public Document injectScript(Document html, ChartCreationContext chartCreationContext) {
    Document clonedDocument = html.clone();

    Element head = clonedDocument.head();
    head.appendChild(jquery);
    head.appendChild(highCharts);

    Element appScript = new Element(Tag.valueOf("script"), "");
    appScript.attr("id", "appScript");
    String chartDiv = getChartDiv(clonedDocument, chartCreationContext);
    DataNode scriptContent = new DataNode(createScript(chartCreationContext, chartDiv), "");
    appScript.appendChild(scriptContent);

    head.appendChild(appScript);

    return clonedDocument;
  }

  private String createScript(ChartCreationContext chartCreationContext, String chartDiv) {
    if(StringUtils.isEmpty(chartCreationContext.getChartRequest().getChart())) {
      log.warn("No chart data included request");
    }
    return String.format(SCRIPT, chartDiv, chartCreationContext.getChartRequest().getChart());
  }

  private String getChartDiv(Document document, ChartCreationContext chartCreationContext) {
    if(StringUtils.isEmpty(chartCreationContext.getChartRequest().getHtml())) {
      log.info("No html included in request.  Inserting a placeholder div directly under body with context id.");
      document.body().html("<div id=\"" + chartCreationContext.getContextId() + "\"></div>");
      return chartCreationContext.getContextId();
    } else {
      //Confirm that id exists in document.
      Element container = document.getElementById(chartCreationContext.getChartRequest().getChartContainerId());
      if(container == null) {
        throw new IllegalStateException("Document does not contain an element is ID "
            + chartCreationContext.getChartRequest().getChartContainerId());
      }
      return chartCreationContext.getChartRequest().getChartContainerId();
    }
  }

  @VisibleForTesting
  Element getJquery() {
    return jquery;
  }

  @VisibleForTesting
  Element getHighCharts() {
    return highCharts;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("Pre building jquery and highchart elements");

    jquery = new Element(Tag.valueOf("script"), "");
    jquery.attr("id", "jquery");
    jquery.attr("src", String.format(SCRIPT_SRC_FORMAT, appConfig.getJqueryScript()));
    log.info("JQuery element \n\n{}\n", jquery);

    highCharts = new Element(Tag.valueOf("script"), "");
    highCharts.attr("id", "highcharts");
    highCharts.attr("src", String.format(SCRIPT_SRC_FORMAT, appConfig.getHighChartsScript()));
    log.info("Highcharts element \n\n{}\n", highCharts);
  }
}
